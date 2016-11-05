package ru.sondar.documentmodel.dependencymodel;

/**
 * Object for link between SDMainObject and Cell in DB
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
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

    public DependencyItem() {
        //Empty constructor
    }

    public DependencyItem(String objectName, int cellId) {
        this.objectName = objectName;
        this.cellId = cellId;
    }

    @Override
    public String toString() {
        return "object \"" + this.objectName + "\" to cell \"" + this.cellId + "\"";
    }

}
