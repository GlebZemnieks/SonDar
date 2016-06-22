package ru.sondar.core.filesystem.exception;

/**
 * Throw when user want to save file which not opened
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class FileNotOpenedException extends RuntimeException {

    public FileNotOpenedException() {
        super();
    }

    public FileNotOpenedException(String msg) {
        super(msg);
    }

}
