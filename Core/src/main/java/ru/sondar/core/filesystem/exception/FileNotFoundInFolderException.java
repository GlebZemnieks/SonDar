package ru.sondar.core.filesystem.exception;

/**
 * Throw when file not belonging Folder file list
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class FileNotFoundInFolderException extends RuntimeException {

    public FileNotFoundInFolderException() {
        super();
    }

    public FileNotFoundInFolderException(String msg) {
        super(msg);
    }
}
