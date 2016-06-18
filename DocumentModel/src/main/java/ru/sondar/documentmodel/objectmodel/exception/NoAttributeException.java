package ru.sondar.documentmodel.objectmodel.exception;

/**
 * Throw if parsing attribute value is null
 *
 * @author GlebZemnieks
 */
public class NoAttributeException extends ObjectStructureException {

    public NoAttributeException(String msg) {
        super(msg);
    }
}