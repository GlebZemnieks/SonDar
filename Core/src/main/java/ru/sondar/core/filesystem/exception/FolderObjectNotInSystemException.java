package ru.sondar.core.filesystem.exception;

/**
 * Throw when try to initialization folder not belong FileSystem
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class FolderObjectNotInSystemException extends RuntimeException {

    public FolderObjectNotInSystemException() {
        super();
    }

    public FolderObjectNotInSystemException(String msg) {
        super(msg);
    }

}
