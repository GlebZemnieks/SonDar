package ru.sondar.plugin;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import ru.sondar.documentmodel.dependencymodel.DependencyItem;
import ru.sondar.documentmodel.dependencymodel.SupportDependencyInterface;
import ru.sondar.documentmodel.SDDocument;
import ru.sondar.documentmodel.objectmodel.SDMainObject;
import ru.sondar.plugin.driver.DBDriverInterface;
import ru.sondar.plugin.driver.DBRowInterface;
import ru.sondar.plugin.driver.exception.DataBaseFileNotFoundException;
import ru.sondar.plugin.driver.exception.RowNotFoundException;

/**
 * Abstract class for customer plug-in
 *
 * @author GlebZemnieks
 */
public abstract class Plugin {

    /**
     * Plug-in configuration object
     */
    protected PluginConfigurator configurator;

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
    protected DriverManager manager;

    /**
     * Getter for supported drivers list
     *
     * @return
     */
    public Set<DriverName> getDriversList() {
        return this.manager.getDriversList();
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
        driver.connectToDB();
        SDDocument document = this.configurator.getExampleDocument();
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

}
