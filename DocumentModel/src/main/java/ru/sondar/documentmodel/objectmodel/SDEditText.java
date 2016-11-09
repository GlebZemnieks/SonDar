package ru.sondar.documentmodel.objectmodel;

import ru.sondar.documentmodel.dependencymodel.SupportDependencyInterface;

/**
 * SDEditText object
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SDEditText extends SDMainObject implements SupportDependencyInterface {

    /**
     * Tag for print and parse text filed
     */
    public static String EditText_textFieldTag = "textEdit";
    /**
     * Tag for print and parse length filed
     */
    public static String EditText_textLengthTag = "textLength";
    /**
     * Tag for print and parse length filed
     */
    public static String EditText_contentTypeTag = "contentType";

    /**
     * Text field
     */
    private String Text = "";

    /**
     * Length field
     */
    private int textLength = 0;

    private String contentType = "text";

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String newType) {
        this.contentType = newType;
    }

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

    @Override
    public String toString() {
        return super.toString() + " : editText(" + this.textLength + ") : " + this.Text;
    }

}
