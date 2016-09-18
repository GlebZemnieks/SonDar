package ru.sondar.core.filemodule;

/**
 * Main class to work with files. All fileModule's must implements this
 * interface<p>
 * Before start to manipulation with file create object, which extends this
 * interface for system, which you use.</p>
 * <p>
 * Current package have default realization for windows
 * {@link ru.sondar.core.filemodule.pc.FileModule}.
 * </p>
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public interface FileModuleInterface {

    /**
     * Tag to search activity this object in logging file
     */
    String LOGTAG = "FileModule213";

    /**
     * Open new thread to write file. If file not exist, create new file. If
     * file exist <b>clear it</b> and write again.
     *
     * @param fileName Filename with full path to file and format.
     * @return Writing thread implementing
     * {@link ru.sondar.core.filemodule.FileModuleWriteThreadInterface}
     */
    FileModuleWriteThreadInterface getWriteThread(String fileName);

    /**
     * Open new thread to append string to file. If file not exist, create new
     * file. If file exist, <b>append newline</b> to file.
     *
     * @param fileName file name with full path to file and format.
     * @return Writing thread implementing
     * {@link ru.sondar.core.filemodule.FileModuleWriteThreadInterface}
     */
    FileModuleWriteThreadInterface getWriteThreadToAppend(String fileName);

    /**
     * Open new thread to read file.
     * <p>
     * If file not exist by path throw
     * {@link ru.sondar.core.filemodule.exception.SonDarFileNotFoundException}(RunTime)
     * </p>
     *
     * @param fileName Filename with full path to file and format.
     * @return Reading thread implementing
     * {@link ru.sondar.core.filemodule.FileModuleReadThreadInterface}
     */
    FileModuleReadThreadInterface getReadThread(String fileName);
}
