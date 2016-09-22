package ru.sondar.documentmodel.documentfactory.structure;

import ru.sondar.documentmodel.documentfactory.SDObjectFactory;
import ru.sondar.documentmodel.objectmodel.SDSpinner;
import ru.sondar.documentmodel.objectmodel.SDWordsBasePart;
import ru.sondar.documentmodel.objectmodel.WordBase;
import ru.sondar.documentmodel.objectmodel.exception.BaseWithNameNotExistException;

/**
 *
 * @author GlebZemnieks
 */
public class TextAndSpinnerList extends CompositeObject {

    public TextAndSpinnerList(String text, SDWordsBasePart baseList,
            String baseName, int selected, String name) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(getSpinner(baseList, baseName, selected, name));
    }

    public TextAndSpinnerList(String text, SDWordsBasePart baseList,
            String baseName1, int selected1, String name1,
            String baseName2, int selected2, String name2) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(getSpinner(baseList, baseName1, selected1, name1));
        this.addObject(getSpinner(baseList, baseName2, selected2, name2));
    }

    public TextAndSpinnerList(String text, SDWordsBasePart baseList,
            String baseName1, int selected1, String name1,
            String baseName2, int selected2, String name2,
            String baseName3, int selected3, String name3) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(getSpinner(baseList, baseName1, selected1, name1));
        this.addObject(getSpinner(baseList, baseName2, selected2, name2));
        this.addObject(getSpinner(baseList, baseName3, selected3, name3));
    }

    public TextAndSpinnerList(String text, SDWordsBasePart baseList,
            String baseName1, int selected1, String name1,
            String baseName2, int selected2, String name2,
            String baseName3, int selected3, String name3,
            String baseName4, int selected4, String name4) {
        super();
        this.addObject(SDObjectFactory.getText(text));
        this.addObject(getSpinner(baseList, baseName1, selected1, name1));
        this.addObject(getSpinner(baseList, baseName2, selected2, name2));
        this.addObject(getSpinner(baseList, baseName3, selected3, name3));
        this.addObject(getSpinner(baseList, baseName4, selected4, name4));
    }
    
    private SDSpinner getSpinner(SDWordsBasePart baseList, String baseName, int selected, String name){        
        try {
            return (SDSpinner)SDObjectFactory.getSpinner(baseList, baseName, selected).setObjectName(name);
        } catch (IllegalArgumentException | BaseWithNameNotExistException error) {
            WordBase temp = new WordBase();
            temp.add("");
            baseList.addNewBase(baseName, temp);
            return (SDSpinner)SDObjectFactory.getSpinner(baseList, baseName, selected).setObjectName(name);
        }
    
    }
}
