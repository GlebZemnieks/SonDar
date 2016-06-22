package ru.sondar.core.filesystem.exception;

/**
 * Throw when in folder missing configuration file
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class FirstFolderUseException extends SomeTroubleWithFolderException {

    public FirstFolderUseException() {
        super();
    }

    public FirstFolderUseException(String msg) {
        super(msg);
    }

}
