package ru.sondar.plugin.driver.exception;


import ru.sondar.core.exception.SonDarRuntimeException;

/**
 *
 * @author GlebZemnieks
 */
public class FunctionalityNotSupportedException extends SonDarRuntimeException {

    public FunctionalityNotSupportedException() {
        super();
    }

    public FunctionalityNotSupportedException(String msg) {
        super(msg);
    }

    public FunctionalityNotSupportedException(String msg, Exception cause) {
        super(msg, cause);
    }
}
