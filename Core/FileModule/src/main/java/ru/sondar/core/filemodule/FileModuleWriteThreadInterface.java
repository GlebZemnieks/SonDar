package ru.sondar.core.filemodule;

/**
 * Write thread interface.
 * <p>
 * Implement this interface for writing to file
 * </p>
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public interface FileModuleWriteThreadInterface extends FileModuleThreadInterface {

    /**
     * Write string in thread.
     * <p>
     * End string symbol should be added by user
     * </p>
     *
     * @param textForWriting
     * @return
     */
    public int write(String textForWriting);
}
