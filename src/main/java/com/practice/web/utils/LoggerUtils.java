package com.practice.web.utils;

import com.practice.web.enums.Reports;
import com.practice.web.exceptions.ReportNotImplementedException;
import com.practice.web.loggers.ExtentLogger;
import com.practice.web.loggers.ILogger;
import com.practice.web.loggers.ReportPortalLogger;

import java.io.File;

public final class LoggerUtils {

    private LoggerUtils() {
    }

    private static ILogger getInstance() {
        ILogger logger;
        String reportType = System.getProperty("reportType","REPORT_PORTAL");
        Reports reports = Reports.valueOf(reportType.toUpperCase());
        switch (reports) {
            case EXTENT_REPORT:
                logger = new ExtentLogger();
                break;
            case REPORT_PORTAL:
                logger = new ReportPortalLogger();
                break;
            default:
                try {
                    throw new ReportNotImplementedException("Reporting Not Implemented for " + reports);
                } catch (ReportNotImplementedException e) {
                    throw new RuntimeException(e);
                }
        }

        return logger;
    }

    private static final ILogger logger = getInstance();

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void pass(String message) {
        logger.pass(message);
    }

    public static void fail(String message) {
        logger.fail(message);
    }

    public static void attachScreenShot(String testName, File file) {
        logger.attachScreenshot(testName, file);
    }

    public static void browserAction(String action, String element) {
        logger.browserAction(action, element);
    }
}
