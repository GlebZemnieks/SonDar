package ru.sondar.core.filesystem.exception;

import ru.sondar.core.exception.SonDarRuntimeException;

/**
 * Throw when try to initialization folder not belong FileSystem
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class FolderObjectNotInSystemException extends SonDarRuntimeException {

    public FolderObjectNotInSystemException() {
        super();
    }

    public FolderObjectNotInSystemException(String msg) {
        super(msg);
    }

}
