package ru.sondar.plugin.driver.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import ru.sondar.core.logger.Logger;
import ru.sondar.plugin.driver.*;
import ru.sondar.plugin.driver.exception.*;

/**
 * Driver to import document to excel document and import form document
 *
 * @author GlebZemnieks
 */
public class ExcelDBDriver extends DBDriverInterface {

    /**
     * Domain folder to data base
     */
    private String folderName;

    /**
     * Data base file object
     */
    private File dataBase;
    /**
     * POIFS file system object
     */
    private NPOIFSFileSystem fileSystem;
    /**
     * HSSF word book object
     */
    private HSSFWorkbook workBook;

    /**
     * Constructor
     *
     * @param foldeName
     * @param element
     */
    public ExcelDBDriver(String foldeName, Element element) {
        this.functionality.add(DriverFunctionality.Import);
        this.functionality.add(DriverFunctionality.Export);
        try {
            this.folderName = foldeName;
            configuration = new ExcelConfiguration(element);
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.Log("Excel driver", "Trouble with parse configuration", ex);
        }
    }

    @Override
    public void connectToDB() throws DataBaseFileNotFoundException {
        try {
            this.dataBase = new File(this.folderName + "\\" + this.configuration.fileName);
            this.fileSystem = new NPOIFSFileSystem(new FileInputStream(dataBase));
            this.workBook = new HSSFWorkbook(new FileInputStream(dataBase));
        } catch (IOException ex) {
            Logger.Log("Excel driver", "Trouble with connection", ex);
        }
    }

    /**
     * Working sheet for next operation
     */
    private int activeSheet = 0;

    /**
     * Setter for active sheet field
     *
     * @param sheetId
     */
    public void setActiveSheet(int sheetId) {
        if (((ExcelConfiguration) this.configuration).isSheetExist(sheetId)) {
            this.activeSheet = sheetId;
        } else {
            throw new SheetNotSupportedException("Try to activate sheet id \"" + sheetId + "\" with configuration list " + this.configuration.toString());
        }
    }

    @Override
    public DBRowInterface getRowByKey(Object key) throws RowNotFoundException {
        HSSFSheet sheet = this.workBook.getSheetAt(activeSheet);
        Iterator<Row> rows = sheet.rowIterator();
        if (rows.hasNext()) {
            rows.next();
        }
        boolean isKeyFound = false;
        while (!isKeyFound && rows.hasNext()) {
            HSSFRow row = (HSSFRow) rows.next();
            HSSFCell keyCell = row.getCell(this.configuration.getKeyCellId(activeSheet));
            if (((String) key).equals(keyCell.getStringCellValue())) {
                return new HSSFRowImplements(row);
            }
        }
        throw new RowNotFoundException();
    }

    @Override
    public DBRowInterface createNewRowInDB(Object newKey) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DBDriverConfiguration getConfiguration() {
        return this.configuration;
    }

    @Override
    public ArrayList<Object> getKeyList() {
        HSSFSheet sheet = this.workBook.getSheetAt(activeSheet);
        Iterator<Row> rows = sheet.rowIterator();
        if (rows.hasNext()) {
            rows.next();
        }
        ArrayList<Object> temp = new ArrayList<>();
        while (rows.hasNext()) {
            HSSFRow row = (HSSFRow) rows.next();
            HSSFCell keyCell = row.getCell(this.configuration.getKeyCellId(activeSheet));
            temp.add(keyCell.getStringCellValue());
        }
        return temp;
    }

    @Override
    public void closeConnection() {
        try {
            this.fileSystem.writeFilesystem(new FileOutputStream(dataBase));
            this.workBook.write(new FileOutputStream(dataBase));
            this.fileSystem.close();
            this.workBook.close();
        } catch (IOException ex) {
            Logger.Log("Excel driver", "Trouble with closing connection", ex);
        }
    }

    @Override
    public String toString() {
        String temp = "File name \"" + this.configuration.fileName + "\"\n";
        for (int i = 0; i < ((ExcelConfiguration) this.configuration).sheetConfigurations.size(); i++) {
            temp += "\t\t\t\t" + (((ExcelConfiguration) this.configuration).sheetConfigurations.get(i).toString());
        }
        return temp;
    }
}
