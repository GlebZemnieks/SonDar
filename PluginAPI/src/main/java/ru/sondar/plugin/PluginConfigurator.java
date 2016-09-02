package ru.sondar.plugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import ru.sondar.core.DOMParser;

/**
 * Plug-in configuration abstract class
 *
 * @author GlebZemnieks
 */
public class PluginConfigurator {

    /**
     * Plug-in folder name
     */
    public String localFolderName;

    /**
     * Relative path to configuration file. TODO Remove default value to
     * configuration.
     */
    public String pluginConfigurationFileName = "\\plugin.configuration";

    /**
     * Plug-in UUID for identification
     */
    protected UUID pluginUUID;
    
    public UUID getPluginUUID(){
        return this.pluginUUID;
    }
    
    /**
     * Plug-in name to show Users
     */
    public String pluginName;

    /**
     * Global folder path
     */
    public String globalPluginFolder = null;

    public PluginConfigurator(String globalFolder, String localFoldeName) throws SAXException, IOException, ParserConfigurationException {
        this.globalPluginFolder = globalFolder;
        this.localFolderName = localFoldeName;
        this.init();
    }

    /**
     * Properties initialization
     *
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    private void init() throws SAXException, IOException, ParserConfigurationException {
        File folder = new File(this.globalPluginFolder);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File localFolder = new File(this.globalPluginFolder + "\\" + this.localFolderName);
        if (!localFolder.exists()) {
            localFolder.mkdir();
        }
        Element properties = (Element)((new DOMParser(globalPluginFolder + "\\" + localFolderName + "\\" + pluginConfigurationFileName)).getRootElement().getElementsByTagName("properties").item(0));
        this.pluginUUID = UUID.fromString((properties.getElementsByTagName("PluginUUID").item(0)).getTextContent());
    }
}
