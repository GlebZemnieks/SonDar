package ru.sondar.plugin.driver.word;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import ru.sondar.plugin.driver.exception.DataBaseFileNotFoundException;

/**
 *
 * @author GlebZemnieks
 */
public class ProtectedDocument {

    /**
     * Apache POI word document object
     */
    private XWPFDocument docx;
    /**
     * Apache POI Word extractor object
     */
    private XWPFWordExtractor we;
    /**
     * Text of protected document
     */
    private String protectedText;

    public ProtectedDocument(String fileName) throws DataBaseFileNotFoundException {
        try {
            docx = new XWPFDocument(new FileInputStream(fileName));
            we = new XWPFWordExtractor(docx);
            protectedText = we.getText();
        } catch (IOException ex) {
            throw new DataBaseFileNotFoundException("Can't find file by path \"" + fileName + "\"", ex);
        }
    }
    
    public boolean isTagExist(String tagName){
        return (!this.protectedText.contains("%" + tagName + "%"));
    }
    
    public void replace(String tagName, String value){
        this.protectedText = protectedText.replaceFirst("%" + tagName + "%", value);
    }
}
