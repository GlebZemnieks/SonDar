package ru.sondar.core.filesystem.exception;

import ru.sondar.core.exception.SonDarRuntimeException;

/**
 * Throw when file not belonging Folder file list
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class FileNotFoundInFolderException extends SonDarRuntimeException {

    public FileNotFoundInFolderException() {
        super();
    }

    public FileNotFoundInFolderException(String msg) {
        super(msg);
    }
}
