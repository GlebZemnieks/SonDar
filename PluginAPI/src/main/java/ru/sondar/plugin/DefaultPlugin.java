package ru.sondar.plugin;

import ru.sondar.core.exception.parser.ObjectStructureException;
import ru.sondar.core.exception.parser.NoFieldException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import ru.sondar.core.exception.SonDarRuntimeException;
import ru.sondar.documentmodel.SDDocument;

/**
 *
 * @author GlebZemnieks
 */
public class DefaultPlugin extends Plugin {

    public DefaultPlugin(Element plugin) throws SAXException, IOException, ParserConfigurationException, NoFieldException {
        super(plugin);
    }

    @Override
    public SDDocument cutsomImportParameters(DriverName name, String key) {
        return null;
    }

    @Override
    protected void cutsomExportParameters(DriverName name, SDDocument document) {
    }

    @Override
    public SDDocument getExampleDocument() {
        //In NON-default plugin realization, documents given by the document in plugin directory. 
        SDDocument document = new SDDocument();
        try {
            document.loadDocument(this.configurator.globalPluginFolder + "\\" + this.configurator.localFolderName + "\\Demo.xml");
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            throw new SonDarRuntimeException("Missing(Or incorrect format) Demo.xml in plugin by path \"" + this.configurator.globalPluginFolder + "\\" + this.configurator.localFolderName + "\"");
        } catch (ObjectStructureException ex) {
            Logger.getLogger(DefaultPlugin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return document;
    }

}
