package ru.sondar.plugin.driver;

/**
 * Cell interface
 *
 * @author GlebZemnieks
 */
public interface DBCellInterface {

    /**
     * Set value to cell
     *
     * @param value
     */
    void setCellValue(Object value);

    /**
     * Set value from cell
     *
     * @return
     */
    Object getCellValue();

}
