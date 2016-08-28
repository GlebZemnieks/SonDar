package ru.sondar.client.objectmodel.android;

import ru.sondar.client.documentmodel.android.AXMLSequenceObject;
import ru.sondar.documentmodel.objectmodel.SDMainObject;
import ru.sondar.documentmodel.objectmodel.SDMainObjectType;

/**
 * Created by GlebZemnieks on 8/19/2016.
 */
public enum AXMLMainObjectType {
    /**
     * Check box for boolean choice
     */
    CheckBox,
    /**
     * Date object
     */
    Date,
    /**
     * Container for objects group
     */
    DivContainer,
    /**
     * Editable test field
     */
    EditText,
    /**
     * End of line object
     */
    EndLn,
    /**
     * If we can't determining object type, return error object
     */
    ErrorObject,
    /**
     * Drop-down list
     */
    Spinner,
    /**
     * Just text filed. Without activity
     */
    Text;

    /**
     * Return object by type.
     *
     * @return
     */
    public SDMainObject getObjectByType() {
        switch (this) {
            case Text:
                return new AXMLText();
            case Spinner:
                return new AXMLSpinner();
            case CheckBox:
                return new AXMLCheckBox();
            case EndLn:
                return new AXMLEndln();
            case Date:
                return new AXMLDate();
            case EditText:
                return new AXMLEditText();
            case DivContainer:
                return new AXMLSequenceObject();
            default:
                throw new UnsupportedOperationException("Object with type \"" + this.toString() + "\" not supported");
        }
    }
}
