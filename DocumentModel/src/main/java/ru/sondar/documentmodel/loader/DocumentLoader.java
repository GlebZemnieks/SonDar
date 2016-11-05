package ru.sondar.documentmodel.loader;

import ru.sondar.core.parser.exception.ObjectStructureException;
import ru.sondar.documentmodel.SDSequenceObject;
import ru.sondar.documentmodel.dependencymodel.DependencyPart;
import ru.sondar.documentmodel.objectmodel.SDHeadPart;
import ru.sondar.documentmodel.objectmodel.SDLogPart;
import ru.sondar.documentmodel.objectmodel.SDWordsBasePart;
import ru.sondar.documentmodel.serializer.DocumentSerializer;

/**
 *
 * @author GlebZemnieks
 */
public interface DocumentLoader {

    /**
     * Get Head part of document
     *
     * @param serializer
     * @param headPart get head part from document by "%fileName%"
     * @throws ObjectStructureException
     */
    void getHeadPart(DocumentSerializer serializer, SDHeadPart headPart) throws ObjectStructureException;

    /**
     * Get words base part of document
     *
     * @param serializer
     * @param wordsBase get words base part from document by "%fileName%"
     * @throws ObjectStructureException
     */
    void getWordsBasePart(DocumentSerializer serializer, SDWordsBasePart wordsBase) throws ObjectStructureException;

    /**
     * Get Sequence of document
     *
     * @param serializer
     * @param xmlSequence get sequence part from document by "%fileName%"
     * @throws ObjectStructureException
     */
    void getSequence(DocumentSerializer serializer, SDSequenceObject xmlSequence) throws ObjectStructureException;

    /**
     * Get Log part of document
     *
     * @param serializer
     * @param logPart get log part from document by "%fileName%"
     * @throws ObjectStructureException
     */
    void getLogPart(DocumentSerializer serializer, SDLogPart logPart) throws ObjectStructureException;

    /**
     * Get dependency part of document
     *
     * @param serializer
     * @param dependencyPart get dependency part from document by "%fileName%"
     * @throws ObjectStructureException
     */
    void getDependencyPart(DocumentSerializer serializer, DependencyPart dependencyPart) throws ObjectStructureException;
}
