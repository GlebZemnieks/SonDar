package ru.sondar.documentmodel.objectmodel.exception;

/**
 * Throw when code try set name to object second time
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class ObjectAlreadyHaveNameException extends RuntimeException {

    public ObjectAlreadyHaveNameException() {
        super();
    }

    public ObjectAlreadyHaveNameException(String msg) {
        super(msg);
    }
}
