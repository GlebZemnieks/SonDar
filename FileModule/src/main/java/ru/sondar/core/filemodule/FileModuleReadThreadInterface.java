package ru.sondar.core.filemodule;

/**
 * Read thread interface.
 * <p>
 * Implement this interface for reading file
 * </p>
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public interface FileModuleReadThreadInterface extends FileModuleThreadInterface {

    /**
     * Read line from current thread.
     *
     * @return Next line from file
     */
    public String read();
}
