package ru.sondar.core.dependencymodel.exception;

/**
 * Throw when in link object missing some of attribute
 *
 * @author GlebZemnieks
 */
public class MissingDependencyAttributeException extends RuntimeException {

    public MissingDependencyAttributeException() {
        super();
    }

    public MissingDependencyAttributeException(String msg) {
        super(msg);
    }
}
