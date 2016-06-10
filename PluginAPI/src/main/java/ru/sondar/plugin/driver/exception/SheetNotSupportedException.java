package ru.sondar.plugin.driver.exception;

/**
 * Throw when code try to set active sheet which not supported in this plug-in
 *
 * @author GlebZemnieks
 */
public class SheetNotSupportedException extends RuntimeException {

    public SheetNotSupportedException() {
        super();
    }

    public SheetNotSupportedException(String msg) {
        super(msg);
    }
}
