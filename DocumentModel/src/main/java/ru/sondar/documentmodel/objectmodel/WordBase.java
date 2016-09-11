package ru.sondar.documentmodel.objectmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.sondar.core.exception.SonDarRuntimeException;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import static ru.sondar.documentmodel.objectmodel.SDWordsBasePart.Tag_DataList;

/**
 *
 * @author GlebZemnieks
 */
public class WordBase {

    /**
     * Words base object. (FilterName, Items). For add item without filter use
     * name <code>null</code>
     */
    private Map<String, ArrayList<String>> base;

    /**
     * Tag for print and parse item filed
     */
    public static String Tag_Item = "item";
    /**
     * Tag for print and parse filter filed
     */
    public static String Tag_Filter = "filter";

    private String wordBaseName;

    public void setBaseName(String name) {
        this.wordBaseName = name;
    }

    public WordBase() {
        base = new HashMap<>();
        base.put(null, new ArrayList<String>());
    }

    public void add(String item) {
        this.add(null, item);
    }

    public String get(int i) {
        return this.getList(null).get(i);
    }

    public int size() {
        return this.getList(null).size();
    }

    public boolean isFilterExist(String name) {
        return base.containsKey(name);
    }

    public Set<String> getFilterNames() {
        return base.keySet();
    }

    public void addFilter(String filter) {
        if (isFilterExist(filter)) {
            throw new SonDarRuntimeException("Filter with name \"" + filter + "\" already exist in words base");
        }
        base.put(filter, new ArrayList<String>());
    }

    public void removeFilter(String filter) {
        if (!isFilterExist(filter)) {
            throw new SonDarRuntimeException("Filter with name \"" + filter + "\" not exist in words base");
        }
        base.remove(filter);
    }

    public void removeItem(String filter, String item) {
        if (!isFilterExist(filter)) {
            throw new SonDarRuntimeException("Filter with name \"" + filter + "\" not exist in words base");
        }
        this.base.get(filter).remove(item);
    }

    public void add(String filter, String item) {
        if (isFilterExist(filter)) {
            //Add element to current filter list
            base.get(filter).add(item);
        } else {
            //Create new filter list with current item
            ArrayList<String> temp = new ArrayList<>();
            temp.add(item);
            base.put(filter, temp);
        }
    }

    public ArrayList<String> getList(String filter) {
        if (!isFilterExist(filter)) {
            throw new SonDarRuntimeException("Filter with name \"" + filter + "\" not exist in words base");
        }
        ArrayList<String> temp = new ArrayList<>();
        if (filter == null) {
            for (String name : base.keySet()) {
                temp.addAll(base.get(name));
            }
        } else {
            temp.addAll(base.get(null));
            temp.addAll(base.get(filter));
        }
        return temp;
    }

    public ArrayList<String> getListRaw(String filter) {
        return base.get(filter);
    }

    public void parseObjectFromXML(Element element) {
        this.parseObjectFromXML(element, null);
    }

    private void parseObjectFromXML(Element element, String filter) {
        wordBaseName = element.getAttribute("name");
        NodeList list = element.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeName().equals(Tag_Item)) {
                add(filter, list.item(i).getTextContent());
            }
            if (filter == null && list.item(i).getNodeName().equals(Tag_Filter)) {
                this.parseObjectFromXML((Element) list.item(i), ((Element) list.item(i)).getAttribute("name"));
            }
        }
    }

    public void printObjectToXML(FileModuleWriteThreadInterface fileModule) {
        fileModule.write("<" + Tag_DataList + " name=\"" + wordBaseName + "\">\n");
        for (String filter : this.base.keySet()) {
            if (filter != null) {
                fileModule.write("<" + Tag_Filter + " name=\"" + filter + "\">\n");
            }
            for (String item : base.get(filter)) {
                fileModule.write("<" + Tag_Item + ">" + item + "</" + Tag_Item + ">\n");
            }
            if (filter != null) {
                fileModule.write("</" + Tag_Filter + ">\n");
            }
        }
        fileModule.write("</" + Tag_DataList + ">\n");
    }

    @Override
    public String toString() {
        String temp = wordBaseName + ":\n";
        for (String filter : base.keySet()) {
            temp += "Filter : " + filter + " : " + base.get(filter).toString() + "\n";
        }
        return temp;
    }
}
