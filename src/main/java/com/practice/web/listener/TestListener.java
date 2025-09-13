package com.practice.web.listener;


import com.practice.web.driver.DriverManager;
import com.practice.web.loggers.ExtentReport;
import com.practice.web.utils.ArtifactUtils;
import com.practice.web.utils.LoggerUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class TestListener implements ITestListener {

    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        LoggerUtils.info("Test Passed Successfully !!! " + testName);
        LoggerUtils.attachScreenShot(testName, new File(ArtifactUtils.saveScreenShot(DriverManager.getDriver(),testName)));
        LoggerUtils.pass(result.getMethod().getMethodName()+" Passed Successfully !!!");
    }

    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        LoggerUtils.error("Failure reason: " + result.getThrowable().getMessage());
        LoggerUtils.attachScreenShot(testName, new File(ArtifactUtils.saveScreenShot(DriverManager.getDriver(),testName)));
        LoggerUtils.info(result.getMethod().getMethodName()+" Failed !!!");
        LoggerUtils.fail(result.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
        ExtentReport.initReports();
    }

    public void onFinish(ITestContext context) {
        ExtentReport.flushReports();
    }
}
