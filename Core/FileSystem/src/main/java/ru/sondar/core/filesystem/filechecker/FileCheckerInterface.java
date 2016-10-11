package ru.sondar.core.filesystem.filechecker;

import java.io.File;

/**
 *
 * @author GlebZemnieks
 */
public interface FileCheckerInterface {

    /**
     * Check what current file valid for your conditions, before return true.
     *
     * @param file
     * @return
     */
    boolean isFileValid(File file);

}
