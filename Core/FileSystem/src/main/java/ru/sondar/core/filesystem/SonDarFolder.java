package ru.sondar.core.filesystem;

import ru.sondar.core.filesystem.filechecker.FileCheckerInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import ru.sondar.core.filemodule.*;
import ru.sondar.core.filemodule.exception.*;
import ru.sondar.core.filesystem.exception.*;
import ru.sondar.core.logger.Logger;

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

    public void init(FileModuleInterface fileModule) throws SomeTroubleWithFolderException {
        this.init(fileModule, 0);
    }

    public void init(FileModuleInterface fileModule, FileCheckerInterface checker) throws SomeTroubleWithFolderException {
        this.init(fileModule, checker, 0);
    }

    public void init(FileModuleInterface fileModule, int retryCount) throws SomeTroubleWithFolderException {
        this.init(fileModule, new FileCheckerInterface() {
            @Override
            public boolean isFileValid(File file) {
                return true;
            }
        }, retryCount);
    }

    /**
     * Read configuration file and check that folder relevant
     *
     * @param fileModule
     * @param checker
     * @param retryCount
     * @throws SomeTroubleWithFolderException
     */
    public void init(FileModuleInterface fileModule, FileCheckerInterface checker, int retryCount) throws SomeTroubleWithFolderException {
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
        MissFileInFolderException missFileError = new MissFileInFolderException("File in folder nor found : ");
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
            if (retryCount > 0) {
                Logger.Log(logTag, "Trouble with folder init. Start rebuild", missFileError);
                this.rebuild(fileModule, checker);
                this.init(fileModule, checker, retryCount - 1);
            } else {
                Logger.Log(logTag, "Trouble with folder init. Retry count equals 0. Throw missFileError", missFileError);
                throw missFileError;
            }
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

    public void rebuild(FileModuleInterface fileModule) {
        this.rebuild(fileModule, new FileCheckerInterface() {
            @Override
            public boolean isFileValid(File file) {
                //Add all file in folder without filter by content
                return true;
            }
        });
    }

    public void rebuild(FileModuleInterface fileModule, FileCheckerInterface checker) {
        Logger.Log(logTag, "rebuild : Start in folder \"" + this.toString() + "\"");
        this.config.resetFileList();
        this.isInit = SonDarFolderState.None;
        File folder = new File(this.globalFolder + "/" + this.folderName);
        File[] folderEntries = folder.listFiles();
        Logger.Log(logTag, "rebuild : Files was found in folder : " + Arrays.toString(folderEntries));
        for (File entry : folderEntries) {
            if (entry.getName().equals("config.txt")) {
                //Skip configuration file
                continue;
            }
            if (entry.isDirectory()) {
                //Sub folder unsupported
                Logger.Log(logTag, "rebuild : Find folder \"" + entry.getName() + "\". Skip from rebuild");
                continue;
            }
            if (checker.isFileValid(entry)) {
                Logger.Log(logTag, "rebuild : Valid file  \"" + entry.getName() + "\"");
                this.addFile(fileModule, entry.getName());
            } else {
                Logger.Log(logTag, "rebuild : Invalid file \"" + entry.getName() + "\"");
            }
        }
        Logger.Log(logTag, "rebuild : Finish. Current file list : " + this.getFileList());
    }

    @Override
    public String toString() {
        return "Folder name '" + this.folderName + "'";
    }
}
