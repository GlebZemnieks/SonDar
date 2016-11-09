package ru.sondar.documentmodel.serializer;

import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;

/**
 *
 * @author GlebZemnieks
 */
public interface DocumentSaver {

    /**
     * Return thread for printing some message in document
     *
     * @return
     */
    FileModuleWriteThreadInterface getThreadForComment();

    /**
     * Return thread for printing head part of document
     *
     * @return
     */
    FileModuleWriteThreadInterface getThreadForHeadPart();

    /**
     * Return thread for printing words base part of document
     *
     * @return
     */
    FileModuleWriteThreadInterface getThreadForWordsBasePart();

    /**
     * Return thread for printing sequence of file
     *
     * @return
     */
    FileModuleWriteThreadInterface getThreadSequence();

    /**
     * Return thread for printing log part of document
     *
     * @return
     */
    FileModuleWriteThreadInterface getThreadForLogPart();

    /**
     * Return thread for printing dependency part of document
     *
     * @return
     */
    FileModuleWriteThreadInterface getThreadForDependencyPart();

}
