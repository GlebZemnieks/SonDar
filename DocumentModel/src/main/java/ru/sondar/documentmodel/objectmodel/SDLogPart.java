package ru.sondar.documentmodel.objectmodel;

import java.util.ArrayList;

/**
 * Log file list object
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class SDLogPart {

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
}
