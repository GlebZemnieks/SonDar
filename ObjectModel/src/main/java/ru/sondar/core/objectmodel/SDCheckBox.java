package ru.sondar.core.objectmodel;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.core.objectmodel.exception.NoFieldException;
import ru.sondar.core.objectmodel.exception.ObjectStructureException;

/**
 *
 * @author GlebZemnieks
 */
public class SDCheckBox extends SDMainObject {

    /**
     * Tag to print and parse text field
     */
    public static String CheckBox_textFieldTag = "text";
    /**
     * Tag to print and parse checked field
     */
    public static String CheckBox_defaultCheck = "default";

    /**
     * Text field
     */
    private String Text;

    /**
     * Getter for text field
     *
     * @return
     */
    public String getText() {
        return this.Text;
    }

    /**
     * Setter for text field
     *
     * @param newText
     */
    public void setText(String newText) {
        this.Text = newText;
    }
    /**
     * Checked field
     */
    private boolean checked;

    /**
     * Getter for checked field
     *
     * @return
     */
    public boolean getChecked() {
        return this.checked;
    }

    /**
     * Setter for checked field
     *
     * @param newValue
     */
    public void setChecked(boolean newValue) {
        this.checked = newValue;
    }

    /**
     * Constructor
     */
    public SDCheckBox() {
        this.objectType = SDMainObjectType.CheckBox;
    }

    @Override
    protected void parseCurrentObjectField(Element element) throws ObjectStructureException {
        NodeList list = element.getElementsByTagName(CheckBox_textFieldTag);
        if (list.item(0) != null) {
            this.setText(list.item(0).getTextContent());
        } else {
            throw new NoFieldException("Missing \"text\" field");
        }
        list = element.getElementsByTagName(CheckBox_defaultCheck);
        if (list.item(0) != null) {
            this.setChecked(Boolean.valueOf(list.item(0).getTextContent()));
        } else {
            throw new NoFieldException("Missing \"default\" field");
        }
    }

    @Override
    protected void printCurrentObjectField(FileModuleWriteThread fileModule) {
        fileModule.write("<" + CheckBox_textFieldTag + ">" + this.Text + "</" + CheckBox_textFieldTag + ">\n"
                + "<" + CheckBox_defaultCheck + ">" + this.checked + "</" + CheckBox_defaultCheck + ">\n");
    }

}
