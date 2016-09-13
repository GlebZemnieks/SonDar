package ru.sondar.core.filesystem.exception;

import ru.sondar.core.exception.SonDarException;

/**
 * Main exception to throws above.<br>
 * {@link ru.sondar.core.filesystem.exception.ConfigFileFormatException}<br>
 * {@link ru.sondar.core.filesystem.exception.FirstFolderUseException}<br>
 * {@link ru.sondar.core.filesystem.exception.MissFileInFolderException}<br>
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SomeTroubleWithFolderException extends SonDarException {

    public SomeTroubleWithFolderException() {
        super();
    }

    public SomeTroubleWithFolderException(String msg) {
        super(msg);
    }
}
