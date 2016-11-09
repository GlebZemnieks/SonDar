package ru.sondar.client;

import android.util.Log;

import ru.sondar.core.logger.LoggerInterface;

public class ALogging implements LoggerInterface {
    /**
     * Customer Logging function with loggingLevel
     *
     * @param loggingLevel
     * @param logTag
     * @param logMsg
     */
    @Override
    public void Log(int loggingLevel, String logTag, String logMsg) {
        switch (loggingLevel) {
            case Log.DEBUG:
                Log.d(logTag, logMsg);
                break;
            case Log.INFO:
                Log.i(logTag, logMsg);
                break;
            case Log.WARN:
                Log.w(logTag, logMsg);
                Log.e("Warning", logMsg);
                break;
            case Log.ERROR:
                Log.e(logTag, logMsg);
                Log.e("Error", logMsg);
                break;
        }
    }

    /**
     * Customer Logging function with loggingLevel and StackTrace input
     *
     * @param loggingLevel
     * @param logTag
     * @param logMsg
     * @param errorStackTrace
     */
    @Override
    public void Log(int loggingLevel, String logTag, String logMsg, Throwable errorStackTrace) {
        switch (loggingLevel) {
            case Log.DEBUG:
                Log.d(logTag, logMsg, errorStackTrace);
                break;
            case Log.INFO:
                Log.i(logTag, logMsg, errorStackTrace);
                break;
            case Log.WARN:
                Log.w(logTag, logMsg, errorStackTrace);
                Log.e("Warning", logMsg, errorStackTrace);
                break;
            case Log.ERROR:
                Log.e(logTag, logMsg, errorStackTrace);
                Log.e("Error", logMsg, errorStackTrace);
                break;
        }
    }

    /**
     * Default Logging function
     *
     * @param logTag
     * @param logMsg
     */
    @Override
    public void Log(String logTag, String logMsg) {
        Log.d(logTag, logMsg);
    }

    /**
     * Default Logging function with StackTrace input
     *
     * @param logTag
     * @param logMsg
     * @param errorStackTrace
     */
    @Override
    public void Log(String logTag, String logMsg, Throwable errorStackTrace) {
        Log.d(logTag, logMsg, errorStackTrace);
    }
}
