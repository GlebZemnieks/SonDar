package ru.sondar.core.parser;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * DOM parser for SONDAR project
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class DOMParser {

    /**
     * Tag to search activity this object in logging file
     */
    String logTag = "Parser";

    /**
     * Object of document model in DOM
     */
    protected Document document;

    /**
     * Empty constructor
     */
    public DOMParser() {
    }

    /**
     * Constructor. Object of parser must to use once and should be created for
     * one file.
     *
     * @param fileName Filename with full path to file and format.
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public DOMParser(String fileName) throws SAXException, IOException, ParserConfigurationException {
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

    public Element getRootElement(String content) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(content);
        return document.getDocumentElement();
    }

    /**
     * Return NodeList by format <code><br>
     * (rootTag)<br>
     * (subTag)<br>
     * (tag)Node(/tag)<br>
     * (tag)Node2(/tag)<br>
     * .
     * ..<br>
     * (/subTag)<br>
     * (/rootTag)<br>
     * </code> Support only one subTag in root object. Count of tag not limit.
     *
     * @param subTag
     * @param tag
     * @return
     */
    public NodeList getNodeList(String subTag, String tag) {
        Element element = this.getRootElement();
        NodeList temp = element.getElementsByTagName(subTag);
        temp = ((Element) temp.item(0)).getElementsByTagName(tag);
        return temp;
    }
}
