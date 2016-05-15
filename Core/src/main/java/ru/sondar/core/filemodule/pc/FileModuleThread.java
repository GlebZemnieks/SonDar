package ru.sondar.core.filemodule.pc;

import java.io.File;
import ru.sondar.core.Config;
import ru.sondar.core.filemodule.FileModuleThreadInterface;

/**
 * Main thread for PC
 *
 * @author GlebZemnieks
 */
public class FileModuleThread implements FileModuleThreadInterface {

    protected File file;

    @Override
    public void delFile() {
        if (this.file != null) {
            this.file.delete();
        }
    }
    private boolean isClose = false;

    @Override
    public boolean isClose() {
        return this.isClose;
    }

    @Override
    public void close() {
        Config.Log("FileModuleLog", "Close file '" + fileName + "'");
        this.isClose = true;
    }
    protected String fileName;

    protected FileModuleThread(String fileName) {
        this.fileName = fileName;
    }

}
