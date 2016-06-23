package ru.sondar.documentmodel.objectmodel;

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
}
