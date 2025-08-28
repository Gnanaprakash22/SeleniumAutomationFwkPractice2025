package com.practice.web.loggers;

import java.io.File;

public class ExtentLogger implements ILogger{
    @Override
    public void info(String message) {

    }

    @Override
    public void error(String message) {

    }

    @Override
    public void warn(String message) {

    }

    @Override
    public void debug(String message) {

    }

    @Override
    public void step(String stepDescription) {

    }

    @Override
    public void testStart(String testName) {

    }

    @Override
    public void testEnd(String testName) {

    }

    @Override
    public void browserAction(String action, String element) {

    }

    @Override
    public void assertion(String description, boolean result) {

    }

    @Override
    public void attachScreenshot(String testName, File file) {

    }

    @Override
    public void attachFile(String message, String content, String fileName) {

    }

    @Override
    public void apiLog(String endpoint, String method, String requestBody, String responseBody) {

    }

    @Override
    public void errorWithScreenshot(String message) {

    }

    @Override
    public void pass(String message) {

    }

    @Override
    public void fail(String message) {

    }

    @Override
    public void skip(String message) {

    }
}
