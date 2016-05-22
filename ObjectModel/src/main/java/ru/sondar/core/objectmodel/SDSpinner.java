package ru.sondar.core.objectmodel;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.objectmodel.exception.NoFieldException;
import ru.sondar.core.objectmodel.exception.ObjectStructureException;

/**
 * Spinner object
 *
 * @author GlebZemnieks
 */
public class SDSpinner extends SDMainObject {

    /**
     * Tag for print and parse dataList filed
     */
    public static String Spinner_DataList = "dataList";
    /**
     * Tag for print and parse item filed
     */
    public static String Spinner_Item = "item";
    /**
     * Tag for print and parse ItemSelected filed
     */
    public static String Spinner_defaultItemSelected = "ItemSelected";

    /**
     * Data list field
     */
    private String[] dataList = {"item1", "item2"};

    /**
     * Getter for data list
     *
     * @return
     */
    public String[] getList() {
        return dataList;
    }

    /**
     * Setter for data list
     *
     * @param dataList
     */
    public void setList(String[] dataList) {
        this.dataList = dataList;
    }

    /**
     * Selected item index field
     */
    private int defaultItemSelected;

    /**
     * Getter for selected item index
     *
     * @return
     */
    public int getDefaultItemSelected() {
        return defaultItemSelected;
    }

    /**
     * Setter for selected item index
     *
     * @param defaultItemSelected
     */
    public void setDefaultItemSelected(int defaultItemSelected) {
        this.defaultItemSelected = defaultItemSelected;
    }

    /**
     * Return selected item object
     *
     * @return
     */
    public String getSelectedItem() {
        return this.dataList[this.defaultItemSelected];
    }

    /**
     * Constructor
     */
    public SDSpinner() {
        this.objectType = SDMainObjectType.Spinner;
    }

    @Override
    protected void parseCurrentObjectField(Element element) throws ObjectStructureException {
        NodeList list = element.getElementsByTagName(Spinner_DataList);
        if (list.item(0) != null) {
            this.setList(getStringArray((Element) list.item(0)));
        } else {
            throw new NoFieldException("Missing \"dataList\" field");
        }
        list = element.getElementsByTagName(Spinner_defaultItemSelected);
        if (list.item(0) != null) {
            this.setDefaultItemSelected(Integer.valueOf(list.item(0).getTextContent()));
        } else {
            throw new NoFieldException("Missing \"ItemSelected\" field");
        }
    }

    /**
     * Parse data list from internal object
     *
     * @param element
     * @return
     */
    private String[] getStringArray(Element element) {
        NodeList list = element.getElementsByTagName(Spinner_Item);
        String[] tempArray = new String[list.getLength()];
        for (int i = 0; i < list.getLength(); i++) {
            tempArray[i] = list.item(i).getTextContent();
        }
        return tempArray;
    }

    @Override
    protected void printCurrentObjectField(FileModuleWriteThreadInterface fileModule) {
        fileModule.write("<" + Spinner_DataList + ">\n");
        for (String data : this.dataList) {
            fileModule.write("<" + Spinner_Item + ">" + data + "</" + Spinner_Item + ">\n");
        }
        fileModule.write("</" + Spinner_DataList + ">\n"
                + "<" + Spinner_defaultItemSelected + ">" + this.defaultItemSelected + "</" + Spinner_defaultItemSelected + ">\n");
    }

}
