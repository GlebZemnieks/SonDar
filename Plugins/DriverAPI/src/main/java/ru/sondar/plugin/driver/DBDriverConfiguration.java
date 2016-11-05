package ru.sondar.plugin.driver;

/**
 *
 * @author GlebZemnieks
 */
public abstract class DBDriverConfiguration {

    public String fileName;

    public String driverName;

    public abstract int getKeyCellId(int sheetId);
}
