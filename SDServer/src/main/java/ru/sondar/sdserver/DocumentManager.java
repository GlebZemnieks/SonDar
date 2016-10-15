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
 *
 * @author GlebZemnieks
 */
public class DocumentManager {

    private final String globalFolder;
    public SonDarFileSystem fileSystem;
    private final String EXPORT_FOLDER_NAME = "ToDB";
    private final String IMPORT_FOLDER_NAME = "FromDB";

    public ArrayList<SDDocument> exportFileList = new ArrayList<>();
    public ArrayList<SDDocument> importFileList = new ArrayList<>();

    public DocumentManager(FileModuleInterface fileModule, String globalFolder) {
        this.globalFolder = globalFolder;
        fileSystem = new SonDarFileSystem(globalFolder);
        fileSystem.addFolder(EXPORT_FOLDER_NAME);
        fileSystem.addFolder(IMPORT_FOLDER_NAME);
        fileSystem.init(fileModule);
        pullFileList();
    }

    public void init(FileModuleInterface fileModule) {
        ArrayList<String> exportList = fileSystem.getFolderByName(EXPORT_FOLDER_NAME).getFileList();
        ArrayList<String> importList = fileSystem.getFolderByName(IMPORT_FOLDER_NAME).getFileList();
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
        File folderTO = new File(this.globalFolder + "\\" + EXPORT_FOLDER_NAME);
        File folderFROM = new File(this.globalFolder + "\\" + IMPORT_FOLDER_NAME);
        for (File file : folderTO.listFiles()) {
            if (file.isFile()) {
                SDDocument document = new SDDocument();
                try {
                    document.loadDocument(file.getAbsolutePath());
                } catch (SAXException | IOException | ParserConfigurationException | ObjectStructureException ex) {
                    continue;
                }
                this.importFileList.add(document);
            }
        }
        for (File file : folderFROM.listFiles()) {
            if (file.isFile()) {
                SDDocument document = new SDDocument();
                try {
                    document.loadDocument(file.getAbsolutePath());
                } catch (SAXException | IOException | ParserConfigurationException | ObjectStructureException ex) {
                    continue;
                }
                this.exportFileList.add(document);
            }
        }
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
