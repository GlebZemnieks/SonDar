package ru.sondar.core.filesystem;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.sondar.core.Config;
import ru.sondar.core.DOMParser;
import ru.sondar.core.filemodule.*;
import ru.sondar.core.filesystem.exception.*;

/**
 * Folder's configuration object. Supports configuration file in actual state
 * and do action with them.
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SonDarFolderConfig {

    /**
     * Configuration file name
     */
    private final String configFileName = "config.txt";

    /**
     * Global tag text
     */
    private final String configTag = "configFile";

    /**
     * Internal text field
     */
    private final String configFileTag = "fileName";
    /**
     * Tag for logging
     */
    private final String logTag = "FileSystemLog";

    /**
     * List of file on folder
     */
    public ArrayList<String> configFileList;

    /**
     * Constructor
     *
     * @param globalFolder
     * @param folderName
     * @throws ConfigFileFormatException
     */
    public SonDarFolderConfig(String globalFolder, String folderName) throws ConfigFileFormatException {
        this.configFileList = new ArrayList<>();
        this.getConfigList(globalFolder, folderName);
    }

    /**
     * Getter for file name field
     *
     * @return
     */
    public String getConfigFileName() {
        return this.configFileName;
    }

    /**
     * Reset file list
     */
    public void resetFileList() {
        this.configFileList = new ArrayList<>();
    }

    /**
     * Read configuration file and check it relevant Configurate actual file
     * List
     *
     * @param fileModule
     * @param globalFolder
     * @param folderName
     * @throws ConfigFileFormatException
     */
    private void getConfigList(String globalFolder, String folderName) throws ConfigFileFormatException {
        DOMParser parser;
        try {
            parser = new DOMParser(globalFolder + "/" + folderName + "/" + configFileName);
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            throw new ConfigFileFormatException("Trouble with parse \"" + this.configFileName + "\" file");
        }
        Element element = parser.getRootElement();
        NodeList tempList = element.getElementsByTagName(this.configFileTag);
        for (int count = 0; count < tempList.getLength(); count++) {
            Element tempElement = (Element) tempList.item(count);
            this.configFileList.add(((Element) tempList.item(count)).getTextContent());
        }
        Config.Log(logTag, "Final config list " + configFileList.toString());
    }

    /**
     * Add file in fileList. To write changes in configuration file calling
     * method Update
     *
     * @param fileModule
     * @param globalFolder
     * @param folderName
     * @param fileName
     */
    public void addFile(FileModuleInterface fileModule, String globalFolder, String folderName, String fileName) {
        if (this.configFileList.contains(fileName)) {
            return;
        }
        this.configFileList.add(fileName);
        this.update(fileModule, globalFolder, folderName);
    }

    /**
     *
     * Delete file from fileList. To write changes in configuration file calling
     * method Update
     *
     * @param fileModule
     * @param globalFolder
     * @param folderName
     * @param fileName
     */
    public void DeleteFile(FileModuleInterface fileModule, String globalFolder, String folderName, String fileName) {
        if (!this.configFileList.contains(fileName)) {
            throw new FileNotFoundInFolderException();
        }
        this.configFileList.remove(fileName);
        this.update(fileModule, globalFolder, folderName);
    }

    /**
     * Write changes to configuration file
     *
     * @param fileModule
     * @param globalFolder
     * @param folderName
     */
    public void update(FileModuleInterface fileModule, String globalFolder, String folderName) {
        FileModuleWriteThreadInterface writeThread = fileModule.getWriteThread(globalFolder + "/" + folderName + "/" + configFileName);
        writeThread.write("<" + configTag + ">\n");
        for (String configList1 : this.configFileList) {
            writeThread.write("<" + configFileTag + ">" + configList1 + "</" + configFileTag + ">\n");
        }
        writeThread.write("</" + configTag + ">");
        writeThread.close();
    }
}
