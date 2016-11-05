package ru.sondar.core.filemodule.string;

import ru.sondar.core.filemodule.FileModuleThreadInterface;

/**
 *
 * @author GlebZemnieks
 */
public class FileModuleThread implements FileModuleThreadInterface {

    protected String currentValue = "";

    private boolean isClose = false;

    @Override
    public void delFile() {
        this.currentValue = "";
    }

    @Override
    public void close() {
        this.isClose = true;
    }

    @Override
    public boolean isClose() {
        return this.isClose;
    }

}
