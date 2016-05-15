package ru.sondar.core.documentmodel.exception;

/**
 * Throw when code try to change part of document which already initialized
 *
 * @author GlebZemnieks
 */
public class DocumentAlreadyInitException extends RuntimeException {

    public DocumentAlreadyInitException(String msg) {
        super(msg);
    }
}
