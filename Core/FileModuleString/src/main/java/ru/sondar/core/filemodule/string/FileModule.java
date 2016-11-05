package ru.sondar.core.filemodule.string;

import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.FileModuleReadThreadInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;

/**
 *
 * @author GlebZemnieks
 */
public class FileModule implements FileModuleInterface {
    
    FileModuleWriteThread currentThread;

    @Override
    public FileModuleWriteThreadInterface getWriteThread(String fileName) {
        this.currentThread = new FileModuleWriteThread();
        return this.currentThread;
    }

    @Override
    public FileModuleWriteThreadInterface getWriteThreadToAppend(String fileName) {
        this.currentThread = new FileModuleWriteThread();
        return this.currentThread;
    }

    @Override
    public FileModuleReadThreadInterface getReadThread(String fileName) {
        return new FileModuleReadThread(currentThread);
    }

}
