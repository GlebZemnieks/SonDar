package ru.sondar.core.documentmodel;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.sondar.core.objectmodel.SDHeadPart;
import ru.sondar.core.objectmodel.SDLogPart;
import ru.sondar.core.objectmodel.exception.ObjectStructureException;

/**
 * DOM parser for document
 *
 * @author GlebZemnieks
 */
public class SDDOMParser {

    /**
     * Tag to search activity this object in logging file
     */
    String logTag = "Parser";

    /**
     * Object of document model in DOM
     */
    private final Document document;

    /**
     * Constructor. Object of parser must to use once and should be created for
     * one file
     *
     * @param fileName
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public SDDOMParser(String fileName) throws SAXException, IOException, ParserConfigurationException {
        File inputFile = new File(fileName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(inputFile);
    }

    /**
     * Get root element of file in document
     *
     * @return
     */
    public Element getRootElement() {
        return document.getDocumentElement();
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

}
