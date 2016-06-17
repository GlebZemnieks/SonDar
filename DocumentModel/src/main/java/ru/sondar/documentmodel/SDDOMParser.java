package ru.sondar.documentmodel;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.sondar.core.DOMParser;
import ru.sondar.documentmodel.dependencymodel.DependencyPart;
import ru.sondar.documentmodel.objectmodel.SDHeadPart;
import ru.sondar.documentmodel.objectmodel.SDLogPart;
import ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException;

/**
 * DOM parser for document
 *
 * @author GlebZemnieks
 */
public class SDDOMParser extends DOMParser {

    public SDDOMParser(String fileName) throws SAXException, IOException, ParserConfigurationException {
        super(fileName);
    }

    /**
     * Get Sequence of document
     *
     * @param xmlSequence
     * @throws ObjectStructureException
     */
    public void getSequence(SDSequenceObject xmlSequence) throws ObjectStructureException {
        NodeList nList = document.getElementsByTagName("XMLSequence");
        Element sequence = ((Element) nList.item(0));
        xmlSequence.parseSequence(sequence);
    }

    /**
     * Get Head part of document
     *
     * @param headPart
     * @throws ObjectStructureException
     */
    public void getHeadPart(SDHeadPart headPart) throws ObjectStructureException {
        NodeList nList = document.getElementsByTagName("head");
        Element head = ((Element) nList.item(0));
        headPart.parseObjectFromXML(head);
    }

    /**
     * Get Log part of document
     *
     * @param logPart
     * @throws ObjectStructureException
     */
    public void getLogPart(SDLogPart logPart) throws ObjectStructureException {
        NodeList nList = document.getElementsByTagName("Log");
        Element log = ((Element) nList.item(0));
        logPart.parseObjectFromXML(log);
    }
    
    /**
     * Get dependency part of document
     *
     * @param dependencyPart
     * @throws ObjectStructureException
     */
    public void getDependencyPart(DependencyPart dependencyPart) throws ObjectStructureException {
        NodeList nList = document.getElementsByTagName("DependencyPart");
        Element log = ((Element) nList.item(0));
        dependencyPart.parseItemFromXML(log);
    }

}
