package ru.sondar.core.logger;

/**
 * Main logger interface. All logger classes must implement this.
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public interface LoggerInterface {

    /**
     * Function to Information logger
     *
     * @param logTag
     * @param logMsg
     */
    public abstract void Log(String logTag, String logMsg);

    /**
     * Function to Error logger
     *
     * @param logTag
     * @param logMsg
     * @param errorStackTrace
     */
    public abstract void Log(String logTag, String logMsg, Throwable errorStackTrace);

    /**
     * Function to Information logger with Logging level parameter
     *
     * @param logingLevel
     * @param logTag
     * @param logMsg
     */
    public abstract void Log(int logingLevel, String logTag, String logMsg);

    /**
     * Function to Error logger with Logging level parameter
     *
     * @param logingLevel
     * @param logTag
     * @param logMsg
     * @param errorStackTrace
     */
    public abstract void Log(int logingLevel, String logTag, String logMsg, Throwable errorStackTrace);
}
