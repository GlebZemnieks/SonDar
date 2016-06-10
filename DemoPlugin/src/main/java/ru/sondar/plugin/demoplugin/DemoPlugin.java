package ru.sondar.plugin.demoplugin;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import ru.sondar.core.dependencymodel.DependencyItem;
import ru.sondar.core.dependencymodel.SupportDependencyInterface;
import ru.sondar.core.documentmodel.SDDocument;
import ru.sondar.core.objectmodel.SDMainObject;
import ru.sondar.core.objectmodel.exception.ObjectStructureException;
import ru.sondar.plugin.DriverManager;
import ru.sondar.plugin.DriverName;
import ru.sondar.plugin.Plugin;
import ru.sondar.plugin.driver.DBDriverInterface;
import ru.sondar.plugin.driver.DBRowInterface;
import ru.sondar.plugin.driver.excel.ExcelDBDriver;
import ru.sondar.plugin.driver.exception.DataBaseFileNotFoundException;
import ru.sondar.plugin.driver.exception.RowNotFoundException;
import ru.sondar.plugin.exception.MissingPluginConfigurationFileException;

/**
 * Plug-in for example
 *
 * @author GlebZemnieks
 */
public class DemoPlugin extends Plugin {

    public static void main(String... args) {
        DemoPlugin demo = new DemoPlugin("E:\\Share");
        demo.configurator.generateExampleFile();
        System.out.println(demo.manager.getDriversList());
        //SDDocument document = demo.configurator.getExampleDocument();
        SDDocument[] document = new SDDocument[4];
        for (int i = 0; i < document.length; i++) {
            document[i] = new SDDocument();
            try {
                document[i].loadDocument("E:\\Share\\Demo\\test2\\Demo" + (i + 1) + ".xml");
            } catch (SAXException | IOException | ParserConfigurationException | ObjectStructureException ex) {
                Logger.getLogger(DemoPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
            demo.exportDocumentToDB(DriverName.ExcelDriver, document[i]);
        }
    }

    public DemoPlugin(String globalFolder) {
        try {
            this.configurator = new DemoPluginConfigurator(globalFolder);
            this.manager = new DriverManager(globalFolder + "\\" + this.configurator.localFolderName, this.configurator.pluginConfigurationFileName);
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            throw new MissingPluginConfigurationFileException();
        }
    }

    @Override
    public SDDocument importDocumentFromDB(DriverName name, String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportDocumentToDB(DriverName name, SDDocument document) {
        DBDriverInterface driver = this.manager.getDriver(name);
        try {
            driver.connectToDB();
        } catch (DataBaseFileNotFoundException ex) {
            Logger.getLogger(DemoPlugin.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //!!! 
            ((ExcelDBDriver) driver).setActiveSheet(0);
            DBRowInterface row = driver.getRowByKey(document.getDocumentUUID().toString());
            Iterator<DependencyItem> iterator = document.getDependencyPart().iterator();
            while (iterator.hasNext()) {
                DependencyItem item = iterator.next();
                SDMainObject object = document.getSequense().getXMLObjectByName(item.objectName);
                if (object instanceof SupportDependencyInterface) {
                    System.out.println("item.cellId : " + item.cellId);
                    System.out.println("row.getCellById(item.cellId) value : " + row.getCellById(item.cellId).getCellValue());
                    row.getCellById(item.cellId).setCellValue(((SupportDependencyInterface) object).getValue());
                    System.out.println("row.getCellById(item.cellId) value : " + row.getCellById(item.cellId).getCellValue());
                }
            }
        } catch (RowNotFoundException ex) {
            Logger.getLogger(DemoPlugin.class.getName()).log(Level.SEVERE, null, ex);
        }
        driver.closeConnection();
    }

}
