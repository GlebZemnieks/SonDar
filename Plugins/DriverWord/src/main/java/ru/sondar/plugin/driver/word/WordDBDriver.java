package ru.sondar.plugin.driver.word;

import java.io.FileNotFoundException;
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
     * Driver configuration object
     */
    private WordConfiguration configuration;
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

    public static void main(String... args) throws FileNotFoundException, IOException {
        /*
        XWPFDocument docx = new XWPFDocument(
                new FileInputStream("E:\\Development\\SonDar\\SDServer\\JUnitTest\\testserver1\\test1\\SampleRaw.docx"));
        //using XWPFWordExtractor Class
        XWPFWordExtractor we = new XWPFWordExtractor(docx);
        String test = we.getText();
        System.out.println(we.getText());
        
        System.out.println("createdocument.docx written successully");
        
        String valueTag = "DATE";
        String valueTag2 = "FOND";
        test = test.replaceFirst("%" + valueTag + "%", "02.10.2010");
        test = test.replaceFirst("%" + valueTag2 + "%", "личный");
        
        //Blank Document
        XWPFDocument document= new XWPFDocument(); 
        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File("E:\\Development\\SonDar\\SDServer\\JUnitTest\\testserver1\\test1\\SampleRaw_temp.docx"));
        
        //Create a blank spreadsheet
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run=paragraph.createRun();
        run.setText(test);
        
        document.write(out);
        out.close();
        System.out.println("createdocument.docx written successully");
         */
        String test = "test";
        Test value = new Test();
        System.out.println("test : " + test(value).test);
        System.out.println("test : " + value.test);
    }

    static class Test {

        String test = "test";
    }

    public static Test test(Test test) {
        test.test += "test2";
        return test;
    }

    @Override
    public String toString() {
        return "File name \"" + this.configuration.fileName + "\"";
    }
}
