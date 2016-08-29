package ru.sondar.documentmodel.dependencymodel.exception;

import ru.sondar.core.exception.SonDarRuntimeException;

/**
 * Throw when in link object missing some of attribute
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class MissingDependencyAttributeException extends SonDarRuntimeException {

    public MissingDependencyAttributeException() {
        super();
    }

    public MissingDependencyAttributeException(String msg) {
        super(msg);
    }
}
