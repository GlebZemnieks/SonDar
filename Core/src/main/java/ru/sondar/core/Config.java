package ru.sondar.core;

import ru.sondar.core.logging.LoggerInterface;
import ru.sondar.core.logging.PCLogging;

/**
 *
 * @author GlebZemnieks
 */
public class Config {

    private static final LoggerInterface logger = new PCLogging();

    public static void Log(String tag, String msg) {
        logger.Log(tag, msg);
    }

    public static void main(String[] args) {
        int i = 5;
        System.out.println((++i) + (++i));
    }
}
