package ru.sondar.client;

import android.content.Context;
import android.os.Environment;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import ru.sondar.client.filemodule.android.FileModule;
import ru.sondar.core.Config;
import ru.sondar.core.DOMParser;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.logging.*;

/**
 * Created by GlebZemnieks on 9/6/2016.
 */
public class ClientConfiguration {

    /**
     * Client version field
     */
    public static int VERSION = 0;

    /**
     * Testing mode enabled flag
     */
    public static String testingEnabled = "None";

    /**
     * relative path to configuration file. Should be in directory with .jar
     * file
     */
    public static final String PROPERTIES_FILE = "client.configuration";

    private static final String versionTag = "version";
    private static final String logModeTag = "logMode";
    private static final String testingEnabledTag = "testingEnabled";

    public ClientConfiguration(Context context) {
        Element element = init(context, 1);
        parse(element);
    }

    private Element init(Context context, int retry){
        DOMParser parser = null;
        Element congif = null;
        FileModuleInterface fileModule = new FileModule(context);
        try {
            parser = new DOMParser(Environment.getExternalStorageDirectory()+"/sondar/" + PROPERTIES_FILE);
            congif = parser.getRootElement();
        } catch (IOException | ParserConfigurationException | SAXException e) {
            FileModuleWriteThreadInterface writeThreadConfig = fileModule.getWriteThread(Environment.getExternalStorageDirectory()+"/sondar/client.configuration");
            writeThreadConfig.write("<clientConfiguration>\n" +
                    "\t<version>1</version>\n" +
                    "\t<logMode></logMode>\n" +
                    "\t<testingEnabled>False</testingEnabled>\n" +
                    "</clientConfiguration>" );
            writeThreadConfig.close();
            Config.Log("ClientConfiguration","Trouble with parse client.configuration value. File was rewritten in default value", e);
            if(retry > 0) {
                return init(context, 0);
            } else {
                Config.Log("ClientConfiguration","Trouble with parse client.configuration value. Rewriting failed. God - save us please.(Unsupported version detected). Throw RunTime exception. You shall not pass for next step.", e);
                throw new RuntimeException("Trouble with parse client.configuration value. Rewriting failed. God - save us please.(Unsupported version detected)");
            }
        }
        return congif;
    }

    private void parse(Element element){
        NodeList list = element.getElementsByTagName(versionTag);
        if (list.item(0) == null) {
            Config.Log("ClientConfiguration","No version tag field");
        }

        list = element.getElementsByTagName(logModeTag);
        if (list.item(0) == null) {
            Config.Log("ClientConfiguration","No logMode tag field. Set default - EmptyLogging");
            Config.setLogger(this.getLogger(""));
        } else {
            Config.setLogger(getLogger(list.item(0).getTextContent()));
        }

        list = element.getElementsByTagName(testingEnabledTag);
        if (list.item(0) == null) {
            Config.Log("ClientConfiguration","No testingEnabledTag tag field. Set default - None");
            this.testingEnabled = "None";
        } else {
            this.testingEnabled =  list.item(0).getTextContent();
        }


    }

    /**
     * Supported value for logMode tag in client.configuration file
     *
     * @param value
     * @return
     */
    private LoggerInterface getLogger(String value) {
        switch (value) {
            case "None":
                return new EmptyLogging();
            case "Android":
                return new ALogging();
            default:
                return new ALogging();
        }
    }

}
