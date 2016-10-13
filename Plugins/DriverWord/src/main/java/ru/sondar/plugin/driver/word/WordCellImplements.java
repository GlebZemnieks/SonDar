package ru.sondar.plugin.driver.word;

import ru.sondar.plugin.driver.DBCellInterface;

/**
 *
 * @author GlebZemnieks
 */
public class WordCellImplements implements DBCellInterface {

    public String tagName;
    ProtectedDocument document;

    public WordCellImplements(String tagName, ProtectedDocument document) {
        this.tagName = tagName;
        this.document = document;
    }

    @Override
    public void setCellValue(Object value) {
        document.replace(tagName, (String) value);
    }

    @Override
    public Object getCellValue() {
        throw new UnsupportedOperationException("Not supported in with driver");
    }

}
