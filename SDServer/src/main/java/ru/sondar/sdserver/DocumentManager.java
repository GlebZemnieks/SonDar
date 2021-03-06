package ru.sondar.sdserver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filesystem.SonDarFileSystem;
import ru.sondar.core.parser.exception.ObjectStructureException;
import ru.sondar.documentmodel.SDDocument;

/**
 * Document manager.
 *
 * @author GlebZemnieks
 */
public class DocumentManager {

    /**
     * Global path to work directory
     */
    private final String globalFolder;
    /**
     * File system object to make actions with files
     */
    public SonDarFileSystem fileSystem;
    /**
     * Name of folder with document to export
     */
    private final String EXPORT_FOLDER_NAME = "ToDB";
    /**
     * Name of folder with document by import
     */
    private final String IMPORT_FOLDER_NAME = "FromDB";
    /**
     * Current list of document in export folder
     */
    public ArrayList<SDDocument> exportFileList = new ArrayList<>();
    /**
     * Current list of document in import folder
     */
    public ArrayList<SDDocument> importFileList = new ArrayList<>();

    public DocumentManager(FileModuleInterface fileModule, String globalFolder) {
        this.globalFolder = globalFolder;
        fileSystem = new SonDarFileSystem(globalFolder);
        fileSystem.addFolder(EXPORT_FOLDER_NAME);
        fileSystem.addFolder(IMPORT_FOLDER_NAME);
        fileSystem.init(fileModule);
        pullFileList();
    }

    public ArrayList<SDDocument> getDocumentListByPluginFilterFromImportList(String pluginUUID) {
        ArrayList<SDDocument> temp = new ArrayList<>();
        for (SDDocument document : importFileList) {
            if (document.getHeadPart().getPluginUUID().equals(UUID.fromString(pluginUUID))) {
                temp.add(document);
            }
        }
        return temp;
    }

    private void pullFileList() {
        this.exportFileList = pullFileFromDirectory(this.globalFolder + "\\" + EXPORT_FOLDER_NAME);
        this.importFileList = pullFileFromDirectory(this.globalFolder + "\\" + IMPORT_FOLDER_NAME);
    }

    private ArrayList<SDDocument> pullFileFromDirectory(String directoryName) {
        File directory = new File(directoryName);
        ArrayList<SDDocument> temp = new ArrayList<>();
        for (File file : directory.listFiles()) {
            if (file.isFile()) {
                SDDocument document = new SDDocument();
                try {
                    document.loadDocument(file.getAbsolutePath());
                } catch (SAXException | IOException | ParserConfigurationException | ObjectStructureException ex) {
                    continue;
                }
                temp.add(document);
            }
        }
        return temp;
    }

    @Override
    public String toString() {
        String temp = "Document manager : \n\tImportList\n";
        for (SDDocument document : importFileList) {
            temp += "\t\t" + document.getHeadPart().getDocumentName() + "\n";
        }
        temp += "\tExport list\n";
        for (SDDocument document : exportFileList) {
            temp += "\t\t" + document.getHeadPart().getDocumentName() + "\n";
        }
        return temp;
    }
}
