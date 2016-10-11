package ru.sondar.core.logger.file;

import java.util.Date;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.logger.LoggerInterface;

/**
 * Class logger for File Implements
 *
 * @author GlebZemnieks
 * @since SonDar-1.2
 */
public class FileLogging implements LoggerInterface {

    FileModuleInterface fileModule;
    public String fileName;
    FileModuleWriteThreadInterface out;

    public FileLogging(FileModuleInterface fileModule, String fileName, boolean append) {
        this.fileModule = fileModule;
        this.fileName = fileName;
        if (append) {
            out = fileModule.getWriteThreadToAppend(fileName);
        } else {
            out = fileModule.getWriteThread(fileName);
        }
    }

    @Override
    public void Log(String logTag, String logMsg) {
        out.write((new Date()).toString() + "::" + logTag + "-->  " + logMsg + "\n");
        out.flush();
    }

    @Override
    public void Log(String logTag, String logMsg, Throwable errorStackTrace) {
        out.write((new Date()).toString() + "::" + logTag + "-->  " + logMsg + " ::Type\"" + errorStackTrace.getClass().toString() + "\":: " + errorStackTrace.getMessage() + "\n");
        out.flush();
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
