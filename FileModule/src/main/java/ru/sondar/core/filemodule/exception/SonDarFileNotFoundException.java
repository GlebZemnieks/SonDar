package ru.sondar.core.filemodule.exception;

import ru.sondar.core.exception.SonDarRuntimeException;

/**
 * Throw when code trying to open file, which not exist by path
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SonDarFileNotFoundException extends SonDarRuntimeException {

    public SonDarFileNotFoundException() {
        super();
    }

    public SonDarFileNotFoundException(String msg) {
        super(msg);
    }

}
