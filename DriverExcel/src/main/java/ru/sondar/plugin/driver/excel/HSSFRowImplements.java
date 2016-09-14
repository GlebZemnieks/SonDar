package ru.sondar.plugin.driver.excel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import ru.sondar.plugin.driver.DBRowInterface;
import ru.sondar.plugin.driver.DBCellInterface;

/**
 * Implementing DBRowInterface object to support HSSFRow object
 *
 * @author GlebZemnieks
 */
public class HSSFRowImplements implements DBRowInterface {

    /**
     * HSSFRow object
     */
    private HSSFRow row;

    /**
     * Constructor
     *
     * @param row
     */
    public HSSFRowImplements(HSSFRow row) {
        this.row = row;
    }

    @Override
    public DBCellInterface getCellById(Object key) {
        return new HSSFCellImplements(row.getCell((int) key));
    }

}
