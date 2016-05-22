package ru.sondar.client.filemodule.android;

import android.content.Context;

import java.io.File;

import ru.sondar.core.Config;
import ru.sondar.core.filemodule.FileModuleThreadInterface;

public class FileModuleThread implements FileModuleThreadInterface {

    /**
     * Closed flag.
     */
    private boolean isClose = false;
    /**
     * Context for access
     */
    protected Context context;
    /**
     * Name of file to this thread
     */
    protected String fileName;

    /**
     * Protected constructor
     *
     * @param context
     * @param fileName
     */
    protected FileModuleThread(Context context, String fileName) {
        this.context = context;
        this.fileName = fileName;
    }

    @Override
    public void close() {
        Config.Log("FileModuleLog", "Close file '" + fileName + "'");
        this.isClose = true;
    }

    @Override
    public void delFile() {
        File file = new File(fileName);
        if (file != null) {
            file.delete();
        }
    }

    @Override
    public boolean isClose() {
        return this.isClose;
    }

}
