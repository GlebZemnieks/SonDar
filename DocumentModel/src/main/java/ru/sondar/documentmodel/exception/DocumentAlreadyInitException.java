package ru.sondar.documentmodel.exception;

/**
 * Throw when code try to change part of document which already initialized
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class DocumentAlreadyInitException extends RuntimeException {

    public DocumentAlreadyInitException() {
        super();
    }

    public DocumentAlreadyInitException(String msg) {
        super(msg);
    }
}
