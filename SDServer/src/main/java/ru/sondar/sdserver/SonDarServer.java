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

    public final PluginManager pluginManager;
    public final DocumentManager documentManager;
    public final FileModuleInterface fileModule;

    public SonDarServer(String globalFolder) {
        this.fileModule = new FileModule();
        this.pluginManager = new PluginManager(globalFolder);
        this.documentManager = new DocumentManager(fileModule, globalFolder);
    }
    
    @Override
    public String toString() {
        String temp = "Server configuration :\n";
        temp += this.pluginManager.toString();
        temp += this.documentManager.toString();
        return temp;
    }
}
