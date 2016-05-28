package ru.sondar.core.documentfactory.structure;

import ru.sondar.core.documentfactory.SDObjectFactory;

/**
 *
 * @author GlebZemnieks
 */
public class TextAndEditTextList extends CompositeObject {

    public TextAndEditTextList(String text,
            String defaultText, int textLengt, String name) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getEditText(defaultText, textLengt).setObjectName(name));
    }

    public TextAndEditTextList(String text,
            String defaultText, int textLengt, String name,
            String defaultText2, int textLengt2, String name2) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getEditText(defaultText, textLengt).setObjectName(name));
        this.addObject(SDObjectFactory.getEditText(defaultText2, textLengt2).setObjectName(name2));
    }

    public TextAndEditTextList(String text,
            String defaultText, int textLengt, String name,
            String defaultText2, int textLengt2, String name2,
            String defaultText3, int textLengt3, String name3) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getEditText(defaultText, textLengt).setObjectName(name));
        this.addObject(SDObjectFactory.getEditText(defaultText2, textLengt2).setObjectName(name2));
        this.addObject(SDObjectFactory.getEditText(defaultText3, textLengt3).setObjectName(name3));
    }

    public TextAndEditTextList(String text,
            String defaultText, int textLengt, String name,
            String defaultText2, int textLengt2, String name2,
            String defaultText3, int textLengt3, String name3,
            String defaultText4, int textLengt4, String name4) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getEditText(defaultText, textLengt).setObjectName(name));
        this.addObject(SDObjectFactory.getEditText(defaultText2, textLengt2).setObjectName(name2));
        this.addObject(SDObjectFactory.getEditText(defaultText3, textLengt3).setObjectName(name3));
        this.addObject(SDObjectFactory.getEditText(defaultText4, textLengt4).setObjectName(name4));
    }
}
