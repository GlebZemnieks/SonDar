package ru.sondar.plugin.exception;

import ru.sondar.core.exception.SonDarRuntimeException;

/**
 * Throw when plug-in configuration file is missing
 *
 * @author GlebZemnieks
 */
public class MissingPluginConfigurationFileException extends SonDarRuntimeException {

    public MissingPluginConfigurationFileException() {
        super();
    }

    public MissingPluginConfigurationFileException(String msg) {
        super(msg);
    }
}
