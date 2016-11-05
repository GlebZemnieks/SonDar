package ru.sondar.core.filemodule.string;

import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;

/**
 *
 * @author GlebZemnieks
 */
public class FileModuleWriteThread extends FileModuleThread
        implements FileModuleWriteThreadInterface {

    @Override
    public int write(String textForWriting) {
        this.currentValue += textForWriting + "\n";
        return textForWriting.length() + 1;
    }

    @Override
    public void flush() {
    }
}
