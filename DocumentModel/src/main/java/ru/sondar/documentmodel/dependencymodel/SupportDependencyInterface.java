package ru.sondar.documentmodel.dependencymodel;

/**
 * Implement this interface for make possible to set DB protection for object
 * type
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
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
