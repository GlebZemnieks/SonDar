package ru.sondar.core.filesystem;

import java.util.Arrays;
import ru.sondar.core.Config;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.FileModuleReadThreadInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import static ru.sondar.core.filesystem.SonDarFileSystem.getTagContent;
import ru.sondar.core.filesystem.exception.ConfigFileFormatException;
import ru.sondar.core.filesystem.exception.FileNotFoundInFolderException;

public class SonDarFolderConfig {

    /**
     * Configuration file name
     */
    private final String configFileName = "config.txt";

    /**
     * Getter for file name field
     *
     * @return
     */
    public String getConfigFileName() {
        return this.configFileName;
    }
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
     * Constructor
     *
     * @param fileModule
     * @param globalFolder
     * @param folderName
     * @param logger
     * @throws ConfigFileFormatException
     */
    public SonDarFolderConfig(FileModuleInterface fileModule, String globalFolder, String folderName) throws ConfigFileFormatException {
        this.getConfigList(fileModule, globalFolder, folderName);
    }

    /**
     * List of file on folder
     */
    public String[] configFileList;

    /**
     * Reset file list
     */
    public void resetFileList() {
        this.configFileList = null;
    }

    /**
     * Read config file and check it relevant Configurate actual fileList
     *
     * @param fileModule
     * @param globalFolder
     * @param folderName
     * @throws ConfigFileFormatException
     */
    private void getConfigList(FileModuleInterface fileModule, String globalFolder, String folderName) throws ConfigFileFormatException {
        FileModuleReadThreadInterface readThread = fileModule.getReadThread(globalFolder + "/" + folderName + "/" + configFileName);
        String[] result = new String[1];
        readThread.read();
        String temp = readThread.read();
        if (temp != null && !temp.contains(configTag)) {
            result[0] = getTagContent(temp, configFileTag);
        } else {
            Config.Log(logTag, "Final config list is empty");
            return;
        }
        temp = readThread.read();
        while (temp != null) {
            if (getTagContent(temp, configFileTag) != null) {
                String[] resultTemp = new String[result.length + 1];
                System.arraycopy(result, 0, resultTemp, 0, result.length);
                resultTemp[result.length] = getTagContent(temp, configFileTag);
                result = resultTemp;
            } else {
                if (!temp.contains(configTag)) {
                    throw new ConfigFileFormatException();
                }
                break;
            }
            temp = readThread.read();
        }
        readThread.close();
        configFileList = result;
        Config.Log(logTag, "Final config list " + Arrays.toString(configFileList));
    }

    /**
     * Add file in fileList. To write changes in config file calling method
     * Update
     *
     * @param fileModule
     * @param globalFolder
     * @param folderName
     * @param fileName
     */
    public void addFile(FileModuleInterface fileModule, String globalFolder, String folderName, String fileName) {
        String[] temp;
        if (this.configFileList == null) {
            temp = new String[1];
            temp[0] = fileName;
            this.configFileList = temp;
            this.update(fileModule, globalFolder, folderName);
            return;
        }
        for (String file : this.configFileList) {
            if (file.equals(fileName)) {
                return;
            }
        }
        temp = new String[this.configFileList.length + 1];
        System.arraycopy(this.configFileList, 0, temp, 0, this.configFileList.length);
        temp[this.configFileList.length] = fileName;
        this.configFileList = temp;
        this.update(fileModule, globalFolder, folderName);
    }

    /**
     *
     * Delete file from fileList. To write changes in config file calling method
     * Update
     *
     * @param fileModule
     * @param globalFolder
     * @param folderName
     * @param fileName
     */
    public void DeleteFile(FileModuleInterface fileModule, String globalFolder, String folderName, String fileName) {
        if (this.configFileList == null) {
            throw new FileNotFoundInFolderException();
        }
        int Id = 0;
        boolean isFileInFolder = false;
        for (int count = 0; !isFileInFolder && count < this.configFileList.length; count++) {
            if (this.configFileList[count].equals(fileName)) {
                isFileInFolder = true;
                Id = count;
            }
        }
        if (!isFileInFolder) {
            throw new FileNotFoundInFolderException();
        }
        if (this.configFileList.length == 1) {
            this.configFileList = null;
            this.update(fileModule, globalFolder, folderName);
            return;
        }
        String[] temp;
        temp = new String[this.configFileList.length - 1];
        System.arraycopy(this.configFileList, 0, temp, 0, Id);
        for (int count = Id + 1; count < this.configFileList.length; count++) {
            temp[count - 1] = this.configFileList[count];
        }
        this.configFileList = temp;
        this.update(fileModule, globalFolder, folderName);
    }

    /**
     * Write changes to config file
     *
     * @param fileModule
     * @param globalFolder
     * @param folderName
     */
    public void update(FileModuleInterface fileModule, String globalFolder, String folderName) {
        FileModuleWriteThreadInterface writeThread = fileModule.getWriteThread(globalFolder + "/" + folderName + "/" + configFileName);
        writeThread.write("<" + configTag + ">\n");
        if (this.configFileList == null) {
            writeThread.write("</" + configTag + ">");
            writeThread.close();
            return;
        }
        for (String configList1 : this.configFileList) {
            writeThread.write("<" + configFileTag + ">" + configList1 + "</" + configFileTag + ">\n");
        }
        writeThread.write("</" + configTag + ">");
        writeThread.close();
    }
}
