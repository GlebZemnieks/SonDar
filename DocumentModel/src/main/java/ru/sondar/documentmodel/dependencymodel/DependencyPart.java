package ru.sondar.documentmodel.dependencymodel;

import java.util.ArrayList;
import java.util.Iterator;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import static ru.sondar.documentmodel.dependencymodel.DependencyItem.DependencyItem_xmlTag;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;

/**
 * Object for store dependency item and iteration for them
 *
 * @author GlebZemnieks
 */
public class DependencyPart implements Iterable<DependencyItem> {

    /**
     * Tag for print and parse object
     */
    private final String xmlTag = "DependencyPart";

    /**
     * Dependency list object
     */
    private ArrayList<DependencyItem> dependencyList = new ArrayList<>();

    /**
     * Method for parse item from XML
     *
     * @param element
     */
    public void parseItemFromXML(Element element) {
        this.dependencyList = new ArrayList<>();
        NodeList list = element.getElementsByTagName(DependencyItem_xmlTag);
        for (int i = 0; i < list.getLength(); i++) {
            DependencyItem item = new DependencyItem();
            item.parseItemFromXML((Element) list.item(i));
            this.dependencyList.add(item);
        }
    }

    public void addDependencyItem(String name, int cellId) {
        this.dependencyList.add(new DependencyItem(name, cellId));
    }

    /**
     * Method for print item to XML
     *
     * @param fileModule
     */
    public void printObjectToXML(FileModuleWriteThreadInterface fileModule) {
        fileModule.write("<" + xmlTag + ">\n");
        for (int i = 0; i < this.dependencyList.size(); i++) {
            this.dependencyList.get(i).printObjectToXML(fileModule);
        }
        fileModule.write("</" + xmlTag + ">\n");
    }

    @Override
    public Iterator<DependencyItem> iterator() {
        return this.dependencyList.iterator();
    }

    @Override
    public String toString() {
        String temp = "Dependency : \n";
        for (int i = 0; i < this.dependencyList.size(); i++) {
            temp += this.dependencyList.get(i).toString() + "\n";
        }
        return temp;
    }

}
