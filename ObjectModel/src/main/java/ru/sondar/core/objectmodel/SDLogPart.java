package ru.sondar.core.objectmodel;

import org.w3c.dom.Element;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.core.objectmodel.exception.ObjectStructureException;

/**
 *
 * @author GlebZemnieks
 */
public class SDLogPart extends SDMainObject{

    @Override
    protected void parseCurrentObjectField(Element element) throws ObjectStructureException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void printCurrentObjectField(FileModuleWriteThread fileModule) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
