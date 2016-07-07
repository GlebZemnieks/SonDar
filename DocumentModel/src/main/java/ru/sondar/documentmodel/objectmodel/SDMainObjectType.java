package ru.sondar.documentmodel.objectmodel;

import ru.sondar.documentmodel.SDSequenceObject;

/**
 * Description of possible types of objects
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public enum SDMainObjectType {
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
                return new SDText();
            case Spinner:
                return new SDSpinner();
            case CheckBox:
                return new SDCheckBox();
            case EndLn:
                return new SDEndln();
            case Date:
                return new SDDate();
            case EditText:
                return new SDEditText();
            case DivContainer:
                return new SDSequenceObject();
            default:
                throw new UnsupportedOperationException("Object with type \"" + this.toString() + "\" not supported");
        }
    }
}
