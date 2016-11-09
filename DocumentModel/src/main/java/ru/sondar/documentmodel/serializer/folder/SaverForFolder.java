package ru.sondar.documentmodel.serializer.folder;

import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.documentmodel.serializer.DocumentSaver;

/**
 *
 * @author GlebZemnieks
 */
public class SaverForFolder implements DocumentSaver {

    private final FileModuleInterface fileModule;
    private final String folderPath;

    public SaverForFolder(FileModuleInterface fileModule, String folderPath) {
        this.fileModule = fileModule;
        this.folderPath = folderPath;
    }

    @Override
    public FileModuleWriteThreadInterface getThreadForComment() {
        return fileModule.getWriteThread(folderPath + "/comment.txt");
    }

    @Override
    public FileModuleWriteThreadInterface getThreadForHeadPart() {
        return fileModule.getWriteThread(folderPath + "/head.xml");
    }

    @Override
    public FileModuleWriteThreadInterface getThreadForWordsBasePart() {
        return fileModule.getWriteThread(folderPath + "/wordsbase.xml");
    }

    @Override
    public FileModuleWriteThreadInterface getThreadSequence() {
        return fileModule.getWriteThread(folderPath + "/sequence.xml");
    }

    @Override
    public FileModuleWriteThreadInterface getThreadForLogPart() {
        return fileModule.getWriteThread(folderPath + "/log.xml");
    }

    @Override
    public FileModuleWriteThreadInterface getThreadForDependencyPart() {
        return fileModule.getWriteThread(folderPath + "/dependency.xml");
    }

}
