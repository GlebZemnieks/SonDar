package ru.sondar.core.logger;

/**
 * Empty logger class to use by default on whatever system
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class EmptyLogging implements LoggerInterface {

    @Override
    public void Log(String logTag, String logMsg) {
    }

    @Override
    public void Log(String logTag, String logMsg, Throwable errorStackTrace) {
    }

    @Override
    public void Log(int logingLevel, String logTag, String logMsg) {
    }

    @Override
    public void Log(int logingLevel, String logTag, String logMsg, Throwable errorStackTrace) {
    }
}
