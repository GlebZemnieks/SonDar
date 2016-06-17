package ru.sondar.documentmodel.internalfunction.interfaces;

/**
 * Object which will be work as navigation object
 *
 * @author GlebZemnieks
 */
public interface NavigatorInterface {

    /**
     * Get some object by key
     *
     * @param key
     * @return
     */
    Object getObject(Object obj);
}
