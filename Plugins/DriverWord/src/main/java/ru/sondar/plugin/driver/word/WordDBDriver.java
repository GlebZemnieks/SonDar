package ru.sondar.plugin.driver.word;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import ru.sondar.core.logger.Logger;
import ru.sondar.plugin.driver.DBDriverConfiguration;
import ru.sondar.plugin.driver.DBDriverInterface;
import ru.sondar.plugin.driver.DBRowInterface;
import ru.sondar.plugin.driver.DriverFunctionality;
import ru.sondar.plugin.driver.exception.DataBaseFileNotFoundException;
import ru.sondar.plugin.driver.exception.RowNotFoundException;

/**
 *
 * @author GlebZemnieks
 */
public class WordDBDriver extends DBDriverInterface {

    /**
     * Domain folder to data base
     */
    private String folderName;

    private ProtectedDocument protectedDocument;

    public WordDBDriver(String foldeName, Element element) {
        this.functionality.add(DriverFunctionality.Export);
        try {
            this.folderName = foldeName;
            this.configuration = new WordConfiguration(element);
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.Log("Word driver", "Trouble with parse configuration", ex);
        }
    }

    @Override
    public void connectToDB() throws DataBaseFileNotFoundException {
        protectedDocument = new ProtectedDocument(folderName + "\\" + configuration.fileName);
    }

    @Override
    public DBRowInterface getRowByKey(Object key) throws RowNotFoundException {
        return new WordRowImplements(protectedDocument);
    }

    @Override
    public DBRowInterface createNewRowInDB(Object newKey) {
        throw new UnsupportedOperationException("Not supported in with driver");
    }

    @Override
    public ArrayList<Object> getKeyList() {
        throw new UnsupportedOperationException("Not supported in with driver");
    }

    @Override
    public DBDriverConfiguration getConfiguration() {
        return this.configuration;
    }

    @Override
    public void closeConnection() {
        //Empty
    }

    @Override
    public String toString() {
        return "File name \"" + this.configuration.fileName + "\"";
    }
}
