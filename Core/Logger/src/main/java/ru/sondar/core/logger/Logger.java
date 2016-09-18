package ru.sondar.core.logger;

/**
 * Logger object. 
 *
 * @author GlebZemnieks
 */
public class Logger {

    /**
     * Logger object. By default have value
     * {@link ru.sondar.core.logging.EmptyLogging} with empty logging method.
     */
    private static LoggerInterface logger = new EmptyLogging();

    /**
     * Setter for logger object. Logger object should implement interface
     * {@link ru.sondar.core.logging.LoggerInterface}.
     *
     * @param logger All logging message will set off to this object.
     */
    public static void setLogger(LoggerInterface logger) {
        Logger.logger = logger;
        if (logger instanceof EmptyLogging) {
        } else {
        }
    }

    /**
     * Log method. Call {@link ru.sondar.core.logging.LoggerInterface} default
     * method.
     * <p>
     * Access to another methods don't provided in current Configuration
     * version.
     * </p>
     *
     * @param tag
     * @param msg
     */
    public static void Log(String tag, String msg) {
        Logger.logger.Log(tag, msg);
    }

    public static void Log(String tag, String msg, Throwable error) {
        Logger.logger.Log(tag, msg, error);
    }
}
