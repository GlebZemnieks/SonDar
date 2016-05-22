package ru.sondar.core.filesystem;

import java.util.Arrays;
import ru.sondar.core.Config;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.FileModuleReadThreadInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.filemodule.exception.SonDarFileNotFoundException;
import ru.sondar.core.filesystem.exception.ConfigFileFormatException;
import ru.sondar.core.filesystem.exception.FileNotFoundInFolderException;
import ru.sondar.core.filesystem.exception.FileNotOpenedException;
import ru.sondar.core.filesystem.exception.FirstFolderUseException;
import ru.sondar.core.filesystem.exception.FolderNotReadyException;
import ru.sondar.core.filesystem.exception.FolderObjectNotInSystemException;
import ru.sondar.core.filesystem.exception.MissFileInFolderException;
import ru.sondar.core.filesystem.exception.SomeTroubleWithFolderException;

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
     * @param logger
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
    public String[] getFileList() {
        if (this.isInit != SonDarFolderState.None) {
            Config.Log(logTag, "Folder not init. Now state : " + this.isInit.toString());
            throw new FolderNotReadyException();
        }
        return this.config.configFileList;
    }

    /**
     * Return global file name if it exist in this folder
     *
     * @param fileName
     * @return
     */
    public String getGlobalFileName(String fileName) {
        if (this.isInit != SonDarFolderState.None) {
            Config.Log(logTag, "Folder not init. Now state : " + this.isInit.toString());
            throw new FolderNotReadyException();
        }
        boolean isInFolder = false;
        for (String temp : this.config.configFileList) {
            if (fileName.equals(temp)) {
                isInFolder = true;
            }
        }
        if (!isInFolder) {
            Config.Log(logTag, "File '" + fileName + "' not found in folder. FileList : " + Arrays.toString(this.config.configFileList));
            throw new FileNotFoundInFolderException();
        }
        return this.globalFolder + "/" + this.folderName + "/" + fileName;
    }

    /**
     * Read config file and check that folder relevant
     *
     * @param fileModule
     * @throws SomeTroubleWithFolderException
     */
    public void init(FileModuleInterface fileModule) throws SomeTroubleWithFolderException {
        Config.Log(logTag, "init folder '" + folderName + "'");
        if (!isInSystem) {
            Config.Log(logTag, "Folder isInSystem = False");
            throw new FolderObjectNotInSystemException();
        }
        //check config file
        Config.Log(logTag, "check config file in folder '" + folderName + "'");
        try {
            config = new SonDarFolderConfig(fileModule, globalFolder, folderName);
        } catch (SonDarFileNotFoundException error) {
            Config.Log(logTag, "Config file in " + folderName + " folder not exist");
            SomeTroubleWithFolderException next = new FirstFolderUseException();
            next.addSuppressed(error);
            this.isInit = SonDarFolderState.ReduildPending;
            throw next;
        } catch (ConfigFileFormatException error) {
            Config.Log(logTag, "Config file in " + folderName + " folder have bad format");
            SomeTroubleWithFolderException next = new SomeTroubleWithFolderException();
            next.addSuppressed(error);
            this.isInit = SonDarFolderState.ReduildPending;
            throw next;
        }
        Config.Log(logTag, "config file found in folder '" + folderName + "'");
        Config.Log(logTag, "check file consist in folder '" + folderName + "';");
        boolean allRight = true;
        MissFileInFolderException missFileError = new MissFileInFolderException();
        for (int count = 0; (config.configFileList != null) && (count < config.configFileList.length); count++) {
            Config.Log(logTag, "check file : " + config.configFileList[count]);
            try {
                FileModuleReadThreadInterface readThread = fileModule.getReadThread(globalFolder + "/" + folderName + "/" + config.configFileList[count]);
            } catch (SonDarFileNotFoundException error) {
                Config.Log(logTag, "trouble with file " + config.configFileList[count]);
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
            Config.Log(logTag, "Folder not init. Now state : " + this.isInit.toString());
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
            Config.Log(logTag, "Folder not init. Now state : " + this.isInit.toString());
            throw new FolderNotReadyException();
        }
        if (this.config.configFileList == null) {
            throw new FileNotFoundInFolderException();
        }
        boolean isInFolder = false;
        for (String temp : this.config.configFileList) {
            if (temp.equals(fileName)) {
                isInFolder = true;
            }
        }
        if (!isInFolder) {
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
            Config.Log(logTag, "Folder not init. Now state : " + this.isInit.toString());
            throw new FolderNotReadyException();
        }
        if (this.isOpen) {
            Config.Log(logTag, "Save document with name : " + this.FileOpen);
            FileModuleWriteThreadInterface temp = fileModule.getWriteThread(getGlobalFileName(FileOpen));
            this.isOpen = false;
            return temp;
        }
        Config.Log(logTag, "File not opened. Throw exception : FileNotOpenedException");
        throw new FileNotOpenedException();
    }

    /**
     * Delete File. Delete file from folder object, config file and disk space.
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
