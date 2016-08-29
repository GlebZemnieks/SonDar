package ru.sondar.core.filesystem.exception;

import ru.sondar.core.exception.SonDarRuntimeException;

/**
 * Throw when user want to save file which not opened
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class FileNotOpenedException extends SonDarRuntimeException {

    public FileNotOpenedException() {
        super();
    }

    public FileNotOpenedException(String msg) {
        super(msg);
    }

}
