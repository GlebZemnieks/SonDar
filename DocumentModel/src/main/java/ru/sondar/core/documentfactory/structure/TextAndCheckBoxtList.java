package ru.sondar.core.documentfactory.structure;

import ru.sondar.core.documentfactory.SDObjectFactory;

/**
 *
 * @author GlebZemnieks
 */
public class TextAndCheckBoxtList extends CompositeObject {

    public TextAndCheckBoxtList(String text,
            String defaultText, boolean isChecked, String name) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getCheckBox(defaultText, isChecked).setObjectName(name));
    }

    public TextAndCheckBoxtList(String text,
            String defaultText, boolean isChecked, String name,
            String defaultText2, boolean isChecked2, String name2) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getCheckBox(defaultText, isChecked).setObjectName(name));
        this.addObject(SDObjectFactory.getCheckBox(defaultText2, isChecked2).setObjectName(name2));
    }

    public TextAndCheckBoxtList(String text,
            String defaultText, boolean isChecked, String name,
            String defaultText2, boolean isChecked2, String name2,
            String defaultText3, boolean isChecked3, String name3) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getCheckBox(defaultText, isChecked).setObjectName(name));
        this.addObject(SDObjectFactory.getCheckBox(defaultText2, isChecked2).setObjectName(name2));
        this.addObject(SDObjectFactory.getCheckBox(defaultText3, isChecked3).setObjectName(name3));
    }

    public TextAndCheckBoxtList(String text,
            String defaultText, boolean isChecked, String name,
            String defaultText2, boolean isChecked2, String name2,
            String defaultText3, boolean isChecked3, String name3,
            String defaultText4, boolean isChecked4, String name4) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getCheckBox(defaultText, isChecked).setObjectName(name));
        this.addObject(SDObjectFactory.getCheckBox(defaultText2, isChecked2).setObjectName(name2));
        this.addObject(SDObjectFactory.getCheckBox(defaultText3, isChecked3).setObjectName(name3));
        this.addObject(SDObjectFactory.getCheckBox(defaultText4, isChecked4).setObjectName(name4));
    }
}
