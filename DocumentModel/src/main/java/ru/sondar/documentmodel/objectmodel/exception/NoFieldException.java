package ru.sondar.documentmodel.objectmodel.exception;

/**
 * Throw when you can't find object's field
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class NoFieldException extends ObjectStructureException {

    public NoFieldException() {
        super();
    }

    public NoFieldException(String msg) {
        super(msg);
    }
}
