package ru.sondar.documentmodel.documentfactory.structure;

import ru.sondar.documentmodel.documentfactory.SDObjectFactory;

/**
 *
 * @author GlebZemnieks
 */
public class Enter extends CompositeObject{
    
        public Enter() {
        super();
        this.addObject(SDObjectFactory.getEndln());
    }
}
