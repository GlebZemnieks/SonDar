package ru.sondar.core.filemodule.pc;

import java.io.File;
import ru.sondar.core.filemodule.FileModuleThreadInterface;
import ru.sondar.core.filemodule.exception.ThreadIsCloseException;

/**
 * Default realization interface
 * {@link ru.sondar.core.filemodule.FileModuleThreadInterface} for windows.
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class FileModuleThread implements FileModuleThreadInterface {

    /**
     * Object for file which will use in current thread. Common field for
     * classes {@link ru.sondar.core.filemodule.pc.FileModuleReadThread},
     * {@link ru.sondar.core.filemodule.pc.FileModuleWriteThread}. Initialize
     * this field they should in its constructors.
     */
    protected File file;

    /**
     * Name of file which use in current thread.
     */
    protected String fileName;

    /**
     * Is current thread is closed
     */
    private boolean isClose = false;

    /**
     * Constructor
     *
     * @param fileName Filename with full path to file and format.
     */
    protected FileModuleThread(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void delFile() {
        if (isClose) {
            throw new ThreadIsCloseException("Tried to delete file after thread closing. File name : " + this.fileName);
        }
        if (this.file != null) {
            this.file.delete();
        }
        //Do nothing!
    }

    @Override
    public void close() {
        if (isClose) {
            throw new ThreadIsCloseException("Tried to close file after thread closing. File name : " + this.fileName);
        }
        this.isClose = true;
    }

    @Override
    public boolean isClose() {
        return this.isClose;
    }
}
