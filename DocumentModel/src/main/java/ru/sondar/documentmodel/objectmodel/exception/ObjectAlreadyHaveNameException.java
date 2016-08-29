package ru.sondar.documentmodel.objectmodel.exception;

import ru.sondar.core.exception.SonDarRuntimeException;

/**
 * Throw when code try set name to object second time
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class ObjectAlreadyHaveNameException extends SonDarRuntimeException {

    public ObjectAlreadyHaveNameException() {
        super();
    }

    public ObjectAlreadyHaveNameException(String msg) {
        super(msg);
    }
}
