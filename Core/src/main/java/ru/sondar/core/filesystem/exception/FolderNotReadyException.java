package ru.sondar.core.filesystem.exception;

/**
 * Throw when appeal to folder whose state not None
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class FolderNotReadyException extends RuntimeException {

    public FolderNotReadyException() {
        super();
    }

    public FolderNotReadyException(String msg) {
        super(msg);
    }

}
