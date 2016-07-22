package ru.sondar.documentmodel.objectmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;

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
     * throw RunTimeException
     *
     * @param key Name of words base
     * @return
     * @throws RuntimeException
     */
    public WordBase getList(String key) {
        if (wordsBase.containsKey(key)) {
            return wordsBase.get(key);
        } else {
            throw new RuntimeException("Base with name \"" + key + "\" not exist in list");
        }
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
        fileModule.write("<" + this.Tag_MainObject + ">\n");
        for (WordBase base : this.wordsBase.values()) {
            base.printObjectToXML(fileModule);
        }
        fileModule.write("</" + Tag_MainObject + ">\n");
    }

}
