package ru.sondar.documentmodel.serializer;

import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.parser.exception.ObjectStructureException;
import ru.sondar.documentmodel.SDSequenceObject;
import ru.sondar.documentmodel.dependencymodel.DependencyItem;
import ru.sondar.documentmodel.dependencymodel.DependencyPart;
import ru.sondar.documentmodel.objectmodel.SDHeadPart;
import ru.sondar.documentmodel.objectmodel.SDLogPart;
import ru.sondar.documentmodel.objectmodel.SDMainObject;
import ru.sondar.documentmodel.objectmodel.SDWordsBasePart;
import ru.sondar.documentmodel.objectmodel.WordBase;

/**
 *
 * @author GlebZemnieks
 */
public interface DocumentSerializer {

    /**
     * Parse object attributes
     *
     * @param object
     * @param params
     * @throws ru.sondar.core.parser.exception.ObjectStructureException
     */
    void parseObjectAttribute(SDMainObject object, Object... params) throws ObjectStructureException;

    /**
     * Public method for parser XML string
     *
     * @param object
     * @param params
     * @throws ObjectStructureException
     */
    void parseObject(SDMainObject object, Object... params) throws ObjectStructureException;

    /**
     * An abstract method for parsing internal field in object
     *
     * @param object
     * @param params
     * @throws ObjectStructureException
     */
    void parseCurrentObjectField(SDMainObject object, Object... params) throws ObjectStructureException;

    /**
     * Print attribute in file
     *
     * @param object
     * @param fileModule
     */
    void printObjectAttrivute(SDMainObject object, FileModuleWriteThreadInterface fileModule);

    /**
     * An class print XML string in file
     *
     * @param object
     * @param fileModule
     */
    void printObject(SDMainObject object, FileModuleWriteThreadInterface fileModule);

    /**
     * An abstract method for print XML string of current object
     *
     * @param object
     * @param fileModule
     */
    void printCurrentObjectField(SDMainObject object, FileModuleWriteThreadInterface fileModule);

    /**
     * Method for parse sequence.
     *
     * @param sequence
     * @param params
     * @throws ObjectStructureException
     */
    void parseSequence(SDSequenceObject sequence, Object... params) throws ObjectStructureException;

    /**
     * Method for print sequence
     *
     * @param sequence
     * @param fileModule
     */
    void printSequence(SDSequenceObject sequence, FileModuleWriteThreadInterface fileModule);

    /**
     * Method for parse head part of document
     *
     * @param headPart
     * @param params
     * @throws ObjectStructureException
     */
    void parseHeadPart(SDHeadPart headPart, Object... params) throws ObjectStructureException;

    /**
     * Method for print head part of document
     *
     * @param headPart
     * @param fileModule
     */
    void printHeadPart(SDHeadPart headPart, FileModuleWriteThreadInterface fileModule);

    /**
     * Method for parse log part of document
     *
     * @param logPart
     * @param params
     * @throws ObjectStructureException
     */
    void parseLogPart(SDLogPart logPart, Object... params) throws ObjectStructureException;

    /**
     * Method for print log part of document
     *
     * @param logPart
     * @param fileModule
     */
    void printLogPart(SDLogPart logPart, FileModuleWriteThreadInterface fileModule);

    /**
     * Public method for parser XML string
     *
     * @param wordsBase
     * @param params
     */
    void parseWordsBasePart(SDWordsBasePart wordsBase, Object... params);

    /**
     * An class print XML string in file
     *
     * @param wordsBase
     * @param fileModule
     */
    void printWordsBasePart(SDWordsBasePart wordsBase, FileModuleWriteThreadInterface fileModule);

    /**
     * Public method for parser XML string
     *
     * @param wordsBase
     * @param params
     */
    void parseWordsBase(WordBase wordsBase, Object... params);

    /**
     * An class print XML string in file
     *
     * @param wordsBase
     * @param fileModule
     */
    void printWordsBase(WordBase wordsBase, FileModuleWriteThreadInterface fileModule);

    /**
     * Public method for parser XML string
     *
     * @param dependency
     * @param params
     */
    void parseDependencyPart(DependencyPart dependency, Object... params);

    /**
     * An class print XML string in file
     *
     * @param dependency
     * @param fileModule
     */
    void printDependencyPart(DependencyPart dependency, FileModuleWriteThreadInterface fileModule);

    /**
     * Public method for parser XML string
     *
     * @param dependency
     * @param params
     */
    void parseDependencyItem(DependencyItem dependency, Object... params);

    /**
     * An class print XML string in file
     *
     * @param dependency
     * @param fileModule
     */
    void printDependencyItem(DependencyItem dependency, FileModuleWriteThreadInterface fileModule);

}
