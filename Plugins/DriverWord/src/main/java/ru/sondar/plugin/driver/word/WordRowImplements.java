package ru.sondar.plugin.driver.word;

import ru.sondar.plugin.driver.DBCellInterface;
import ru.sondar.plugin.driver.DBRowInterface;
import ru.sondar.plugin.driver.exception.CellNotFoundException;

/**
 *
 * @author GlebZemnieks
 */
public class WordRowImplements implements DBRowInterface {

    private final ProtectedDocument protectedDocument;

    public WordRowImplements(ProtectedDocument text) {
        this.protectedDocument = text;
    }

    @Override
    public DBCellInterface getCellById(Object key) {
        if (protectedDocument.isTagExist((String) key)) {
            return new WordCellImplements((String) key, protectedDocument);
        } else {
            throw new CellNotFoundException("Can't find cell by key \"" + key + "\"");
        }
    }

}
