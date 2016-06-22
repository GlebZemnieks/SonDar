package ru.sondar.core.filesystem.exception;

/**
 * Main exception to throws above.<br>
 * {@link ru.sondar.core.filesystem.exception.ConfigFileFormatException}<br>
 * {@link ru.sondar.core.filesystem.exception.FirstFolderUseException}<br>
 * {@link ru.sondar.core.filesystem.exception.MissFileInFolderException}<br>
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SomeTroubleWithFolderException extends Exception {

    public SomeTroubleWithFolderException() {
        super();
    }

    public SomeTroubleWithFolderException(String msg) {
        super(msg);
    }
}
