package ru.sondar.documentmodel.exception;

import ru.sondar.core.exception.SonDarRuntimeException;

/**
 * Throw when code try to save not initializing document
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class DocumentNotInitException extends SonDarRuntimeException {

    public DocumentNotInitException() {
        super();
    }

    public DocumentNotInitException(String msg) {
        super(msg);
    }
}
