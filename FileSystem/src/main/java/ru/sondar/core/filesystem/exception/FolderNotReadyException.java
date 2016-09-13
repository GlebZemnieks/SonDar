package ru.sondar.core.filesystem.exception;

import ru.sondar.core.exception.SonDarRuntimeException;

/**
 * Throw when appeal to folder whose state not None
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class FolderNotReadyException extends SonDarRuntimeException {

    public FolderNotReadyException() {
        super();
    }

    public FolderNotReadyException(String msg) {
        super(msg);
    }

}
