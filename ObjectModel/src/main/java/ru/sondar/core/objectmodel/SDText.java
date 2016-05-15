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
public class SDText extends SDMainObject {

    /**
     * Tag for text tag
     */
    public static String Text_textFieldTag = "text";
    /**
     * Class fields
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
    protected void printCurrentObjectField(FileModuleWriteThread fileModule) {
        fileModule.write("<" + Text_textFieldTag + ">" + this.Text + "</" + Text_textFieldTag + ">\n");
    }

    @Override
    public String toString() {
        return super.toString() + ":text:" + this.Text;
    }
}
