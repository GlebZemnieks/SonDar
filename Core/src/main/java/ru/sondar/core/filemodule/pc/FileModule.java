package ru.sondar.core.filemodule.pc;

import ru.sondar.core.Config;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.FileModuleReadThreadInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;

/**
 * File module for PC
 *
 * @author GlebZemnieks
 */
public class FileModule implements FileModuleInterface {

    @Override
    public FileModuleWriteThreadInterface getWriteThread(String fileName) {
        Config.Log("FileModuleLog", "Try to open file '" + fileName + "' for writing");
        FileModuleWriteThreadInterface temp = new FileModuleWriteThread(fileName);
        Config.Log("FileModuleLog", "File '" + fileName + "' ready for writing");
        return temp;
    }

    @Override
    public FileModuleReadThreadInterface getReadThread(String fileName) {
        Config.Log("FileModuleLog", "Try to open file '" + fileName + "' for reading");
        FileModuleReadThreadInterface temp = new FileModuleReadThread(fileName);
        Config.Log("FileModuleLog", "File '" + fileName + "' ready for reading");
        return temp;
    }

    @Override
    public FileModuleWriteThreadInterface getWriteThreadToAppend(String fileName) { 
        Config.Log("FileModuleLog", "Try to open file '" + fileName + "' to append writing");
        FileModuleWriteThreadInterface temp = new FileModuleWriteThread(fileName, true);
        Config.Log("FileModuleLog", "File '" + fileName + "' ready for writing");
        return temp;
    }

}
