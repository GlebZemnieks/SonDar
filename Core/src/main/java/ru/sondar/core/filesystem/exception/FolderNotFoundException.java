package ru.sondar.core.filesystem.exception;

/**
 * Throw when folder not belong folder list of FileSystem
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class FolderNotFoundException extends RuntimeException {

    public FolderNotFoundException() {
        super();
    }

    public FolderNotFoundException(String msg) {
        super(msg);
    }

}
