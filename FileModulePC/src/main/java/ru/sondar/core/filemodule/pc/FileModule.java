package ru.sondar.core.filemodule.pc;

import ru.sondar.core.Config;
import ru.sondar.core.filemodule.*;

/**
 * File module for windows.
 * <p>
 * Uses default realization for interfaces
 * <li>{@link ru.sondar.core.filemodule.FileModuleWriteThreadInterface} -> {@link ru.sondar.core.filemodule.pc.FileModuleWriteThread}</li>
 * <li>{@link ru.sondar.core.filemodule.FileModuleReadThreadInterface} -> {@link ru.sondar.core.filemodule.pc.FileModuleReadThread}</li>
 * </p>
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class FileModule implements FileModuleInterface {

    @Override
    public FileModuleWriteThreadInterface getWriteThread(String fileName) {
        Config.Log(LOGTAG, "Try to open file '" + fileName + "' for writing");
        FileModuleWriteThreadInterface temp = new FileModuleWriteThread(fileName, false);
        Config.Log(LOGTAG, "File '" + fileName + "' ready for writing");
        return temp;
    }

    @Override
    public FileModuleWriteThreadInterface getWriteThreadToAppend(String fileName) {
        Config.Log(LOGTAG, "Try to open file '" + fileName + "' to append writing");
        FileModuleWriteThreadInterface temp = new FileModuleWriteThread(fileName, true);
        Config.Log(LOGTAG, "File '" + fileName + "' ready for writing");
        return temp;
    }

    @Override
    public FileModuleReadThreadInterface getReadThread(String fileName) {
        Config.Log(LOGTAG, "Try to open file '" + fileName + "' for reading");
        FileModuleReadThreadInterface temp = new FileModuleReadThread(fileName);
        Config.Log(LOGTAG, "File '" + fileName + "' ready for reading");
        return temp;
    }

}
