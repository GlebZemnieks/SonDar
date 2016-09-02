package ru.sondar.documentmodel.objectmodel;

import org.w3c.dom.Element;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.exception.parser.ObjectStructureException;

/**
 * End of line object decoration object
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
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
