/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class SDEditText extends SDMainObject {

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
     * Length field
     */
    private int textLength = 0;

    /**
     * Getter for length field
     *
     * @return
     */
    public int getTextLength() {
        return this.textLength;
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
     * Constructor
     */
    public SDEditText() {
        this.objectType = SDMainObjectType.EditText;
    }

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
    protected void printCurrentObjectField(FileModuleWriteThread fileModule) {
        this.Text = this.Text.replaceAll("\n", "Zzz");
        fileModule.write("<" + EditText_textFieldTag + ">" + this.Text + "</" + EditText_textFieldTag + ">\n"
                + "<" + EditText_textLengthTag + ">" + this.textLength + "</" + EditText_textLengthTag + ">\n");
    }

}
