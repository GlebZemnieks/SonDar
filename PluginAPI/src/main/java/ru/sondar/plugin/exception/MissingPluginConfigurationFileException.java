package ru.sondar.plugin.exception;

/**
 * Throw when plug-in configuration file is missing
 *
 * @author GlebZemnieks
 */
public class MissingPluginConfigurationFileException extends RuntimeException {

    public MissingPluginConfigurationFileException() {
        super();
    }

    public MissingPluginConfigurationFileException(String msg) {
        super(msg);
    }
}
