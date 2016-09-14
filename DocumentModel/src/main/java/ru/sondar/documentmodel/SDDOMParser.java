package ru.sondar.documentmodel;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.sondar.core.parser.DOMParser;
import ru.sondar.core.parser.exception.ObjectStructureException;
import ru.sondar.documentmodel.dependencymodel.DependencyPart;
import ru.sondar.documentmodel.objectmodel.SDHeadPart;
import ru.sondar.documentmodel.objectmodel.SDLogPart;
import ru.sondar.documentmodel.objectmodel.SDWordsBasePart;

/**
 * Expansion for DOM parser object {@link ru.sondar.core.DOMParser } with
 * document partitions support
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SDDOMParser extends DOMParser {

    public SDDOMParser(String fileName) throws SAXException, IOException, ParserConfigurationException {
        super(fileName);
    }

    /**
     * Get Sequence of document
     *
     * @param xmlSequence get sequence part from document by "%fileName%"
     * @throws ObjectStructureException
     */
    public void getSequence(SDSequenceObject xmlSequence) throws ObjectStructureException {
        NodeList nList = document.getElementsByTagName(SDSequenceObject.Sequence_MainTag);
        Element sequence = ((Element) nList.item(0));
        xmlSequence.parseSequence(sequence);
    }

    /**
     * Get Head part of document
     *
     * @param headPart get head part from document by "%fileName%"
     * @throws ObjectStructureException
     */
    public void getHeadPart(SDHeadPart headPart) throws ObjectStructureException {
        NodeList nList = document.getElementsByTagName(SDHeadPart.Tag_MainObject);
        Element head = ((Element) nList.item(0));
        headPart.parseObjectFromXML(head);
    }

    /**
     * Get words base part of document
     *
     * @param wordsBase get words base part from document by "%fileName%"
     * @throws ObjectStructureException
     */
    public void getWordsBasePart(SDWordsBasePart wordsBase) throws ObjectStructureException {
        NodeList nList = document.getElementsByTagName(SDWordsBasePart.Tag_MainObject);
        Element head = ((Element) nList.item(0));
        wordsBase.parseObjectFromXML(head);
    }

    /**
     * Get Log part of document
     *
     * @param logPart get log part from document by "%fileName%"
     * @throws ObjectStructureException
     */
    public void getLogPart(SDLogPart logPart) throws ObjectStructureException {
        NodeList nList = document.getElementsByTagName(SDLogPart.Log_MainTag);
        Element log = ((Element) nList.item(0));
        logPart.parseObjectFromXML(log);
    }

    /**
     * Get dependency part of document
     *
     * @param dependencyPart get dependency part from document by "%fileName%"
     * @throws ObjectStructureException
     */
    public void getDependencyPart(DependencyPart dependencyPart) throws ObjectStructureException {
        NodeList nList = document.getElementsByTagName(DependencyPart.Dependency_MainTag);
        Element log = ((Element) nList.item(0));
        dependencyPart.parseItemFromXML(log);
    }

}
