package ru.sondar.documentmodel.objectmodel.exception;

/**
 * Throw when code try set name to object second time
 *
 * @author GlebZemnieks
 */
public class ObjectAlreadyHaveNameException extends RuntimeException {

    public ObjectAlreadyHaveNameException(String msg) {
        super(msg);
    }
}
