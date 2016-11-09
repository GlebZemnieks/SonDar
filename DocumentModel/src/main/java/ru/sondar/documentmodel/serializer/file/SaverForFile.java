package ru.sondar.documentmodel.serializer.file;

import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.documentmodel.serializer.DocumentSaver;

/**
 * Default realization for DocumentSaver. Using for write all part in one thread
 *
 * @author GlebZemnieks
 */
public class SaverForFile implements DocumentSaver {

    private final FileModuleWriteThreadInterface thread;

    public SaverForFile(FileModuleWriteThreadInterface thread) {
        this.thread = thread;
    }

    @Override
    public FileModuleWriteThreadInterface getThreadForComment() {
        return this.thread;
    }

    @Override
    public FileModuleWriteThreadInterface getThreadForHeadPart() {
        return this.thread;
    }

    @Override
    public FileModuleWriteThreadInterface getThreadForWordsBasePart() {
        return this.thread;
    }

    @Override
    public FileModuleWriteThreadInterface getThreadSequence() {
        return this.thread;
    }

    @Override
    public FileModuleWriteThreadInterface getThreadForLogPart() {
        return this.thread;
    }

    @Override
    public FileModuleWriteThreadInterface getThreadForDependencyPart() {
        return this.thread;
    }

}
