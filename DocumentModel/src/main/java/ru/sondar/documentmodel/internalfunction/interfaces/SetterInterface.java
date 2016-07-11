package ru.sondar.documentmodel.internalfunction.interfaces;

/**
 * Interface for using in internal function structure. Implement this interface
 * to open object to changer.
 *
 * @author GlebZemnieks
 */
public interface SetterInterface {

    /**
     * Get parameter of object type. Cast it if target field have different
     * type.
     *
     * @param obj
     */
    void setValueByAction(Object obj);
}
