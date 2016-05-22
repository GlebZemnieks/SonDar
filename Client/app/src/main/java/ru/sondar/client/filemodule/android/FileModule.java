/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sondar.client.filemodule.android;

import android.content.Context;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.FileModuleReadThreadInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;

/**
 * Android fileModule class
 *
 * @author GlebZemnieks
 */
public class FileModule implements FileModuleInterface {

    /**
     * Context for document access
     */
    private Context context;

    /**
     * Constructor
     *
     * @param context
     * @param logger
     */
    public FileModule(Context context) {
        this.context = context;
    }

    @Override
    public FileModuleReadThreadInterface getReadThread(String fileName) {
        FileModuleThreadRead temp = new FileModuleThreadRead(this.context, fileName);
        return temp;
    }

    @Override
    public FileModuleWriteThreadInterface getWriteThread(String fileName) {
        return new FileModuleThreadWrite(this.context, fileName);
    }

    @Override
    public FileModuleWriteThreadInterface getWriteThreadToAppend(String fileName) {
        return new FileModuleThreadWrite(this.context, fileName, true);
    }
}
