package Logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LoggerManager is a utility class for logging messages throughout the application.
 */
public class LoggerManager {

    private static final Logger logger = LoggerFactory.getLogger(LoggerManager.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}
