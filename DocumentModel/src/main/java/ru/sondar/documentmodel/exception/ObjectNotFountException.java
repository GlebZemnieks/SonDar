package ru.sondar.documentmodel.exception;

import ru.sondar.core.exception.SonDarRuntimeException;

/**
 * Throw when object target id not exist in navigator list
 *
 * @author GlebZemnieks
 */
public class ObjectNotFountException extends SonDarRuntimeException {

    public ObjectNotFountException() {
        super();
    }

    public ObjectNotFountException(String msg) {
        super(msg);
    }

    public ObjectNotFountException(String msg, Exception cause) {
        super(msg, cause);
    }

}
