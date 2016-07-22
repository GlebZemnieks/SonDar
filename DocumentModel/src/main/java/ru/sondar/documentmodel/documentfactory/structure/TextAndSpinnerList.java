package ru.sondar.documentmodel.documentfactory.structure;

import ru.sondar.documentmodel.documentfactory.SDObjectFactory;
import ru.sondar.documentmodel.objectmodel.WordBase;

/**
 *
 * @author GlebZemnieks
 */
public class TextAndSpinnerList extends CompositeObject {

    public TextAndSpinnerList(String text,
            WordBase listValue, int selected, String name) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getSpinner(listValue, selected).setObjectName(name));
    }

    public TextAndSpinnerList(String text,
            WordBase listValue, int selected, String name,
            WordBase listValue2, int selected2, String name2) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getSpinner(listValue, selected).setObjectName(name));
        this.addObject(SDObjectFactory.getSpinner(listValue2, selected2).setObjectName(name2));
    }

    public TextAndSpinnerList(String text,
            WordBase listValue, int selected, String name,
            WordBase listValue2, int selected2, String name2,
            WordBase listValue3, int selected3, String name3) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getSpinner(listValue, selected).setObjectName(name));
        this.addObject(SDObjectFactory.getSpinner(listValue2, selected2).setObjectName(name2));
        this.addObject(SDObjectFactory.getSpinner(listValue3, selected3).setObjectName(name3));
    }

    public TextAndSpinnerList(String text,
            WordBase listValue, int selected, String name,
            WordBase listValue2, int selected2, String name2,
            WordBase listValue3, int selected3, String name3,
            WordBase listValue4, int selected4, String name4) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getSpinner(listValue, selected).setObjectName(name));
        this.addObject(SDObjectFactory.getSpinner(listValue2, selected2).setObjectName(name2));
        this.addObject(SDObjectFactory.getSpinner(listValue3, selected3).setObjectName(name3));
        this.addObject(SDObjectFactory.getSpinner(listValue4, selected4).setObjectName(name4));
    }
}
