package ru.sondar.core.dependencymodel;

import org.w3c.dom.Element;
import ru.sondar.core.dependencymodel.exception.MissingDependencyAttributeException;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;

/**
 * Object for link between SDMainObject and Cell in DB
 *
 * @author GlebZemnieks
 */
public class DependencyItem {

    /**
     * Tag for print and parse object
     */
    public static String DependencyItem_xmlTag = "link";

    /**
     * Name of SDObject
     */
    public String objectName;

    /**
     * Cell Id
     */
    public int cellId;

    public void parseItemFromXML(Element element) {
        if (!"".equals(element.getAttribute("objectName"))) {
            this.objectName = (element.getAttribute("objectName"));
        } else {
            throw new MissingDependencyAttributeException("Attribute \"objectName\"");
        }
        if (!"".equals(element.getAttribute("cellId"))) {
            this.cellId = Integer.valueOf(element.getAttribute("cellId"));
        } else {
            throw new MissingDependencyAttributeException("Attribute \"cellId\"");
        }
    }

    public void printObjectToXML(FileModuleWriteThreadInterface fileModule) {
        fileModule.write("<" + DependencyItem_xmlTag + " objectName=\"" + this.objectName + "\" cellId=\"" + this.cellId + "\"></" + DependencyItem_xmlTag + ">\n");
    }

    @Override
    public String toString() {
        return "object \"" + this.objectName + "\" to cell \"" + this.cellId + "\"";
    }

}
