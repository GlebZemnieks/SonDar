package ru.sondar.plugin.driver.word;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import ru.sondar.plugin.driver.DBDriverConfiguration;
import ru.sondar.plugin.driver.exception.NoSheetAttributeException;

/**
 *
 * @author GlebZemnieks
 */
public class WordConfiguration extends DBDriverConfiguration {

    @Override
    public int getKeyCellId(int sheetId) {
        throw new UnsupportedOperationException("Not supported in with driver"); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Constructor
     *
     * @param element
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public WordConfiguration(Element element) throws SAXException, IOException, ParserConfigurationException {
        if (!"".equals(element.getAttribute("fileName"))) {
            this.fileName = (element.getAttribute("fileName"));
        } else {
            throw new NoSheetAttributeException("Attribute \"fileName\" in root element");
        }
    }

}
