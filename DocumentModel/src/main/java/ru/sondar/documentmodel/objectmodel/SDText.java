package ru.sondar.documentmodel.objectmodel;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.documentmodel.objectmodel.exception.NoFieldException;
import ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException;

/**
 *
 * @author GlebZemnieks
 */
public class SDText extends SDMainObject {

    /**
     * Tag for print and parse text filed
     */
    public static String Text_textFieldTag = "text";
    /**
     * Text fields
     */
    private String Text = "";

    /**
     * Getter for text field
     *
     * @return
     */
    public String getText() {
        return this.Text;
    }

    /**
     * Setter fir text filed
     *
     * @param newText
     */
    public void setText(String newText) {
        this.Text = newText;
    }

    /**
     * Object constructor
     */
    public SDText() {
        this.objectType = SDMainObjectType.Text;
    }

    @Override
    protected void parseCurrentObjectField(Element element) throws ObjectStructureException {
        NodeList list = element.getElementsByTagName(Text_textFieldTag);
        if (list.item(0) != null) {
            this.setText(list.item(0).getTextContent());
        } else {
            throw new NoFieldException("Missing \"text\" field");
        }
    }

    @Override
    protected void printCurrentObjectField(FileModuleWriteThreadInterface fileModule) {
        fileModule.write("<" + Text_textFieldTag + ">" + this.Text + "</" + Text_textFieldTag + ">\n");
    }

    @Override
    public String toString() {
        return super.toString() + ":text:" + this.Text;
    }
}
