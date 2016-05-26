package ru.sondar.core.filesystem;

import java.util.Arrays;
import java.util.UUID;
import ru.sondar.core.Config;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.FileModuleReadThreadInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.filesystem.exception.FolderNotFoundException;
import ru.sondar.core.filesystem.exception.SomeTroubleWithFolderException;

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
        Config.Log(logTag, "FileSystem create. Add folder and call method init");
    }

    /**
     * Folder list
     */
    private SonDarFolder[] folderList;

    /**
     * Add new folder in file system
     *
     * @param folderName
     */
    public void addFolder(String folderName) {
        Config.Log(logTag, "add '" + folderName + "' folder to fileSystem '" + this.globalFolder + "';");
        SonDarFolder folder = new SonDarFolder(globalFolder, folderName);
        SonDarFolder[] temp;
        if (this.folderList == null) {
            temp = new SonDarFolder[1];
            temp[0] = folder;
            folder.isInSystem = true;
            this.folderList = temp;
            Config.Log(logTag, "folder add successful");
            return;
        }
        temp = new SonDarFolder[this.folderList.length + 1];
        System.arraycopy(this.folderList, 0, temp, 0, this.folderList.length);
        folder.setGlobalFolder(this.globalFolder);
        folder.isInSystem = true;
        temp[this.folderList.length] = folder;
        this.folderList = temp;
        Config.Log(logTag, "folder add successful");
    }

    /**
     * Get folder object by name
     *
     * @param folderName
     * @return
     */
    public SonDarFolder getFolderByName(String folderName) {
        if (this.folderList == null) {
            Config.Log(logTag, "This FileSystem empty");
            throw new FolderNotFoundException();
        }
        Config.Log(logTag, "Get folder " + folderName + " from list : " + Arrays.toString(this.folderList));
        for (SonDarFolder folderList1 : this.folderList) {
            if (folderName == null ? folderList1.getFolderName() == null : folderList1.getFolderName().equals(folderName)) {
                return folderList1;
            }
        }
        Config.Log(logTag, "Folder not found");
        throw new FolderNotFoundException();
    }

    /**
     * Initialization file system
     *
     * @param fileModule
     */
    public void init(FileModuleInterface fileModule) {
        Config.Log(logTag, "Init file system '" + this.globalFolder + "' with folder list : " + Arrays.toString(this.folderList));
        for (SonDarFolder folderList1 : this.folderList) {
            try {
                folderList1.init(fileModule);
                Config.Log(logTag, "successfully init " + folderList1.toString() + "! result : " + folderList1.getState().toString());
            } catch (SomeTroubleWithFolderException ex) {
                Config.Log(logTag, "Trouble with folder: " + folderList1.toString());
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
        Config.Log(logTag, "Add file '" + fileName + "'to folder '" + folderName + "'");
        SonDarFolder temp = this.getFolderByName(folderName);
        FileModuleWriteThreadInterface tempThread = temp.addFile(fileModule, fileName);
        return tempThread;
    }

    /**
     * Delete file from internal folder
     *
     * @param fileModule
     * @param folderName
     * @param fileName
     */
    public void deleteFile(FileModuleInterface fileModule, String folderName, String fileName) {
        Config.Log(logTag, "Delete file '" + fileName + "' from folder '" + folderName + "'");
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
        Config.Log(logTag, "Move file '" + oldFileName + "'from folder '" + folderFrom + "' to folder '" + folderIn + "' with name '" + newFileName + "'");
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
        Config.Log(logTag, "Successfully move");

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
        Config.Log(logTag, "Copy file '" + oldFileName + "'from folder '" + folderFrom + "' to folder '" + folderIn + "' with name '" + newFileName + "'");
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
        Config.Log(logTag, "Successfully copy");
    }

    /**
     * UUID generation
     *
     * @return
     */
    public UUID getUUID() {
        return UUID.randomUUID();
    }

    public static String getTagContent(String Is, String tag) {
        int start = (Is.indexOf("<" + tag + ">") + ("<" + tag + ">").length());
        int end = Is.indexOf("</" + tag + ">");
        // TODO Need Exception
        if (start == -1 || end == -1) {
            return null;
        }
        char temp[] = new char[end - start];
        Is.getChars(start, end, temp, 0);
        String tempR = new String(temp, 0, temp.length);
        return tempR;
    }
}
