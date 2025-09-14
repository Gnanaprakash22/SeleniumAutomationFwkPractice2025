package com.practice.web.loggers;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Media;
import com.practice.web.utils.ArtifactUtils;

import java.io.File;

public class ExtentLogger implements ILogger{
    @Override
    public void info(String message) {
        ExtentTest extentTest =ExtentManager.getExtentTest();
        if(extentTest!=null){
            extentTest.info(message);
        }
    }

    @Override
    public void error(String message) {
        ExtentTest extentTest = ExtentManager.getExtentTest();
        if(extentTest != null){
            extentTest.fail(message);
        }
    }

    @Override
    public void warn(String message) {
        ExtentTest extentTest =ExtentManager.getExtentTest();
        if(extentTest!=null){
            extentTest.warning(message);
        }
    }

    @Override
    public void debug(String message) {
        ExtentTest extentTest = ExtentManager.getExtentTest();
        if(extentTest != null){
            extentTest.info("<b>DEBUG:</b> " + message);
        }
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
        info(action+" performed on "+element);
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

    public Media attachScreenshot(){
        String base64Screenshot = "data:image/png;base64," + ArtifactUtils.getScreenShot();
        return ExtentManager.getExtentTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().getFirst();
    }

    @Override
    public void apiLog(String endpoint, String method, String requestBody, String responseBody) {

    }

    @Override
    public void errorWithScreenshot(String message) {

    }

    @Override
    public void pass(String message) {
        ExtentTest extentTest =ExtentManager.getExtentTest();
        if(extentTest!=null){
            extentTest.pass(message,attachScreenshot());
        }
    }

    @Override
    public void fail(String message) {
        ExtentTest extentTest =ExtentManager.getExtentTest();
        if(extentTest!=null){
            extentTest.fail(message,attachScreenshot());
        }
    }

    @Override
    public void skip(String message) {
        ExtentTest extentTest = ExtentManager.getExtentTest();
        if(extentTest != null){
            extentTest.skip(message);
        }
    }
}
