package ru.sondar.plugin.driver.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
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
     * Constructor
     *
     * @param cell
     */
    public HSSFCellImplements(HSSFCell cell) {
        this.cell = cell;
    }

    @Override
    public void setCellValue(Object value) {
        System.out.println("set value " + (String) value + " old value " + cell.getStringCellValue());
        cell.setCellValue((String) value);
    }

    @Override
    public Object getCellValue() {
        System.out.println("Get value " + cell.getStringCellValue());
        return cell.getStringCellValue();
    }

}
