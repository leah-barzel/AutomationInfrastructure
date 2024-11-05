package Logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LoggerManager is a utility class for logging messages throughout the application.
 */

public class LoggerManager{

    private static final Logger logger = LoggerFactory.getLogger(LoggerManager.class);

    /**
     * Logs an informational message.
     *
     * @param message The message to log.
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * Logs a warning message.
     *
     * @param message The message to log.
     */
    public static void warn(String message) {
        logger.warn(message);
    }

    /**
     * Logs an error message.
     *
     * @param message The message to log.
     */
    public static void error(String message) {
        logger.error(message);
    }

    /**
     * Logs a debug message.
     *
     * @param message The message to log.
     */
    public static void debug(String message) {
        logger.debug(message);
    }

    /**
     * Logs an exception with an error message.
     *
     * @param message   The error message.
     * @param throwable The exception to log.
     */
    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}
