package ru.sondar.core.filemodule;

/**
 * Thread interface. Allow work with files/
 *
 * @author GlebZemnieks
 */
public interface FileModuleThreadInterface {

    /**
     * Delete file
     */
    public void delFile();

    /**
     * Close thread
     */
    public void close();

    /**
     * Is thread open now checking
     *
     * @return
     */
    public boolean isClose();
}
