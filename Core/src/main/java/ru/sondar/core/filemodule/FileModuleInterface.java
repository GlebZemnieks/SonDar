package ru.sondar.core.filemodule;

import ru.sondar.core.filemodule.exception.SonDarFileNotFoundException;

/**
 * Facade for file module. Main class to work with files. All fileModule's must
 * implements this interface
 *
 * @author GlebZemnieks
 */
public interface FileModuleInterface {

    /**
     * Open new thread to write file
     *
     * @param fileName
     * @return
     */
    public FileModuleWriteThreadInterface getWriteThread(String fileName);

    /**
     * Open new thread to append string to file. If file not exist create new
     * file
     *
     * @param fileName
     * @return
     * @throws SonDarFileNotFoundException
     */
    public FileModuleWriteThreadInterface getWriteThreadToAppend(String fileName);

    /**
     * Open new thread to read file
     *
     * @param fileName
     * @return
     */
    public FileModuleReadThreadInterface getReadThread(String fileName);
}
