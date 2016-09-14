package ru.sondar.documentmodel.dependencymodel;

import java.util.ArrayList;
import java.util.Iterator;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.sondar.core.logger.Logger;
import static ru.sondar.documentmodel.dependencymodel.DependencyItem.DependencyItem_xmlTag;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.documentmodel.SDSequenceObject;
import ru.sondar.documentmodel.exception.ObjectNotFountException;

/**
 * Object for store dependency item and iteration for them
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class DependencyPart implements Iterable<DependencyItem> {

    /**
     * Tag for print and parse object
     */
    public static String Dependency_MainTag = "DependencyPart";

    /**
     * Dependency list object
     */
    private ArrayList<DependencyItem> dependencyList = new ArrayList<>();

    public void addDependencyItem(String name, int cellId) {
        this.dependencyList.add(new DependencyItem(name, cellId));
    }

    public void addDependencyItemWithValidation(SDSequenceObject sequence, String name, int cellId) {
        if (!sequence.isObjectWithNameExist(name)) {
            throw new ObjectNotFountException("Try to set dependency to object with name \"" + name + "\" fail. Object not found.");
        }
        addDependencyItem(name, cellId);
    }

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

    /**
     * Method for print item to XML
     *
     * @param fileModule
     */
    public void printObjectToXML(FileModuleWriteThreadInterface fileModule) {
        Logger.Log("DependencyPart::printObjectToXML", "Write dependency : " + this.toString());
        fileModule.write("<" + Dependency_MainTag + ">\n");
        for (int i = 0; i < this.dependencyList.size(); i++) {
            this.dependencyList.get(i).printObjectToXML(fileModule);
        }
        fileModule.write("</" + Dependency_MainTag + ">\n");
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
