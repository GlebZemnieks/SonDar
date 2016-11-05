package ru.sondar.documentmodel.objectmodel;

/**
 * SDText object
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
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
    public String toString() {
        return super.toString() + " : text : " + this.Text;
    }
}
