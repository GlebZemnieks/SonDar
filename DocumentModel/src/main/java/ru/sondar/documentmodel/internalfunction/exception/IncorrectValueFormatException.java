package ru.sondar.documentmodel.internalfunction.exception;

/**
 * Throw when code set to object value with incorrect format
 *
 * @author GlebZemnieks
 */
public class IncorrectValueFormatException extends RuntimeException {

    public IncorrectValueFormatException() {
        super();
    }

    public IncorrectValueFormatException(String msg) {
        super(msg);
    }
}
