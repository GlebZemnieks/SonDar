package ru.sondar.core.filemodule;

/**
 * Read thread interface.
 *
 * @author GlebZemnieks
 */
public interface FileModuleReadThreadInterface extends FileModuleThreadInterface {

    /**
     * Read line from current thread
     *
     * @return
     */
    public String read();
}
