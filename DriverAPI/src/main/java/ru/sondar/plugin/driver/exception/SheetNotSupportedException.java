package ru.sondar.plugin.driver.exception;

import ru.sondar.core.exception.SonDarRuntimeException;

/**
 * Throw when code try to set active sheet which not supported in this plug-in
 *
 * @author GlebZemnieks
 */
public class SheetNotSupportedException extends SonDarRuntimeException {

    public SheetNotSupportedException() {
        super();
    }

    public SheetNotSupportedException(String msg) {
        super(msg);
    }
}
