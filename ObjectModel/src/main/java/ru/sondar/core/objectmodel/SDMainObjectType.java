/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sondar.core.objectmodel;

/**
 * Description of possible types of objects
 *
 * @author GlebZemnieks
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
