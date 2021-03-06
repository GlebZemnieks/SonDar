package ru.sondar.plugin.demoplugin;

import java.io.IOException;
import java.util.UUID;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import ru.sondar.documentmodel.dependencymodel.DependencyPart;
import ru.sondar.documentmodel.documentfactory.SDObjectFactory;
import ru.sondar.documentmodel.SDDocument;
import ru.sondar.documentmodel.SDSequenceObject;
import ru.sondar.documentmodel.objectmodel.SDHeadPart;
import ru.sondar.documentmodel.objectmodel.SDLogPart;
import ru.sondar.plugin.PluginConfigurator;

/**
 *
 * @author GlebZemnieks
 */
public class DemoPluginConfigurator extends PluginConfigurator {

    public DemoPluginConfigurator(String globalFolder) throws SAXException, IOException, ParserConfigurationException {
        super(globalFolder, "Demo");
    }

    @Override
    public SDDocument getExampleDocument() {
        SDDocument document = new SDDocument();
        SDHeadPart headPart = new SDHeadPart();
        headPart.setPluginUUID(UUID.fromString("69e0093d-bdde-4110-b8b4-92609b3c2fb3"));
        headPart.setUUID(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        document.setHeadPart(headPart);
        document.setLogPart(new SDLogPart());
        document.setSequence(new SDSequenceObject());
        document.getSequense().AddXMLObject(SDObjectFactory.getText("test"));
        document.getSequense().AddXMLObject(SDObjectFactory.getCheckBox("test2").setObjectName("test"));
        DependencyPart dependency = SDObjectFactory.getDependency();
        dependency.addDependencyItem("test", 1);
        document.setDependencyPart(dependency);
        document.getSequense().enumirateSequence(0);
        return document;
    }

}
