package ru.sondar.core;

import ru.sondar.core.logging.EmptyLogging;
import ru.sondar.core.logging.LoggerInterface;

/**
 *
 * @author GlebZemnieks
 */
public class Config {

    /**
     * Logger object
     */
    private static LoggerInterface logger = new EmptyLogging();

    /**
     * Setter for logger object
     *
     * @param loggerInterface
     */
    public static void setLogger(LoggerInterface loggerInterface) {
        logger = loggerInterface;
    }

    /**
     * Log method
     *
     * @param tag
     * @param msg
     */
    public static void Log(String tag, String msg) {
        logger.Log(tag, msg);
    }
}
