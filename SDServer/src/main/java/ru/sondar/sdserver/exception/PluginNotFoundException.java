package ru.sondar.sdserver.exception;

import ru.sondar.core.exception.SonDarRuntimeException;

/**
 *
 * @author GlebZemnieks
 */
public class PluginNotFoundException extends SonDarRuntimeException {

    public PluginNotFoundException() {
        super();
    }

    public PluginNotFoundException(String msg) {
        super(msg);
    }
}
