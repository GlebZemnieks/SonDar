package ru.sondar.plugin.driver.exception;

/**
 * Throw when excel configuration file have missing attribute
 *
 * @author GlebZemnieks
 */
public class NoSheetAttributeException extends RuntimeException {

    public NoSheetAttributeException() {
        super();
    }

    public NoSheetAttributeException(String msg) {
        super(msg);
    }
}
