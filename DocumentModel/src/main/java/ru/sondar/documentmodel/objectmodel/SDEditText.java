package ru.sondar.documentmodel.objectmodel;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.sondar.documentmodel.dependencymodel.SupportDependencyInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.documentmodel.internalfunction.interfaces.SetterInterface;
import ru.sondar.documentmodel.objectmodel.exception.NoFieldException;
import ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException;

/**
 * SDEditText object
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SDEditText extends SDMainObject
        implements SupportDependencyInterface, SetterInterface {

    /**
     * Tag for print and parse text filed
     */
    public static String EditText_textFieldTag = "textEdit";
    /**
     * Tag for print and parse length filed
     */
    public static String EditText_textLengthTag = "textLength";

    /**
     * Text field
     */
    private String Text;

    /**
     * Length field
     */
    private int textLength = 0;

    /**
     * Constructor
     */
    public SDEditText() {
        this.objectType = SDMainObjectType.EditText;
    }

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
     * Setter for length field
     *
     * @param length
     */
    public void setTextLength(int length) {
        this.textLength = length;
    }

    /**
     * Getter for length field
     *
     * @return
     */
    public int getTextLength() {
        return this.textLength;
    }

    // Start SupportDependency Interface
    @Override
    public Object getValue() {
        return this.Text;
    }

    @Override
    public void setValue(Object object) {
        this.Text = (String) object;
    }
    // End SupportDependency Interface

    // Start SetterInterface Interface
    @Override
    public void setValueByAction(Object obj) {
        this.setValue(obj);
    }
    // End SetterInterface Interface

    @Override
    protected void parseCurrentObjectField(Element element) throws ObjectStructureException {
        NodeList list = element.getElementsByTagName(EditText_textFieldTag);
        if (list.item(0) != null) {
            this.setText(list.item(0).getTextContent());
            this.Text = this.Text.replaceAll("Zzz", "\n");
        } else {
            throw new NoFieldException("Missing \"text\" field");
        }
        list = element.getElementsByTagName(EditText_textLengthTag);
        if (list.item(0) != null) {
            this.setTextLength(Integer.valueOf(list.item(0).getTextContent()));
        } else {
            throw new NoFieldException("Missing \"length\" field");
        }
    }

    @Override
    protected void printCurrentObjectField(FileModuleWriteThreadInterface fileModule) {
        this.Text = this.Text.replaceAll("\n", "Zzz");
        fileModule.write("<" + EditText_textFieldTag + ">" + this.Text + "</" + EditText_textFieldTag + ">\n"
                + "<" + EditText_textLengthTag + ">" + this.textLength + "</" + EditText_textLengthTag + ">\n");
    }

}
