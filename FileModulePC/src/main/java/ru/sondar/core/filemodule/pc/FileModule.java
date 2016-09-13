package ru.sondar.core.filemodule.pc;

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
        FileModuleWriteThreadInterface temp = new FileModuleWriteThread(fileName, false);
        return temp;
    }

    @Override
    public FileModuleWriteThreadInterface getWriteThreadToAppend(String fileName) {
        FileModuleWriteThreadInterface temp = new FileModuleWriteThread(fileName, true);
        return temp;
    }

    @Override
    public FileModuleReadThreadInterface getReadThread(String fileName) {
        FileModuleReadThreadInterface temp = new FileModuleReadThread(fileName);
        return temp;
    }

}
