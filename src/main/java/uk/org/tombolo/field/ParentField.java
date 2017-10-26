package uk.org.tombolo.field;

import java.util.List;

//TOASK check if this is legacy code not needed anymore.
//TOASK investigate where it is used and if it's still needed
public interface ParentField extends Field {
    // TOASK is any field that contains fields
    List<Field> getChildFields();
}
