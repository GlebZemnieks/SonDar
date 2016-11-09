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
