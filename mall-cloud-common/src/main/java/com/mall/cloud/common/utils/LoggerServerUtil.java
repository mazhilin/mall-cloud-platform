package com.mall.cloud.common.utils;

import org.slf4j.Logger;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * <p>封装Qicloud项目LoggerServerUtil类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-24 22:43
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public class LoggerServerUtil {
    private static class LoggerServerHolder {
        private static LoggerServerUtil loggerInstance = new LoggerServerUtil();
    }

    public static LoggerServerUtil getInstance() {
        return LoggerServerHolder.loggerInstance;
    }

    private LoggerServerUtil() {}

    /**
     * debug print log.
     *
     * @param logger log
     * @param format log information
     * @param supplier supplier
     */
    public static void debug(Logger logger, String format, Supplier<Object> supplier) {
        if (logger.isDebugEnabled()) {
            logger.debug(format, supplier.get());
        }
    }

    /**
     * Debug.
     *
     * @param logger the logger
     * @param supplier the supplier
     */
    public static void debug(Logger logger, Supplier<Object> supplier) {
        if (logger.isDebugEnabled()) {
            logger.debug(Objects.toString(supplier.get()));
        }
    }

    /**
     * Info.
     *
     * @param logger the logger
     * @param format the format
     * @param supplier the supplier
     */
    public static void info(Logger logger, String format, Supplier<Object> supplier) {
        if (logger.isInfoEnabled()) {
            logger.info(format, supplier.get());
        }
    }

    /**
     * Info.
     *
     * @param logger the logger
     * @param supplier the supplier
     */
    public static void info(Logger logger, Supplier<Object> supplier) {
        if (logger.isInfoEnabled()) {
            logger.info(Objects.toString(supplier.get()));
        }
    }

    /**
     * Error.
     *
     * @param logger the logger
     * @param format the format
     * @param supplier the supplier
     */
    public static void error(Logger logger, String format, Supplier<Object> supplier) {
        if (logger.isErrorEnabled()) {
            logger.error(format, supplier.get());
        }
    }

    /**
     * Error.
     *
     * @param logger the logger
     * @param supplier the supplier
     */
    public static void error(Logger logger, Supplier<Object> supplier) {
        if (logger.isErrorEnabled()) {
            logger.error(Objects.toString(supplier.get()));
        }
    }

    /**
     * Warn.
     *
     * @param logger the logger
     * @param format the format
     * @param supplier the supplier
     */
    public static void warn(Logger logger, String format, Supplier<Object> supplier) {
        if (logger.isWarnEnabled()) {
            logger.warn(format, supplier.get());
        }
    }

    /**
     * Warn.
     *
     * @param logger the logger
     * @param supplier the supplier
     */
    public static void warn(Logger logger, Supplier<Object> supplier) {
        if (logger.isWarnEnabled()) {
            logger.warn(Objects.toString(supplier.get()));
        }
    }
}
