package ru.sondar.core.dependencymodel;

/**
 * Implement this interface for make possible to set DB protection for object
 * type
 *
 * @author GlebZemnieks
 */
public interface SupportDependencyInterface {

    /**
     * Get abstract value and converting to supported format or throw exception
     *
     * @return
     */
    Object getValue();

    /**
     * Set abstract value to supported format
     *
     * @param object
     */
    void setValue(Object object);
}
