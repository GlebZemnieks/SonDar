package ru.sondar.documentmodel.objectmodel;

import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.sondar.core.Config;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.exception.parser.ObjectStructureException;

/**
 * Log file list object
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SDLogPart extends SDMainObject {

    /**
     * Main tag to writing log object
     */
    public static String Log_MainTag = "Log";

    /**
     * log file list field
     */
    private ArrayList<String> logFileName;

    /**
     * Getter for log file list object
     *
     * @return
     */
    public ArrayList<String> getLogList() {
        return this.logFileName;
    }

    /**
     * Setter for log file list object
     *
     * @param logList
     */
    public void setLogList(ArrayList<String> logList) {
        this.logFileName = logList;
    }

    public void addLogFile(String fileName) {
        this.logFileName.add(fileName);
    }

    @Override
    public void parseCurrentObjectField(Element element) throws ObjectStructureException {
        NodeList tempList = element.getElementsByTagName("log");
        logFileName = new ArrayList<>();
        for (int i = 0; i < tempList.getLength(); i++) {
            Element tempLog = ((Element) tempList.item(i));
            this.addLogFile(tempLog.getTextContent());
        }
    }

    @Override
    public void parseObjectFromXML(Element element) throws ObjectStructureException {
        this.parseCurrentObjectField(element);
    }

    @Override
    public void printAttrivute(FileModuleWriteThreadInterface fileModule) {
        fileModule.write("<" + Log_MainTag + ">\n");
    }

    @Override
    public void printCurrentObjectField(FileModuleWriteThreadInterface fileModule) {
        if (this.logFileName != null) {
            for (String log : this.logFileName) {
                fileModule.write("<log>" + log + "</log>\n");
            }
        }
    }

    @Override
    public void printObjectToXML(FileModuleWriteThreadInterface fileModule) {
        Config.Log("SDLogPart::printObjectToXML", "Write logPart : " + this.toString());
        this.printAttrivute(fileModule);
        this.printCurrentObjectField(fileModule);
        fileModule.write("</" + Log_MainTag + ">\n");
    }

}
