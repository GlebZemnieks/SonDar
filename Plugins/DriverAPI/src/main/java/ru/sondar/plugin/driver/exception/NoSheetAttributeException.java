package ru.sondar.plugin.driver.exception;

import ru.sondar.core.exception.SonDarRuntimeException;

/**
 * Throw when excel configuration file have missing attribute
 *
 * @author GlebZemnieks
 */
public class NoSheetAttributeException extends SonDarRuntimeException {

    public NoSheetAttributeException() {
        super();
    }

    public NoSheetAttributeException(String msg) {
        super(msg);
    }
}
