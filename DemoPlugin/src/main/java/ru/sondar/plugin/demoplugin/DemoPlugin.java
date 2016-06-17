package ru.sondar.plugin.demoplugin;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import ru.sondar.documentmodel.SDDocument;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.documentmodel.objectmodel.exception.ObjectStructureException;
import ru.sondar.plugin.DriverManager;
import ru.sondar.plugin.DriverName;
import ru.sondar.plugin.Plugin;
import ru.sondar.plugin.driver.exception.DataBaseFileNotFoundException;
import ru.sondar.plugin.driver.exception.RowNotFoundException;
import ru.sondar.plugin.exception.MissingPluginConfigurationFileException;

/**
 * Plug-in for example
 *
 * @author GlebZemnieks
 */
public class DemoPlugin extends Plugin {

    public static void main(String... args) {
        DemoPlugin demo = new DemoPlugin("E:\\Share");
        demo.configurator.generateExampleFile();
        System.out.println(demo.manager.getDriversList());
        SDDocument[] documents = new SDDocument[4];
        for (int i = 0; i < documents.length; i++) {
            try {
                documents[i] = new SDDocument();
                documents[i].loadDocument("E:\\Share\\Demo\\test2\\Demo" + (i + 1) + ".xml");
                demo.exportDocumentToDB(DriverName.ExcelDriver, documents[i]);
            } catch (SAXException | IOException | ParserConfigurationException | ObjectStructureException | DataBaseFileNotFoundException | RowNotFoundException ex) {
                Logger.getLogger(DemoPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (int i = 0; i < 4; i++) {
            try {
                SDDocument document = demo.importDocumentFromDB(DriverName.ExcelDriver, "00000000-0000-0000-0000-00000000000" + (i + 1));
                FileModuleWriteThread fileModule = new FileModuleWriteThread("E:\\temp\\demo_" + "00000000-0000-0000-0000-00000000000" + (i + 1) + ".txt");
                document.saveDocument(fileModule);
                fileModule.close();
            } catch (DataBaseFileNotFoundException | RowNotFoundException ex) {
                Logger.getLogger(DemoPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public DemoPlugin(String globalFolder) {
        try {
            this.configurator = new DemoPluginConfigurator(globalFolder);
            this.manager = new DriverManager(globalFolder + "\\" + this.configurator.localFolderName, this.configurator.pluginConfigurationFileName);
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            throw new MissingPluginConfigurationFileException();
        }
    }

    @Override
    public SDDocument cutsomImportParameters(DriverName name, String key) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

    @Override
    protected void cutsomExportParameters(DriverName name, SDDocument document) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
