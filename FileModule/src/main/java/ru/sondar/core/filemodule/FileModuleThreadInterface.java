package ru.sondar.core.filemodule;

/**
 * Main thread interface. Default functions for manipulations with files. Temp
 * interface for usability in interface {@link ru.sondar.core.filemodule.FileModuleReadThreadInterface}
 * {@link ru.sondar.core.filemodule.FileModuleWriteThreadInterface}
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public interface FileModuleThreadInterface {

    /**
     * Tag to search activity this object in logging file
     */
    String LOGTAG = "FileModuleThread";

    /**
     * Delete current thread file.
     */
    void delFile();

    /**
     * Close this thread. If method was called, current thread should throw
     * {@link ru.sondar.core.filemodule.exception.ThreadIsCloseException}(RunTime)
     * on every call of its methods
     */
    void close();

    /**
     * Is thread closed now.
     *
     * @return Boolean value with True, when thread is closed.
     */
    boolean isClose();
}
