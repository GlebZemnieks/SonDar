package ru.sondar.plugin.driver.exception;

import ru.sondar.core.exception.SonDarException;

/**
 * Throw when code try to open not exist file
 *
 * @author GlebZemnieks
 */
public class DataBaseFileNotFoundException extends SonDarException {

    public DataBaseFileNotFoundException() {
        super();
    }

    public DataBaseFileNotFoundException(String msg) {
        super(msg);
    }

    public DataBaseFileNotFoundException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
