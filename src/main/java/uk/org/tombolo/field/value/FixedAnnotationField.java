package uk.org.tombolo.field.value;

import uk.org.tombolo.core.Subject;
import uk.org.tombolo.field.AbstractField;

/**
 * FixedAnnotationField.java
 * Returns a fixed value for annotation purposes.
 */
//ANS change the name to ConstantField
public class FixedAnnotationField extends AbstractField {
    private final String value;

    public FixedAnnotationField(String label, String value) {
        super(label);
        this.value = value;
    }

    @Override
    public String valueForSubject(Subject subject, Boolean timeStamp) {
        return value;
    }

}
