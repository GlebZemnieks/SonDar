package ru.sondar.documentmodel.objectmodel;

import java.util.ArrayList;
import ru.sondar.core.logger.Logger;
import ru.sondar.core.exception.SonDarRuntimeException;
import ru.sondar.documentmodel.dependencymodel.SupportDependencyInterface;

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
    private WordBase wordBase;

    private String activeFilter;

    public void setActiveFilter(String filter) {
        if (!wordBase.isFilterExist(filter)) {
            throw new SonDarRuntimeException("Filter with name \"" + filter + "\" not exist in words base");
        }
        this.activeFilter = filter;
        this.defaultItemSelected = 0;
    }

    public String getActiveFilter() {
        return activeFilter;
    }

    public String getActiveFilterAttribute() {
        Logger.Log("SDSpinner::getActiveFilterAttribute", "Return value : '" + activeFilter + "'");
        if (activeFilter != null) {
            return " activeFilter=\"" + activeFilter + "\"";
        }
        return "";
    }

    /**
     * Getter for data list
     *
     * @return
     */
    public ArrayList<String> getList() {
        return wordBase.getList(activeFilter);
    }

    /**
     * Setter for data list
     *
     * @param dataList In ArrayList format
     */
    public void setList(WordBase dataList) {
        this.wordBase = dataList;
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
        if (defaultItemSelected >= 0 && defaultItemSelected < this.wordBase.getList(activeFilter).size()) {
            this.defaultItemSelected = defaultItemSelected;
        } else {
            throw new IllegalArgumentException("Try to set \"" + defaultItemSelected + "\" to list with length \"" + this.wordBase.getList(activeFilter).size() + "\"");
        }
    }

    /**
     * Return selected item object
     *
     * @return
     */
    public String getSelectedItem() {
        return this.wordBase.getList(activeFilter).get(this.defaultItemSelected);
    }

    /**
     * Name of words base list
     */
    private String wordsBaseName;

    public void setWordsBaseName(String newName) {
        this.wordsBaseName = newName;
    }

    public String getWordsBaseNameAttribute() {
        Logger.Log("SDSpinner::getWordsBaseNameAttribute", "Return value : '" + wordsBaseName + "'");
        if (wordsBaseName != null) {
            return " baseName=\"" + wordsBaseName + "\"";
        }
        return "";
    }

    /**
     * Constructor
     */
    public SDSpinner() {
        this.objectType = SDMainObjectType.Spinner;
        this.wordBase = new WordBase();
        this.wordBase.add("item1");
        this.wordBase.add("item2");
        this.activeFilter = null;
    }

    // Start SupportDependency Interface
    @Override
    public Object getValue() {
        return this.defaultItemSelected;
    }

    @Override
    public void setValue(Object object) {
        this.setDefaultItemSelected(Integer.parseInt((String) object));
    }
    // End SupportDependency Interface

    @Override
    public String toString() {
        return super.toString() + " : wordsBaseName : " + this.wordsBaseName + " : activeFilter : " + this.activeFilter;
    }

}
