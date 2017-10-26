package uk.org.tombolo.field.aggregation;

import uk.org.tombolo.core.Subject;
import uk.org.tombolo.recipe.FieldRecipe;
import uk.org.tombolo.field.AbstractField;
import uk.org.tombolo.field.Field;
import uk.org.tombolo.field.IncomputableFieldException;
import uk.org.tombolo.field.SingleValueField;

import java.util.ArrayList;
import java.util.List;

/**
 * Field for providing backed off values when none exist.
 * An example back-off would be mapping to a value for a parent geography.
 */

//TOASK how many nested levels can I have?
    // - As many as I want
    // The user needs to make sure is doing the right thing, we can't guarantee that
    // User testing: name confusing. TOASK shall we change? - Oxford Dictionary
public class BackOffField extends AbstractField {

    private List<FieldRecipe> fields;

    //TOASK naming missleading, this is not real materialisation. CHnage the name to be consistent with the rest of
    // the fields. Find a name we like :) to be decided ... something loike instance or object?!
    private List<Field> materialisedFields;

    public BackOffField(String label, List<FieldRecipe> fields){
        super(label);
        this.fields = fields;
    }

    //TOASK initialise helper method in AbstractField
    public void initialize() {
        this.materialisedFields = new ArrayList<>();
        for (FieldRecipe field : fields) {
            try {
                materialisedFields.add(field.toField());
            } catch (ClassNotFoundException e) {
                throw new Error("Field not valid");
            }
        }
    }

    @Override
    public String valueForSubject(Subject subject, Boolean timeStamp) throws IncomputableFieldException {

        if (materialisedFields == null)
            initialize();
        for (Field field : materialisedFields) {
            String value = null;
            try {
                //TOASK if no backoff value found it will return no value
                value = ((SingleValueField) field).valueForSubject(subject, timeStamp);
            } catch (IncomputableFieldException e){
                // Keep calm and continue processing ... we will back-off
                continue;
            }
            if (value != null)
                return value;
        }
        throw new IncomputableFieldException("No Backed-off value found");
    }
}
