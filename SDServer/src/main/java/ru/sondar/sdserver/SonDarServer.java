package ru.sondar.sdserver;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.pc.FileModule;
import ru.sondar.documentmodel.SDDocument;
import ru.sondar.plugin.*;
import ru.sondar.plugin.driver.exception.*;

/**
 *
 * @author GlebZemnieks
 */
public class SonDarServer {

    private final ServerConfiguration configuration;
    public final DocumentManager documentManager;
    private final FileModuleInterface fileModule;

    public SonDarServer(String globalFolder) {
        this.fileModule = new FileModule();
        this.configuration = new ServerConfiguration(globalFolder);
        this.documentManager = new DocumentManager(fileModule, globalFolder);
    }

    public void importDocument(String key, UUID pluginUuid, DriverName driver) {
        try {
            Plugin plugin = this.configuration.getPluginByUUID(pluginUuid);
            plugin.importDocumentFromDB(driver, key);
        } catch (DataBaseFileNotFoundException | RowNotFoundException ex) {
            Logger.getLogger(SonDarServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exportDocument(SDDocument document, DriverName driver) throws DataBaseFileNotFoundException, RowNotFoundException {
        Plugin plugin = this.configuration.getPluginByUUID(document.getHeadPart().getPluginUUID());
        plugin.exportDocumentToDB(driver, document);
    }

    @Override
    public String toString() {
        String temp = "Server configuration :\n";
        temp += this.configuration.toString();
        temp += this.documentManager.toString();
        return temp;
    }
}
