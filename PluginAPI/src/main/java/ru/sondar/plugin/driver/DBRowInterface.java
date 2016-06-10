package ru.sondar.plugin.driver;

/**
 * Interface for Row object in DB
 *
 * @author GlebZemnieks
 */
public interface DBRowInterface {

    /**
     * Return cell object by key. If cell no found throw exception
     *
     * @param key
     * @return
     */
    DBCellInterface getCellById(Object key);
}
