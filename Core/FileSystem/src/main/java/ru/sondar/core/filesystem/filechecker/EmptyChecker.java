package ru.sondar.core.filesystem.filechecker;

import java.io.File;

/**
 * Default realization of FileCheckerInterface. Rebuild all file in folder
 *
 * @author GlebZemnieks
 */
public class EmptyChecker implements FileCheckerInterface {

    @Override
    public boolean isFileValid(File file) {
        return true;
    }

}
