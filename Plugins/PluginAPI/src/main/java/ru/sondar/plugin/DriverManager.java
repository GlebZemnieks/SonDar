package ru.sondar.plugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.sondar.core.parser.DOMParser;
import ru.sondar.plugin.driver.DBDriverInterface;
import ru.sondar.plugin.driver.excel.ExcelDBDriver;
import ru.sondar.plugin.driver.exception.NoSheetAttributeException;
import ru.sondar.plugin.driver.word.WordDBDriver;

/**
 * Driver configuration object for plug-in. Read configuration file and create
 * all driver
 *
 * @author GlebZemnieks
 */
public class DriverManager {

    /**
     * Supported driver list
     */
    protected Map<DriverName, DBDriverInterface> supportedDrivers = new HashMap<>();

    /**
     * Get supported driver list
     *
     * @return
     */
    public Set<DriverName> getDriversList() {
        return this.supportedDrivers.keySet();
    }

    /**
     * Get driver from list by name
     *
     * @param name
     * @return
     */
    public DBDriverInterface getDriver(DriverName name) {
        return this.supportedDrivers.get(name);
    }

    /**
     * Constructor
     *
     * @param globalFolder
     * @param pluginConfigurationFileName
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public DriverManager(String globalFolder, String pluginConfigurationFileName) throws SAXException, IOException, ParserConfigurationException {
        NodeList nList = (new DOMParser(globalFolder + "\\" + pluginConfigurationFileName)).getNodeList("drivers", "driver");
        for (int i = 0; i < nList.getLength(); i++) {
            DriverName name;
            if (!"".equals((((Element) nList.item(i)).getAttribute("type")))) {
                name = DriverName.valueOf((((Element) nList.item(i)).getAttribute("type")));
            } else {
                throw new NoSheetAttributeException("Attribute \"type\" in drivers element");
            }
            if (name == null) {
                throw new InvalidPropertiesFormatException("Attribute \"type\" in drivers element have unknows value");
            }
            supportedDrivers.put(name, this.createDBDriverByType(name, globalFolder, ((Element) nList.item(i))));
        }
    }

    /**
     * Drivers factory
     *
     * @param name
     * @param folderName
     * @param element
     * @return
     */
    //Temp
    private DBDriverInterface createDBDriverByType(DriverName name, String folderName, Element element) {
        switch (name) {
            case ExcelDriver: {
                return new ExcelDBDriver(folderName, element);
            }
            case WordDriver: {
                return new WordDBDriver(folderName, element);
            }
        }
        return null;
    }

    //Temp
    public String getDriverNameByClass(DBDriverInterface driver) {
        if (driver.getClass() == ExcelDBDriver.class) {
            return "ExcelDriver";
        }
        if (driver.getClass() == WordDBDriver.class) {
            return "WordDriver";
        }
        return "";
    }
}
