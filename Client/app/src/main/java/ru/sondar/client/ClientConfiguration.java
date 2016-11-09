package ru.sondar.client;

import android.os.Environment;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import ru.sondar.core.exception.SonDarRuntimeException;
import ru.sondar.core.filemodule.FileModuleInterface;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.logger.EmptyLogging;
import ru.sondar.core.logger.Logger;
import ru.sondar.core.logger.LoggerInterface;
import ru.sondar.core.logger.file.FileLogging;
import ru.sondar.core.parser.DOMParser;

/**
 * Created by GlebZemnieks on 9/6/2016.
 */
public class ClientConfiguration {

    /**
     * relative path to configuration file. Should be in directory with .jar
     * file
     */
    public static final String PROPERTIES_FILE = "client.configuration";
    private static final String versionTag = "version";
    private static final String logModeTag = "logMode";
    private static final String testingEnabledTag = "testingEnabled";
    /**
     * Client version field
     */
    public static int VERSION = 0;
    /**
     * Testing mode enabled flag
     */
    public static String testingEnabled = "None";
    public static String globalPath;

    public ClientConfiguration(FileModuleInterface fileModule, String globalPath) {
        this.globalPath = globalPath;
        Element element = init(fileModule, 1);
        parse(element, fileModule);
    }

    private Element init(FileModuleInterface fileModule, int retry) {
        DOMParser parser = null;
        Element congif = null;
        try {
            parser = new DOMParser(Environment.getExternalStorageDirectory() + "/sondar/" + PROPERTIES_FILE);
            congif = parser.getRootElement();
        } catch (IOException | ParserConfigurationException | SAXException e) {
            firstInitialization(fileModule, e);
            if (retry > 0) {
                return init(fileModule, 0);
            } else {
                Logger.Log("ClientConfiguration", "Trouble with parse client.configuration value. Rewriting failed. God - save us please.(Unsupported version detected). Throw RunTime exception. You shall not pass for next step.", e);
                throw new SonDarRuntimeException("Trouble with parse client.configuration value. Rewriting failed. God - save us please.(Unsupported version detected)");
            }
        }
        return congif;
    }

    private void parse(Element element, FileModuleInterface fileModule) {
        NodeList list = element.getElementsByTagName(versionTag);
        if (list.item(0) == null) {
            Logger.Log("ClientConfiguration", "No version tag field");
        }
        list = element.getElementsByTagName(logModeTag);
        if (list.item(0) == null) {
            Logger.Log("ClientConfiguration", "No logMode tag field. Set default - EmptyLogging");
            Logger.setLogger(this.getLogger("", fileModule));
        } else {
            LoggerInterface newLogger = getLogger(list.item(0).getTextContent(), fileModule);
            Logger.setLogger(newLogger);
            Logger.Log("ClientConfiguration", "New logger was set. New value : " + newLogger.getClass());
        }

        list = element.getElementsByTagName(testingEnabledTag);
        if (list.item(0) == null) {
            Logger.Log("ClientConfiguration", "No testingEnabledTag tag field. Set default - None");
            this.testingEnabled = "None";
        } else {
            this.testingEnabled = list.item(0).getTextContent();
        }
    }

    private void firstInitialization(FileModuleInterface fileModule, Exception e) {
        FileModuleWriteThreadInterface writeThreadConfig = fileModule.getWriteThread(Environment.getExternalStorageDirectory() + "/sondar/" + PROPERTIES_FILE);
        writeThreadConfig.write("<clientConfiguration>\n" +
                "\t<version>1</version>\n" +
                "\t<logMode>File</logMode>\n" +
                "\t<testingEnabled>False</testingEnabled>\n" +
                "</clientConfiguration>");
        writeThreadConfig.close();
        Logger.Log("ClientConfiguration", "Trouble with parse client.configuration value. File was rewritten in default value", e);
    }

    /**
     * Supported value for logMode tag in client.configuration file
     *
     * @param value
     * @return
     */
    private LoggerInterface getLogger(String value, FileModuleInterface fileModule) {
        switch (value) {
            case "None":
                return new EmptyLogging();
            case "Android":
                return new ALogging();
            case "File":
                return new FileLogging(fileModule, Environment.getExternalStorageDirectory() + "/sondar/log/log.txt", false);
            case "FileLong":
                return new FileLogging(fileModule, Environment.getExternalStorageDirectory() + "/sondar/log/log.txt", true);
            default:
                return new ALogging();
        }
    }

}
