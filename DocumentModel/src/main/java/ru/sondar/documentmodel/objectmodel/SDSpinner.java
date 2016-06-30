package ru.sondar.documentmodel.objectmodel;

import java.util.ArrayList;
import java.util.Arrays;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.sondar.documentmodel.dependencymodel.SupportDependencyInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.documentmodel.objectmodel.exception.NoFieldException;
import ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException;

/**
 * SDSpinner object
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SDSpinner extends SDMainObject implements SupportDependencyInterface {

    /**
     * Tag for print and parse dataList field
     */
    public static String Spinner_DataList = "dataList";
    /**
     * Tag for print and parse item field
     */
    public static String Spinner_Item = "item";
    /**
     * Tag for print and parse ItemSelected field
     */
    public static String Spinner_defaultItemSelected = "ItemSelected";

    /**
     * Selected item index field
     */
    private int defaultItemSelected;

    /**
     * Data list field
     */
    private ArrayList<String> dataList;

    /**
     * Getter for data list
     *
     * @return
     */
    public ArrayList<String> getList() {
        return dataList;
    }

    /**
     * Setter for data list
     *
     * @param dataList in String sequence format
     */
    public void setList(String[] dataList) {
        ArrayList<String> temp = new ArrayList<>();
        temp.addAll(Arrays.asList(dataList));
        this.dataList = temp;
    }

    /**
     * Setter for data list
     *
     * @param dataList In ArrayList format
     */
    public void setList(ArrayList<String> dataList) {
        this.dataList = dataList;
    }

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
        return this.dataList.get(this.defaultItemSelected);
    }

    /**
     * Name of words base list
     */
    private String wordsBaseName;

    private String getWordsBaseNameAttribute() {
        if (wordsBaseName != null) {
            return " baseName=\"wordsBaseName\"";
        }
        return "";
    }

    /**
     * Constructor
     */
    public SDSpinner() {
        this.objectType = SDMainObjectType.Spinner;
        this.dataList = new ArrayList<>();
        this.dataList.add("item1");
        this.dataList.add("item2");
    }

    // Start SupportDependency Interface
    @Override
    public Object getValue() {
        return this.defaultItemSelected;
    }

    @Override
    public void setValue(Object object) {
        int value = Integer.parseInt((String) object);
        if (value >= 0 && value < this.dataList.size()) {
            this.defaultItemSelected = value;
        } else {
            throw new IllegalArgumentException("Try to set \"" + value + "\" to list with length \"" + this.dataList.size() + "\"");
        }
    }
    // End SupportDependency Interface

    @Override
    protected void parseCurrentObjectField(Element element) throws ObjectStructureException {
        NodeList list = element.getElementsByTagName(Spinner_DataList);
        if (list.item(0) != null) {
            if (((Element) list.item(0)).hasAttribute("baseName")) {
                //Have link to words base
                this.setList(this.sequence.document.getWordsBasePart().getList(((Element) list.item(0)).getAttribute("baseName")));
            } else {
                //All words base in current object
                this.setList(getStringArray((Element) list.item(0)));
            }
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
        fileModule.write("<" + Spinner_DataList + this.getWordsBaseNameAttribute() + ">\n");
        if (wordsBaseName == null) {
            for (String data : this.dataList) {
                fileModule.write("<" + Spinner_Item + ">" + data + "</" + Spinner_Item + ">\n");
            }
        }
        fileModule.write("</" + Spinner_DataList + ">\n"
                + "<" + Spinner_defaultItemSelected + ">" + this.defaultItemSelected + "</" + Spinner_defaultItemSelected + ">\n");
    }

}
