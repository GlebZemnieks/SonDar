package ru.sondar.core;

import java.io.File;
import ru.sondar.core.logging.*;

/**
 * Configuration object for SonDar Core.
 *
 * @author GlebZemnieks
 */
public class Config {

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
        Config.logger = logger;
        if (logger instanceof EmptyLogging) {
            Config.Log("Config", "Disable logging");
        } else {
            Config.Log("Config", "New logger object : " + logger.getClass());
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
        Config.logger.Log(tag, msg);
    }
    
    public static void Log(String tag, String msg, Throwable error){
        Config.logger.Log(tag, msg, error);
    }
    
    public static String getAbsolutePath(){
        File file = new File("");
        return file.getAbsolutePath();
    }
}
