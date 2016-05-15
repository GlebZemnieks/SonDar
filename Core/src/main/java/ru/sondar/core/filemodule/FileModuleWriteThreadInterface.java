package ru.sondar.core.filemodule;

/**
 * Write thread interface
 *
 * @author GlebZemnieks
 */
public interface FileModuleWriteThreadInterface extends FileModuleThreadInterface {

    /**
     * write string in thread
     *
     * @param textForWriting
     * @return
     */
    public int write(String textForWriting);
}
