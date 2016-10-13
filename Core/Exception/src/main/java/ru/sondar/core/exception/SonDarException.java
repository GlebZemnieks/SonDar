package ru.sondar.core.exception;

/**
 *
 * @author GlebZemnieks
 */
public class SonDarException extends Exception {

    public Object[] params;

    public SonDarException() {
        super();
    }

    public SonDarException(String msg) {
        super(msg);
    }

    public SonDarException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public SonDarException(String msg, Object... params) {
        super(msg);
        this.params = params;
    }

}
