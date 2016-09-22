package ru.sondar.documentmodel.objectmodel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.sondar.core.exception.SonDarRuntimeException;
import ru.sondar.core.logger.Logger;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.documentmodel.objectmodel.exception.BaseWithNameNotExistException;

/**
 * Words base main class
 *
 * @author GlebZemnieks
 * @since SonDar-1.1
 */
public class SDWordsBasePart {

    /**
     * Tag for print and parse dataList field
     */
    public static String Tag_MainObject = "WordsBase";

    /**
     * Tag for print and parse dataList field
     */
    public static String Tag_DataList = "dataList";

    /**
     * Words base object. (WordsBaseName, WordBase)
     */
    private Map<String, WordBase> wordsBase;

    /**
     * Constructor
     */
    public SDWordsBasePart() {
        wordsBase = new HashMap<>();
    }

    /**
     * Getter for strings list field. If base with <code>key</code> not exist
     * throw SonDarRuntimeException
     *
     * @param key Name of words base
     * @return
     * @throws SonDarRuntimeException
     */
    public WordBase getList(String key) {
        if (wordsBase.containsKey(key)) {
            return wordsBase.get(key);
        } else {
            throw new BaseWithNameNotExistException("Base with name \"" + key + "\" not exist in list", key);
        }
    }

    /**
     * Getter for strings list field. If base with <code>key</code> not exist
     * throw SonDarRuntimeException
     *
     * @param i
     * @return
     * @throws SonDarRuntimeException
     */
    public WordBase getList(int i) {
        if (i < wordsBase.size()) {
            return (WordBase) (wordsBase.values().toArray()[i]);
        } else {
            throw new SonDarRuntimeException("Base with id \"" + i + "\" not exist in list");
        }
    }

    public void remove(String baseName) {
        this.wordsBase.remove(baseName);
    }

    public int size() {
        return wordsBase.size();
    }

    public Set<String> getBaseNames() {
        return wordsBase.keySet();
    }

    /**
     * Add new words base to object. If base with <code>name</code> already
     * exist in list throw RunTimeException
     *
     * @param name
     * @param base
     * @throws RuntimeException
     */
    public void addNewBase(String name, WordBase base) {
        if (!wordsBase.containsKey(name)) {
            base.setBaseName(name);
            this.wordsBase.put(name, base);
        } else {
            throw new RuntimeException("Base with name \"" + name + "\" already exist in list");
        }
    }

    /**
     * Public method for parser XML string
     *
     * @param element
     */
    public void parseObjectFromXML(Element element) {
        NodeList list = element.getElementsByTagName(Tag_DataList);
        for (int i = 0; i < list.getLength(); i++) {
            WordBase temp = new WordBase();
            temp.parseObjectFromXML((Element) list.item(i));
            String name = ((Element) list.item(i)).getAttribute("name");
            addNewBase(name, temp);
        }
    }

    /**
     * An class print XML string in file
     *
     * @param fileModule
     */
    public void printObjectToXML(FileModuleWriteThreadInterface fileModule) {
        Logger.Log("SDWordsBasePart::printObjectToXML", "Write wordsBase : " + this.toString());
        fileModule.write("<" + this.Tag_MainObject + ">\n");
        for (WordBase base : this.wordsBase.values()) {
            base.printObjectToXML(fileModule);
        }
        fileModule.write("</" + Tag_MainObject + ">\n");
    }

}
