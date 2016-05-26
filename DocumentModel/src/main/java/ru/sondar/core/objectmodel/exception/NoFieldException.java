package ru.sondar.core.objectmodel.exception;

/**
 * Throw when you can't find object's field
 *
 * @author GlebZemnieks
 */
public class NoFieldException extends ObjectStructureException {

    public NoFieldException(String msg) {
        super(msg);
    }
}
