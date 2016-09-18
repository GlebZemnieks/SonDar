package ru.sondar.core.exception;

/**
 *
 * @author GlebZemnieks
 */
public class SonDarRuntimeException extends RuntimeException {

    public SonDarRuntimeException() {
        super();
    }

    public SonDarRuntimeException(String msg) {
        super(msg);
    }

    public SonDarRuntimeException(String msg, Exception cause) {
        super(msg, cause);
    }
}
