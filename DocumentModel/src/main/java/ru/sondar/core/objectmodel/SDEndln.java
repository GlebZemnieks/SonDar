package ru.sondar.core.objectmodel;

import org.w3c.dom.Element;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.objectmodel.exception.ObjectStructureException;

/**
 * End of line object decoration object
 *
 * @author GlebZemnieks
 */
public class SDEndln extends SDMainObject {

    /**
     * Constructor
     */
    public SDEndln() {
        this.objectType = SDMainObjectType.EndLn;
    }

    @Override
    protected void parseCurrentObjectField(Element element) throws ObjectStructureException {
    }

    @Override
    protected void printCurrentObjectField(FileModuleWriteThreadInterface fileModule) {
    }

}
