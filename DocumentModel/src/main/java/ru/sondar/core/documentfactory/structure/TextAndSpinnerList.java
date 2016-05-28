package ru.sondar.core.documentfactory.structure;

import ru.sondar.core.documentfactory.SDObjectFactory;

/**
 *
 * @author GlebZemnieks
 */
public class TextAndSpinnerList extends CompositeObject {

    public TextAndSpinnerList(String text,
            String[] listValue, int selected, String name) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getSpinner(listValue, selected).setObjectName(name));
    }

    public TextAndSpinnerList(String text,
            String[] listValue, int selected, String name,
            String[] listValue2, int selected2, String name2) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getSpinner(listValue, selected).setObjectName(name));
        this.addObject(SDObjectFactory.getSpinner(listValue2, selected2).setObjectName(name2));
    }

    public TextAndSpinnerList(String text,
            String[] listValue, int selected, String name,
            String[] listValue2, int selected2, String name2,
            String[] listValue3, int selected3, String name3) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getSpinner(listValue, selected).setObjectName(name));
        this.addObject(SDObjectFactory.getSpinner(listValue2, selected2).setObjectName(name2));
        this.addObject(SDObjectFactory.getSpinner(listValue3, selected3).setObjectName(name3));
    }

    public TextAndSpinnerList(String text,
            String[] listValue, int selected, String name,
            String[] listValue2, int selected2, String name2,
            String[] listValue3, int selected3, String name3,
            String[] listValue4, int selected4, String name4) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getSpinner(listValue, selected).setObjectName(name));
        this.addObject(SDObjectFactory.getSpinner(listValue2, selected2).setObjectName(name2));
        this.addObject(SDObjectFactory.getSpinner(listValue3, selected3).setObjectName(name3));
        this.addObject(SDObjectFactory.getSpinner(listValue4, selected4).setObjectName(name4));
    }
}
