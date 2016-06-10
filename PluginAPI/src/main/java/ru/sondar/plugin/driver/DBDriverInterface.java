package ru.sondar.plugin.driver;

import ru.sondar.plugin.driver.exception.DataBaseFileNotFoundException;
import ru.sondar.plugin.driver.exception.RowNotFoundException;

/**
 * Interface for creating DBDrivers
 *
 * @author GlebZemnieks
 */
public interface DBDriverInterface {

    /**
     * Open connection to data base and wait next command. If file not found
     * throw exception
     *
     * @throws ru.sondar.plugin.driver.exception.DataBaseFileNotFoundException
     */
    void connectToDB() throws DataBaseFileNotFoundException;

    /**
     * Find in data base row with key and return it. If key not found throw
     * exception
     *
     * @param key
     * @return
     * @throws ru.sondar.plugin.driver.exception.RowNotFoundException
     */
    DBRowInterface getRowByKey(Object key) throws RowNotFoundException;

    /**
     * Create new row in data base. If row with newKey already exist throw
     * exception
     *
     * @param newKey
     * @return
     */
    DBRowInterface createNewRowInDB(Object newKey);

    /**
     * Close data base connection with transactions
     */
    void closeConnection();
}
