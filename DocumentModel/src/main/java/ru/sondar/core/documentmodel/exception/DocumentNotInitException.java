package ru.sondar.core.documentmodel.exception;

/**
 * Throw when code try to save not initializing document
 *
 * @author GlebZemnieks
 */
public class DocumentNotInitException extends RuntimeException {

    public DocumentNotInitException() {
        super();
    }

    public DocumentNotInitException(String msg) {
        super(msg);
    }
}
