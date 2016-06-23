package ru.sondar.documentmodel.objectmodel.exception;

/**
 * Main exception class for trouble with object model
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class ObjectStructureException extends Exception {

    public ObjectStructureException() {
        super();
    }

    public ObjectStructureException(String msg) {
        super(msg);
    }
}
