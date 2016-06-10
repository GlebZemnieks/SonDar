package ru.sondar.plugin;

import java.util.Set;
import java.util.UUID;
import ru.sondar.core.documentmodel.SDDocument;

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
     */
    public abstract SDDocument importDocumentFromDB(DriverName name, String key);

    /**
     * Add document to DB or replace current line if document already have
     * protection on DB
     *
     * @param name
     * @param document
     */
    public abstract void exportDocumentToDB(DriverName name, SDDocument document);

}
