package ru.sondar.documentmodel.internalfunction.exception;

/**
 *
 * @author GlebZemnieks
 */
class IncorrectValueTypeException extends RuntimeException {

    public IncorrectValueTypeException() {
        super();
    }

    public IncorrectValueTypeException(String msg) {
        super(msg);
    }
}
