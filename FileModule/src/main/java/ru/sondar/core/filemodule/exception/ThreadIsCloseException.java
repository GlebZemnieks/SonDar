package ru.sondar.core.filemodule.exception;

import ru.sondar.core.exception.SonDarRuntimeException;

/**
 * Throw when try to call some method on read or write thread, which closed
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class ThreadIsCloseException extends SonDarRuntimeException {

    public ThreadIsCloseException() {
        super();
    }

    public ThreadIsCloseException(String msg) {
        super(msg);
    }
}
