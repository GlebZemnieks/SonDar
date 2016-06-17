package ru.sondar.documentmodel.internalfunction.interfaces;

/**
 * Interface for using in internal function structure. Implement this interface
 * if object have trigger field to check it.
 *
 * @author GlebZemnieks
 */
public interface ValueCheckerInterface {

    /**
     * Get parameter of object type. Return true is it equal with value of
     * target field of trigger
     *
     * @param obj
     * @return
     */
    boolean ifValue(Object obj);
}
