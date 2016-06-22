package ru.sondar.core.filesystem.exception;

/**
 * Throw when file included in configuration file but not exist in folder
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class MissFileInFolderException extends SomeTroubleWithFolderException {

    public MissFileInFolderException() {
        super();
    }

    public MissFileInFolderException(String msg) {
        super(msg);
    }

}
