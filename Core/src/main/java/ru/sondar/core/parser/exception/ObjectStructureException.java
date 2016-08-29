package ru.sondar.core.parser.exception;

import ru.sondar.core.exception.SonDarException;

/**
 * Main exception class for trouble with object model
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class ObjectStructureException extends SonDarException {

    public ObjectStructureException() {
        super();
    }

    public ObjectStructureException(String msg) {
        super(msg);
    }
}
