package ru.sondar.documentmodel.documentfactory.structure;

import ru.sondar.documentmodel.documentfactory.SDObjectFactory;
import ru.sondar.documentmodel.objectmodel.SDWordsBasePart;
import ru.sondar.documentmodel.objectmodel.WordBase;

/**
 *
 * @author GlebZemnieks
 */
public class TextAndSpinnerList extends CompositeObject {

    public TextAndSpinnerList(String text, SDWordsBasePart baseList,
            String baseName, int selected, String name) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getSpinner(baseList, baseName, selected).setObjectName(name));
    }

    public TextAndSpinnerList(String text, SDWordsBasePart baseList,
            String baseName1, int selected1, String name1,
            String baseName2, int selected2, String name2) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getSpinner(baseList, baseName1, selected1).setObjectName(name1));
        this.addObject(SDObjectFactory.getSpinner(baseList, baseName2, selected2).setObjectName(name2));
    }

    public TextAndSpinnerList(String text, SDWordsBasePart baseList,
            String baseName1, int selected1, String name1,
            String baseName2, int selected2, String name2,
            String baseName3, int selected3, String name3) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getSpinner(baseList, baseName1, selected1).setObjectName(name1));
        this.addObject(SDObjectFactory.getSpinner(baseList, baseName2, selected2).setObjectName(name2));
        this.addObject(SDObjectFactory.getSpinner(baseList, baseName3, selected3).setObjectName(name3));
    }

    public TextAndSpinnerList(String text, SDWordsBasePart baseList,
            String baseName1, int selected1, String name1,
            String baseName2, int selected2, String name2,
            String baseName3, int selected3, String name3,
            String baseName4, int selected4, String name4) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(SDObjectFactory.getSpinner(baseList, baseName1, selected1).setObjectName(name1));
        this.addObject(SDObjectFactory.getSpinner(baseList, baseName2, selected2).setObjectName(name2));
        this.addObject(SDObjectFactory.getSpinner(baseList, baseName3, selected3).setObjectName(name3));
        this.addObject(SDObjectFactory.getSpinner(baseList, baseName4, selected4).setObjectName(name4));
    }
}
