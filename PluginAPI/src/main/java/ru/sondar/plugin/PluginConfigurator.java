package ru.sondar.plugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.sondar.core.DOMParser;
import ru.sondar.documentmodel.SDDocument;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;

/**
 * Plug-in configuration abstract class
 *
 * @author GlebZemnieks
 */
public abstract class PluginConfigurator {

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

    /**
     * Global folder path
     */
    private String globalPluginFolder = null;

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
        Element element = (new DOMParser(globalPluginFolder + "\\" + localFolderName + "\\" + pluginConfigurationFileName)).getRootElement();
        NodeList nList = element.getElementsByTagName("properties");
        this.pluginUUID = UUID.fromString(((Element) (((Element) nList.item(0)).getElementsByTagName("PluginUUID").item(0))).getTextContent());
    }

    /**
     * Generate default file in plug-in folder
     */
    public void generateExampleFile() {
        SDDocument document = this.getExampleDocument();
        FileModuleWriteThread fileThread = new FileModuleWriteThread(this.globalPluginFolder + "\\" + this.localFolderName + "\\Demo.xml", false);
        document.saveDocument(fileThread);
        fileThread.close();
    }

    /**
     * Abstract method for building current document model
     *
     * @return
     */
    public abstract SDDocument getExampleDocument();
}
