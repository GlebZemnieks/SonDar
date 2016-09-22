package ru.sondar.documentmodel.objectmodel.exception;

import ru.sondar.core.exception.SonDarRuntimeException;

/**
 *
 * @author GlebZemnieks
 */
public class BaseWithNameNotExistException extends SonDarRuntimeException{

    public BaseWithNameNotExistException(String msg, Object... params) {
        super(msg, params);
    }
    
}
