package ru.sondar.documentmodel.documentfactory.structure;

import ru.sondar.documentmodel.documentfactory.SDObjectFactory;

/**
 *
 * @author GlebZemnieks
 */
public class Text extends CompositeObject{
    public Text(String text){
        super();
        this.addObject(SDObjectFactory.getText(text));
    }
}
