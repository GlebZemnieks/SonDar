package ru.sondar.documentmodel.objectmodel.exception;

/**
 * Throw if parsing attribute value is null
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class NoAttributeException extends ObjectStructureException {

    public NoAttributeException() {
        super();
    }

    public NoAttributeException(String msg) {
        super(msg);
    }
}
