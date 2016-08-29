package ru.sondar.plugin.driver.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import ru.sondar.core.Config;
import ru.sondar.plugin.driver.DBCellInterface;

/**
 * Overwriting DBCellObject object to support HSSFCell object
 *
 * @author GlebZemnieks
 */
public class HSSFCellImplements implements DBCellInterface {

    /**
     * HSSFCell object
     */
    private HSSFCell cell;
    
    /**
     * Log tag
     */
    private String logTag = "HSSFCell";

    /**
     * Constructor
     *
     * @param cell
     */
    public HSSFCellImplements(HSSFCell cell) {
        this.cell = cell;
    }

    @Override
    public void setCellValue(Object value) {
        Config.Log(logTag, "Set value " + (String) value + " old value " + cell.getStringCellValue());
        cell.setCellValue((String) value);
    }

    @Override
    public Object getCellValue() {
        Config.Log(logTag, "Get value " + cell.getStringCellValue());
        return cell.getStringCellValue();
    }

}
