package com.practice.web.utils;

import com.practice.web.loggers.ILogger;
import com.practice.web.loggers.ReportPortalLogger;

public final class LoggerUtils {

    private static final ILogger log = new ReportPortalLogger();

    private LoggerUtils(){}

    public static void info(String message){
        log.info(message);
    }

    public static void error(String message){
        log.error(message);
    }

    public static void warn(String message){
        log.warn(message);
    }

    public static void debug(String message){
        log.debug(message);
    }

    public static void pass(String message){
        log.pass(message);
    }

    public static void fail(String message){
        log.fail(message);
    }


}
