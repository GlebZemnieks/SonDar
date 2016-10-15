package ru.sondar.sdserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.sondar.core.parser.DOMParser;
import ru.sondar.core.parser.exception.NoFieldException;
import ru.sondar.plugin.Plugin;
import ru.sondar.plugin.DefaultPlugin;
import ru.sondar.sdserver.exception.PluginNotFoundException;

/**
 * Servers configuration object
 *
 * @author GlebZemnieks
 */
public final class ServerConfiguration {

    /**
     * relative path to configuration file. Should be in directory with .jar
     * file
     */
    public static final String PROPERTIES_FILE = "server.configuration";

    public String globalFolderName;

    /**
     * Plug-in list
     */
    public static ArrayList<Plugin> plugins = new ArrayList<>();

    public ServerConfiguration(String globalFolder) {
        this.globalFolderName = globalFolder;
        try {
            DOMParser parser = new DOMParser(globalFolderName + "\\" + PROPERTIES_FILE);
            NodeList pluginsList = parser.getNodeList("plugins", "plugin");
            for (int i = 0; i < pluginsList.getLength(); i++) {
                Element pluginElement = (Element) pluginsList.item(i);
                if (pluginElement.hasAttribute("customType")) {
                    // If user want use custom .jar for plugin work - in this attribute 
                    // he should set to path to 'custom.jar' in server directory. 
                    // TODO Code for loading .jar file and for using custom class instead of default
                    continue;
                    // If plugin is 'customType' - DefaultPlugin not used! (continue)

                }
                Plugin plugin = new DefaultPlugin(pluginElement);
                this.plugins.add(plugin);
            }
        } catch (SAXException | IOException | ParserConfigurationException | NoFieldException ex) {
            Logger.getLogger(ServerConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isPluginExistByUUID(UUID uuid) {
        for (Plugin plugin : this.plugins) {
            if (plugin.configurator.getPluginUUID().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public Plugin getPluginByUUID(UUID uuid) {
        for (Plugin plugin : this.plugins) {
            if (plugin.configurator.getPluginUUID().equals(uuid)) {
                return plugin;
            }
        }
        throw new PluginNotFoundException("Plugin with uuid \"" + uuid.toString() + "\"not found");
    }

    @Override
    public String toString() {
        String temp = "\tPlugin list\n";
        for (Plugin plugin : plugins) {
            temp += "\t\t" + plugin.getClass().getSimpleName() + " = " + plugin.toString() + "\n";
        }
        return temp;
    }

}
