package ru.sondar.core;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
    protected final Document document;

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
        Config.Log(logTag, "Try to open file \"" + fileName + "\"");
        File inputFile = new File(fileName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Config.Log(logTag, "Try to parse file \"" + fileName + "\"");
        document = builder.parse(inputFile);
        Config.Log(logTag, "Parsing was successfully. Object ready!");
    }

    /**
     * Get root element of file in document
     *
     * @return
     */
    public Element getRootElement() {
        return document.getDocumentElement();
    }
}
