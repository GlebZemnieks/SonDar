package ru.sondar.documentmodel.internalfunction.exception;

/**
 * Throw when object target id not exist in navigator list
 *
 * @author GlebZemnieks
 */
public class ObjectNotFountException extends RuntimeException {

    public ObjectNotFountException() {
        super();
    }

    public ObjectNotFountException(String msg) {
        super(msg);
    }
}
