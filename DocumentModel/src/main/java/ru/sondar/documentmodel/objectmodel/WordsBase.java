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
public class WordsBase {

    /**
     * Tag for print and parse dataList field
     */
    public static String Tag_MainObject = "WordsBase";

    /**
     * Tag for print and parse dataList field
     */
    public static String Tag_DataList = "dataList";
    /**
     * Tag for print and parse item filed
     */
    public static String Tag_Item = "item";

    /**
     * Words base object
     */
    private Map<String, ArrayList<String>> wordsBase;

    /**
     * Constructor
     */
    public WordsBase() {
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
    public ArrayList<String> getList(String key) {
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
    public void addNewBase(String name, ArrayList<String> base) {
        if (!wordsBase.containsKey(name)) {
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
            NodeList list2 = ((Element) list.item(i)).getElementsByTagName(Tag_Item);
            ArrayList<String> temp = new ArrayList<>();
            for (int j = 0; j < list2.getLength(); j++) {
                temp.add(((Element) list2.item(j)).getTextContent());
            }
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
        for (Object object : this.wordsBase.keySet()) {
            fileModule.write("<" + Tag_DataList + " name=\"" + object.toString() + "\">\n");
            for (String item : this.wordsBase.get(object)) {
                fileModule.write("<" + Tag_Item + ">" + item + "</" + Tag_Item + ">\n");
            }
            fileModule.write("</" + Tag_DataList + ">\n");
        }
        fileModule.write("</" + Tag_MainObject + ">\n");
    }

}
