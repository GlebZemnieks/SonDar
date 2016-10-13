package ru.sondar.plugin.driver.exception;

import ru.sondar.core.exception.SonDarRuntimeException;

/**
 *
 * @author GlebZemnieks
 */
public class CellNotFoundException extends SonDarRuntimeException {

    public CellNotFoundException() {
        super();
    }

    public CellNotFoundException(String msg) {
        super(msg);
    }
}
