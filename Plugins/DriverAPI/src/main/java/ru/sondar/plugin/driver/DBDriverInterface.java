package ru.sondar.plugin.driver;

import java.util.ArrayList;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.plugin.driver.exception.DataBaseFileNotFoundException;
import ru.sondar.plugin.driver.exception.RowNotFoundException;

/**
 * Interface for creating DBDrivers
 *
 * @author GlebZemnieks
 */
public abstract class DBDriverInterface {

    /**
     * Configuration object
     */
    public DBDriverConfiguration configuration;

    /**
     * Action which unlocked for this driver
     */
    public ArrayList<DriverFunctionality> functionality = new ArrayList<>();

    /**
     * Open connection to data base and wait next command. If file not found
     * throw exception
     *
     * @throws ru.sondar.plugin.driver.exception.DataBaseFileNotFoundException
     */
    public abstract void connectToDB() throws DataBaseFileNotFoundException;

    /**
     * Find in data base row with key and return it. If key not found throw
     * exception
     *
     * @param key
     * @return
     * @throws ru.sondar.plugin.driver.exception.RowNotFoundException
     */
    public abstract DBRowInterface getRowByKey(Object key) throws RowNotFoundException;

    /**
     * Create new row in data base. If row with newKey already exist throw
     * exception
     *
     * @param newKey
     * @return
     */
    public abstract DBRowInterface createNewRowInDB(Object newKey);

    /**
     * Get list of value by key row.
     *
     * @return
     */
    public abstract ArrayList<Object> getKeyList();

    /**
     * Return configuration object of this driver
     *
     * @return
     */
    public abstract DBDriverConfiguration getConfiguration();

    /**
     * Close data base connection with transactions
     */
    public abstract void closeConnection();
}
