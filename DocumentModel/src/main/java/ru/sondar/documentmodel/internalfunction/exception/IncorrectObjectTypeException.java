package ru.sondar.documentmodel.internalfunction.exception;

/**
 * Throw when target object not implement target interface
 *
 * @author GlebZemnieks
 */
public class IncorrectObjectTypeException extends RuntimeException {

    public IncorrectObjectTypeException() {
        super();
    }

    public IncorrectObjectTypeException(String msg) {
        super(msg);
    }
}
