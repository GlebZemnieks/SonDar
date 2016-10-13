package ru.sondar.plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.documentmodel.dependencymodel.*;
import ru.sondar.documentmodel.SDDocument;
import ru.sondar.documentmodel.objectmodel.SDMainObject;
import ru.sondar.core.parser.exception.NoFieldException;
import ru.sondar.plugin.driver.*;
import ru.sondar.plugin.driver.exception.*;

/**
 * Abstract class for customer plug-in
 *
 * @author GlebZemnieks
 */
public abstract class Plugin {

    public String folderNameTag = "folderName";

    public String versionTag = "version";

    public Plugin(Element plugin) throws NoFieldException, SAXException, IOException, ParserConfigurationException {
        NodeList list = plugin.getElementsByTagName(folderNameTag);
        if (list.item(0) == null) {
            throw new NoFieldException("Missing \"folderName\" field");
        }
        list = plugin.getElementsByTagName(versionTag);
        if (list.item(0) == null) {
            throw new NoFieldException("Missing \"version\" field");
        }
        this.configurator = new PluginConfigurator((new File("")).getAbsolutePath(), plugin.getElementsByTagName(folderNameTag).item(0).getTextContent());
        this.manager = new DriverManager(this.configurator.globalPluginFolder + "\\" + this.configurator.localFolderName, this.configurator.pluginConfigurationFileName);
    }

    /**
     * Plug-in configuration object
     */
    public PluginConfigurator configurator;

    /**
     * Getter for plug-in UUID fields
     *
     * @return
     */
    public UUID getPluginUUID() {
        return this.configurator.pluginUUID;
    }

    /**
     * Driver manager object
     */
    public DriverManager manager;

    /**
     * Getter for supported drivers list
     *
     * @return
     */
    public ArrayList<DriverName> getDriversList() {
        ArrayList<DriverName> temp = new ArrayList<>();
        temp.addAll(this.manager.getDriversList());
        return temp;
    }

    public ArrayList<Object> getKeyList(DriverName driver) throws DataBaseFileNotFoundException {
        DBDriverInterface driverObject = this.manager.getDriver(driver);
        driverObject.connectToDB();
        ArrayList<Object> temp = this.manager.getDriver(driver).getKeyList();
        driverObject.closeConnection();
        return temp;
    }

    /**
     * Return document object from DB by string key
     *
     * @param name
     * @param key
     * @return
     * @throws ru.sondar.plugin.driver.exception.DataBaseFileNotFoundException
     * @throws ru.sondar.plugin.driver.exception.RowNotFoundException
     */
    public SDDocument importDocumentFromDB(DriverName name, String key) throws DataBaseFileNotFoundException, RowNotFoundException {
        DBDriverInterface driver = this.manager.getDriver(name);
        if (!driver.functionality.contains(DriverFunctionality.Import)) {
            throw new FunctionalityNotSupportedException("Try to import by \"" + name + "\" driver. Functionality not supported for with driver");
        }
        driver.connectToDB();
        SDDocument document = this.getExampleDocument();
        DBRowInterface row = driver.getRowByKey(key);
        document.getHeadPart().setUUID(UUID.fromString(key));
        Iterator<DependencyItem> iterator = document.getDependencyPart().iterator();
        while (iterator.hasNext()) {
            DependencyItem item = iterator.next();
            SDMainObject object = document.getSequense().getXMLObjectByName(item.objectName);
            if (object instanceof SupportDependencyInterface) {
                ((SupportDependencyInterface) object).setValue(row.getCellById(item.cellId).getCellValue());
            }
        }
        this.cutsomImportParameters(name, key);
        driver.closeConnection();
        return document;
    }

    /**
     * Customers actions in export process
     *
     * @param name
     * @param key
     * @return
     */
    public abstract SDDocument cutsomImportParameters(DriverName name, String key);

    /**
     * Add document to DB or replace current line if document already have
     * protection on DB
     *
     * @param name
     * @param document
     * @throws ru.sondar.plugin.driver.exception.DataBaseFileNotFoundException
     * @throws ru.sondar.plugin.driver.exception.RowNotFoundException
     */
    public void exportDocumentToDB(DriverName name, SDDocument document) throws DataBaseFileNotFoundException, RowNotFoundException {
        DBDriverInterface driver = this.manager.getDriver(name);
        if (!driver.functionality.contains(DriverFunctionality.Export)) {
            throw new FunctionalityNotSupportedException("Try to export by \"" + name + "\" driver. Functionality not supported for with driver");
        }
        driver.connectToDB();
        DBRowInterface row = driver.getRowByKey(document.getHeadPart().getUUID().toString());
        Iterator<DependencyItem> iterator = document.getDependencyPart().iterator();
        while (iterator.hasNext()) {
            DependencyItem item = iterator.next();
            SDMainObject object = document.getSequense().getXMLObjectByName(item.objectName);
            if (object instanceof SupportDependencyInterface) {
                row.getCellById(item.cellId).setCellValue(((SupportDependencyInterface) object).getValue());
            }
        }
        this.cutsomExportParameters(name, document);
        driver.closeConnection();
    }

    /**
     * Customers actions in export process
     *
     * @param name
     * @param document
     */
    protected abstract void cutsomExportParameters(DriverName name, SDDocument document);

    /**
     * Generate default file in plug-in folder
     *
     * @param fileModule
     */
    public void generateExampleFile(FileModuleInterface fileModule) {
        SDDocument document = this.getExampleDocument();
        FileModuleWriteThreadInterface fileThread = fileModule.getWriteThread(this.configurator.globalPluginFolder + "\\" + this.configurator.localFolderName + "\\Demo.xml");
        document.saveDocument(fileThread);
        fileThread.close();
    }

    /**
     * Abstract method for building current document model
     *
     * @return
     */
    public abstract SDDocument getExampleDocument();

    @Override
    public String toString() {
        return "Global folder : \"" + this.configurator.globalPluginFolder + "\\" + this.configurator.localFolderName + "\"\n"
                + "pluginList : " + this.manager.supportedDrivers.toString();
    }
}
