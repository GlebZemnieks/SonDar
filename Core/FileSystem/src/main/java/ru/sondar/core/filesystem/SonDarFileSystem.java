package ru.sondar.core.filesystem;

import ru.sondar.core.filesystem.filechecker.FileCheckerInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import ru.sondar.core.exception.SonDarRuntimeException;
import ru.sondar.core.filemodule.*;
import ru.sondar.core.filesystem.exception.*;
import ru.sondar.core.filesystem.filechecker.EmptyChecker;
import ru.sondar.core.logger.Logger;

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
        this.folderMap = new HashMap<>();
    }

    /**
     * Folder list
     */
    private Map<SonDarFolder, FileCheckerInterface> folderMap;

    /**
     * Add new folder in file system
     *
     * @param folderName
     */
    public void addFolder(String folderName) {
        this.addFolder(folderName, new EmptyChecker());
    }

    /**
     * Add new folder in file system
     *
     * @param folderName
     * @param checker
     */
    public void addFolder(String folderName, FileCheckerInterface checker) {
        if (folderName == null) {
            throw new SonDarRuntimeException("addFolder :: Folder name is \"null\". Denaid!");
        }
        SonDarFolder folder = new SonDarFolder(globalFolder, folderName);
        folderMap.put(folder, checker);
        folder.isInSystem = true;
    }

    /**
     * Get folder object by name
     *
     * @param folderName
     * @return
     */
    public SonDarFolder getFolderByName(String folderName) {
        if (this.folderMap.isEmpty()) {
            throw new FolderNotFoundException();
        }
        if (folderName == null) {
            throw new SonDarRuntimeException("getFolderByName :: Folder name is \"null\". Denaid!");
        }
        for (SonDarFolder folderList1 : this.folderMap.keySet()) {
            if (folderList1.getFolderName().equals(folderName)) {
                return folderList1;
            }
        }
        throw new FolderNotFoundException();
    }

    public void init(FileModuleInterface fileModule) {
        this.init(fileModule, 0);
    }

    /**
     * Initialization file system
     *
     * @param fileModule
     * @param retryCount
     */
    public void init(FileModuleInterface fileModule, int retryCount) {
        for (SonDarFolder folderList1 : this.folderMap.keySet()) {
            try {
                folderList1.init(fileModule, this.folderMap.get(folderList1), retryCount);
            } catch (SomeTroubleWithFolderException ex) {
                Logger.Log(logTag, "Some trouble with folder \"" + folderList1.getFolderName() + "\"", ex);
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
        //???? O-o ???? 
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
