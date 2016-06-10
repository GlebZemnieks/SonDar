package ru.sondar.plugin.driver.exception;

/**
 * Throw when code try to open not exist file
 *
 * @author GlebZemnieks
 */
public class DataBaseFileNotFoundException extends Exception {

    public DataBaseFileNotFoundException() {
        super();
    }

    public DataBaseFileNotFoundException(String msg) {
        super(msg);
    }
}
