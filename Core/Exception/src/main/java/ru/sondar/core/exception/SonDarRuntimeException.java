package ru.sondar.core.exception;

/**
 *
 * @author GlebZemnieks
 */
public class SonDarRuntimeException extends RuntimeException {

    public Object[] params;

    public SonDarRuntimeException() {
        super();
    }

    public SonDarRuntimeException(String msg) {
        super(msg);
    }

    public SonDarRuntimeException(String msg, Exception cause) {
        super(msg, cause);
    }
    
    public SonDarRuntimeException(String msg, Object... params){
        super(msg);
        this.params = params;
    }
    
    public SonDarRuntimeException(String msg, Exception cause, Object... params){
        super(msg, cause);
        this.params = params;
    }
}
