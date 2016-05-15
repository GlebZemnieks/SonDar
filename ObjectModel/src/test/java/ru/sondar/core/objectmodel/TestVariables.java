package ru.sondar.core.objectmodel;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author GlebZemnieks
 */
public class TestVariables {

    /**
     * Path to folder with testing data
     */
    public static String testFolder = "E:\\Development\\SonDar\\ObjectModel\\JUnitTest\\";

    public static Element getRootElementByFile(String fileName) {
        return getRootElementByFile("", fileName);
    }

    public static Element getRootElementByFile(String folderName, String fileName) {
        try {
            File inputFile = new File(testFolder + folderName + "\\" + fileName);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();
            return doc.getDocumentElement();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(TestVariables.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
