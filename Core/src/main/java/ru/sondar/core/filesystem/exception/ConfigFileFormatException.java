package ru.sondar.core.filesystem.exception;

/**
 * Throw when in configuration file exist some strange line
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class ConfigFileFormatException extends SomeTroubleWithFolderException {

    public ConfigFileFormatException() {
        super();
    }

    public ConfigFileFormatException(String msg) {
        super(msg);
    }
}
