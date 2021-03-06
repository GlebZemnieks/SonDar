package ru.sondar.core.logger.pc;

import ru.sondar.core.logger.LoggerInterface;

/**
 * Class logger for PC Implements
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class PCLogging implements LoggerInterface {

    @Override
    public void Log(String logTag, String logMsg) {
        System.out.println(logTag + "-->  " + logMsg + "\n");
    }

    @Override
    public void Log(String logTag, String logMsg, Throwable errorStackTrace) {
        System.out.println(logTag + "-->  " + logMsg + "::" + errorStackTrace.getMessage() + "\n");
        errorStackTrace.printStackTrace();
    }

    @Override
    public void Log(int logingLevel, String logTag, String logMsg) {
        Log(logTag, logMsg);
    }

    @Override
    public void Log(int logingLevel, String logTag, String logMsg, Throwable errorStackTrace) {
        Log(logTag, logMsg, errorStackTrace);
    }
}
