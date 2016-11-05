package ru.sondar.core.filemodule.string;

import ru.sondar.core.filemodule.FileModuleReadThreadInterface;

/**
 *
 * @author GlebZemnieks
 */
public class FileModuleReadThread extends FileModuleThread
        implements FileModuleReadThreadInterface {

    FileModuleWriteThread currentThread;

    public FileModuleReadThread(FileModuleWriteThread thread) {
        this.currentThread = thread;
    }

    @Override
    public String read() {
        return this.currentThread.currentValue;
    }

}
