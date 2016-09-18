package ru.sondar.core.filesystem;

import java.util.ArrayList;
import java.util.UUID;
import ru.sondar.core.filemodule.*;
import ru.sondar.core.filesystem.exception.*;

/**
 * File system object.
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SonDarFileSystem {

    /**
     * Domain folder name
     */
    private final String globalFolder;
    /**
     * Tag for logging
     */
    public String logTag = "FileSystemLog";

    /**
     * Constructor
     *
     * @param folderName
     */
    public SonDarFileSystem(String folderName) {
        this.globalFolder = folderName;
        this.folderList = new ArrayList<>();
    }

    /**
     * Folder list
     */
    private ArrayList<SonDarFolder> folderList;

    /**
     * Add new folder in file system
     *
     * @param folderName
     */
    public void addFolder(String folderName) {
        SonDarFolder folder = new SonDarFolder(globalFolder, folderName);
        folderList.add(folder);
        folder.isInSystem = true;
    }

    /**
     * Get folder object by name
     *
     * @param folderName
     * @return
     */
    public SonDarFolder getFolderByName(String folderName) {
        if (this.folderList.isEmpty()) {
            throw new FolderNotFoundException();
        }
        for (SonDarFolder folderList1 : this.folderList) {
            if (folderName == null ? folderList1.getFolderName() == null : folderList1.getFolderName().equals(folderName)) {
                return folderList1;
            }
        }
        throw new FolderNotFoundException();
    }

    /**
     * Initialization file system
     *
     * @param fileModule
     */
    public void init(FileModuleInterface fileModule) {
        for (SonDarFolder folderList1 : this.folderList) {
            try {
                folderList1.init(fileModule);
            } catch (SomeTroubleWithFolderException ex) {
            }
        }
    }

    /**
     * Add new file to internal folder
     *
     * @param fileModule
     * @param folderName
     * @param fileName
     * @return
     */
    public FileModuleWriteThreadInterface addFile(FileModuleInterface fileModule, String folderName, String fileName) {
        SonDarFolder temp = this.getFolderByName(folderName);
        return temp.addFile(fileModule, fileName);
    }

    /**
     * Delete file from internal folder
     *
     * @param fileModule
     * @param folderName
     * @param fileName
     */
    public void deleteFile(FileModuleInterface fileModule, String folderName, String fileName) {
        SonDarFolder temp = this.getFolderByName(folderName);
        temp.deleteFile(fileModule, fileName);
    }

    /**
     * Move file from one internal folder to another
     *
     * @param fileModule
     * @param folderFrom
     * @param oldFileName
     * @param folderIn
     * @param newFileName
     */
    public void moveFile(FileModuleInterface fileModule,
            String folderFrom, String oldFileName,
            String folderIn, String newFileName) {
        SonDarFolder folder1 = this.getFolderByName(folderFrom);
        SonDarFolder folder2 = this.getFolderByName(folderIn);
        FileModuleReadThreadInterface Old = folder1.getFile(fileModule, oldFileName);
        FileModuleWriteThreadInterface New = folder2.addFile(fileModule, newFileName);
        String tempString = Old.read();
        while (tempString != null) {
            New.write(tempString + "\n");
            tempString = Old.read();
        }
        Old.close();
        New.close();
        folder1.deleteFile(fileModule, oldFileName);

    }

    /**
     * Copy file from one internal folder to another
     *
     * @param fileModule
     * @param folderFrom
     * @param oldFileName
     * @param folderIn
     * @param newFileName
     */
    public void copyFile(FileModuleInterface fileModule,
            String folderFrom, String oldFileName,
            String folderIn, String newFileName) {
        SonDarFolder folder1 = this.getFolderByName(folderFrom);
        SonDarFolder folder2 = this.getFolderByName(folderIn);
        FileModuleReadThreadInterface Old = folder1.getFile(fileModule, oldFileName);
        FileModuleWriteThreadInterface New = folder2.addFile(fileModule, newFileName);
        String tempString = Old.read();
        while (tempString != null) {
            New.write(tempString + "\n");
            tempString = Old.read();
        }
        Old.close();
        New.close();
    }

    /**
     * UUID generation
     *
     * @return
     */
    public UUID getUUID() {
        return UUID.randomUUID();
    }
}
