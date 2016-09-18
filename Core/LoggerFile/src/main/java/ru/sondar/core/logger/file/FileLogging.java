package ru.sondar.core.logger.file;

import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.logger.LoggerInterface;

/**
 * Class logger for File Implements
 *
 * @author GlebZemnieks
 * @since SonDar-1.2
 */
public class FileLogging implements LoggerInterface{

    FileModuleInterface fileModule;
    public String fileName;
    
    public FileLogging(FileModuleInterface fileModule, String fileName) {
        this.fileModule = fileModule;
        this.fileName = fileName;
        //getWriteThread - create new empty file
        FileModuleWriteThreadInterface temp = fileModule.getWriteThread(fileName);
        temp.close();
    }
    
    @Override
    public void Log(String logTag, String logMsg) {
        FileModuleWriteThreadInterface temp = fileModule.getWriteThreadToAppend(fileName);
        temp.write(logTag + "-->  " + logMsg + "\n");
        temp.close();
    }

    @Override
    public void Log(String logTag, String logMsg, Throwable errorStackTrace) {
        FileModuleWriteThreadInterface temp = fileModule.getWriteThreadToAppend(fileName);
        temp.write(logTag + "-->  " + logMsg + " ::Error:: " + errorStackTrace.getMessage() + "\n");
        temp.close();
    }

    @Override
    public void Log(int logingLevel, String logTag, String logMsg) {
        this.Log(logTag, logMsg);
    }

    @Override
    public void Log(int logingLevel, String logTag, String logMsg, Throwable errorStackTrace) {
        this.Log(logTag, logMsg, errorStackTrace);
    }
    
}
