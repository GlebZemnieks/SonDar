package ru.sondar.documentmodel.exception;

/**
 * Throw when code try to save not initializing document
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class DocumentNotInitException extends RuntimeException {

    public DocumentNotInitException() {
        super();
    }

    public DocumentNotInitException(String msg) {
        super(msg);
    }
}
