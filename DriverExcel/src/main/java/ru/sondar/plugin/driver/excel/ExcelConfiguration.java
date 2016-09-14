package ru.sondar.plugin.driver.excel;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.sondar.plugin.driver.DBDriverConfiguration;
import ru.sondar.plugin.driver.exception.*;

/**
 *
 * @author GlebZemnieks
 */
public class ExcelConfiguration implements DBDriverConfiguration {

    /**
     * Data base file name
     */
    public String fileName;

    /**
     * Supported sheet list
     */
    ArrayList<SheetConfiguration> sheetConfigurations;

    /**
     * Constructor
     *
     * @param element
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public ExcelConfiguration(Element element) throws SAXException, IOException, ParserConfigurationException {
        this.sheetConfigurations = new ArrayList<>();
        if (!"".equals(element.getAttribute("fileName"))) {
            this.fileName = (element.getAttribute("fileName"));
        } else {
            throw new NoSheetAttributeException("Attribute \"fileName\" in root element");
        }
        NodeList nList = element.getElementsByTagName("Sheet");
        for (int i = 0; i < nList.getLength(); i++) {
            sheetConfigurations.add(new SheetConfiguration((Element) nList.item(i)));
        }
    }

    /**
     * Check sheet existence. If sheet by id exist in sheet list return true
     * else return false
     *
     * @param id
     * @return
     */
    public boolean isSheetExist(int id) {
        for (int i = 0; i < sheetConfigurations.size(); i++) {
            if (id == sheetConfigurations.get(i).sheetId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get key cell id in sheet by id
     *
     * @param sheetId
     * @return
     */
    @Override
    public int getKeyCellId(int sheetId) {
        for (int i = 0; i < sheetConfigurations.size(); i++) {
            if (sheetId == sheetConfigurations.get(i).sheetId) {
                return sheetConfigurations.get(i).KeyCellId;
            }
        }
        throw new SheetNotSupportedException("Try to get key in sheet with id \"" + sheetId + "\" with configuration list " + this.toString());
    }

    @Override
    public String toString() {
        String temp = "ExcelConfiguration : \n";
        for (int i = 0; i < this.sheetConfigurations.size(); i++) {
            temp += this.sheetConfigurations.get(i).toString();
        }
        return temp;
    }
}

/**
 * Object of sheet configuration
 *
 * @author GlebZemnieks
 */
class SheetConfiguration {

    /**
     * Number of sheet
     */
    public int sheetId = -1;

    /**
     * Number of key cell
     */
    public int KeyCellId = -1;

    /**
     * Id of max cell in sheet
     */
    public int maxCell = -1;

    /**
     * Constructor with parsing attribute by element
     *
     * @param element
     */
    public SheetConfiguration(Element element) {
        if (!"".equals(element.getAttribute("id"))) {
            this.sheetId = (Integer.valueOf(element.getAttribute("id")));
        } else {
            throw new NoSheetAttributeException("Attribute \"id\"");
        }
        if (!"".equals(element.getAttribute("keyCell"))) {
            this.KeyCellId = (Integer.valueOf(element.getAttribute("keyCell")));
        } else {
            throw new NoSheetAttributeException("Attribute \"keyCell\"");
        }
        if (!"".equals(element.getAttribute("maxCell"))) {
            this.maxCell = (Integer.valueOf(element.getAttribute("maxCell")));
        } else {
            throw new NoSheetAttributeException("Attribute \"maxCell\"");
        }
    }

    @Override
    public String toString() {
        return "Sheet id " + this.sheetId + " with key " + this.KeyCellId + " with max " + this.maxCell + "\n";
    }

}
