package ru.sondar.plugin.driver.exception;

/**
 * Throw when row with current keyId not exist in data base file
 *
 * @author GlebZemnieks
 */
public class RowNotFoundException extends Exception {

    public RowNotFoundException() {
        super();
    }

    public RowNotFoundException(String msg) {
        super(msg);
    }
}
