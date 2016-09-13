package ru.sondar.core.filesystem;

import java.util.ArrayList;
import ru.sondar.core.filemodule.*;
import ru.sondar.core.filemodule.exception.*;
import ru.sondar.core.filesystem.exception.*;

/**
 * Folder object.
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SonDarFolder {

    /**
     * Global folder name
     */
    private String globalFolder;

    /**
     * Setter for global folder field
     *
     * @param folderName
     */
    public void setGlobalFolder(String folderName) {
        this.globalFolder = folderName;
    }
    /**
     * This folder name
     */
    private final String folderName;

    /**
     * Getter for this folder name field
     *
     * @return
     */
    public String getFolderName() {
        return this.folderName;
    }

    /**
     * Tag for logging
     */
    private final String logTag = "FileSystemLog";

    /**
     * Constructor
     *
     * @param globalFolder
     * @param folderName
     */
    public SonDarFolder(String globalFolder, String folderName) {
        this.folderName = folderName;
        this.globalFolder = globalFolder;
    }

    /**
     * Folder status
     */
    private SonDarFolderState isInit = SonDarFolderState.Initialization;

    /**
     * Getter for state field
     *
     * @return
     */
    public SonDarFolderState getState() {
        return this.isInit;
    }
    /**
     * Is this folder already added to file system
     */
    public boolean isInSystem = false;

    /**
     * Configuration object
     */
    public SonDarFolderConfig config;

    /**
     * Getter for file list
     *
     * @return
     */
    public ArrayList<String> getFileList() {
        if (this.isInit != SonDarFolderState.None) {
            throw new FolderNotReadyException();
        }
        return this.config.configFileList;
    }

    public boolean isEmpty() {
        return (this.config.isEmpty());
    }
    
    /**
     * Return global file name if it exist in this folder
     *
     * @param fileName
     * @return
     */
    public String getGlobalFileName(String fileName) {
        if (this.isInit != SonDarFolderState.None) {
            throw new FolderNotReadyException();
        }
        boolean isInFolder = false;
        for (String temp : this.config.configFileList) {
            if (fileName.equals(temp)) {
                isInFolder = true;
            }
        }
        if (!isInFolder) {
            throw new FileNotFoundInFolderException();
        }
        return this.globalFolder + "/" + this.folderName + "/" + fileName;
    }

    /**
     * Read configuration file and check that folder relevant
     *
     * @param fileModule
     * @throws SomeTroubleWithFolderException
     */
    public void init(FileModuleInterface fileModule) throws SomeTroubleWithFolderException {
        if (!isInSystem) {
            throw new FolderObjectNotInSystemException();
        }
        //check config file
        try {
            config = new SonDarFolderConfig(globalFolder, folderName);
        } catch (SonDarFileNotFoundException error) {
            SomeTroubleWithFolderException next = new FirstFolderUseException();
            next.addSuppressed(error);
            this.isInit = SonDarFolderState.ReduildPending;
            throw next;
        } catch (ConfigFileFormatException error) {
            SomeTroubleWithFolderException next = new SomeTroubleWithFolderException();
            next.addSuppressed(error);
            this.isInit = SonDarFolderState.ReduildPending;
            throw next;
        }
        boolean allRight = true;
        MissFileInFolderException missFileError = new MissFileInFolderException();
        for (String fileName : config.configFileList) {
            try {
                fileModule.getReadThread(globalFolder + "/" + folderName + "/" + fileName);
            } catch (SonDarFileNotFoundException error) {
                allRight = false;
                missFileError.addSuppressed(error);
            }
        }
        if (!allRight) {
            this.isInit = SonDarFolderState.ReduildPending;
            throw missFileError;
        }
        this.isInit = SonDarFolderState.None;
    }

    /**
     * Add file in folder. Get thread and write in.
     *
     * @param fileModule
     * @param fileName
     * @return
     */
    public FileModuleWriteThreadInterface addFile(FileModuleInterface fileModule, String fileName) {
        if (this.isInit != SonDarFolderState.None) {
            throw new FolderNotReadyException();
        }
        FileModuleWriteThreadInterface temp = fileModule.getWriteThread(globalFolder + "/" + folderName + "/" + fileName);
        this.config.addFile(fileModule, globalFolder, folderName, fileName);
        return temp;
    }

    /**
     * Get file from current folder. If file not exist in this folder -
     * exception.
     *
     * @param fileModule
     * @param fileName
     * @return
     */
    public FileModuleReadThreadInterface getFile(FileModuleInterface fileModule, String fileName) {
        if (this.isInit != SonDarFolderState.None) {
            throw new FolderNotReadyException();
        }
        if (!this.config.configFileList.contains(fileName)) {
            throw new FileNotFoundInFolderException();
        }
        FileModuleReadThreadInterface temp = fileModule.getReadThread(getGlobalFileName(fileName));
        this.FileOpen = fileName;
        this.isOpen = true;
        return temp;
    }

    /**
     * Name opened file
     */
    private String FileOpen = "";
    /**
     * Opened flag
     */
    private boolean isOpen = false;

    public void setOpenFile(String fileName) {
        this.FileOpen = fileName;
        this.isOpen = true;
    }

    public FileModuleWriteThreadInterface saveFile(FileModuleInterface fileModule) {
        if (this.isInit != SonDarFolderState.None) {
            throw new FolderNotReadyException();
        }
        if (this.isOpen) {
            FileModuleWriteThreadInterface temp = fileModule.getWriteThread(getGlobalFileName(FileOpen));
            this.isOpen = false;
            return temp;
        }
        throw new FileNotOpenedException();
    }

    /**
     * Delete File. Delete file from folder object, configuration file and disk
     * space.
     *
     * @param fileModule
     * @param fileName
     */
    public void deleteFile(FileModuleInterface fileModule, String fileName) {
        FileModuleWriteThreadInterface temp = fileModule.getWriteThread(getGlobalFileName(fileName));
        temp.delFile();
        this.config.DeleteFile(fileModule, globalFolder, folderName, fileName);
    }

    @Override
    public String toString() {
        return "Folder name '" + this.folderName + "'";
    }
}
