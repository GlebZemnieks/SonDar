package ru.sondar.documentmodel.dependencymodel.exception;

/**
 * Throw when in link object missing some of attribute
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class MissingDependencyAttributeException extends RuntimeException {

    public MissingDependencyAttributeException() {
        super();
    }

    public MissingDependencyAttributeException(String msg) {
        super(msg);
    }
}
