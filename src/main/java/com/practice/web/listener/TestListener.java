package com.practice.web.listener;


import com.practice.web.utils.LoggerUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    public void onTestStart(ITestResult result) {
    }

    public void onTestSuccess(ITestResult result) {
        LoggerUtils.info("Test Passed Successfully !!! " + result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        LoggerUtils.info("Test failed: " + result.getMethod().getMethodName());
        LoggerUtils.error("Failure reason: " + result.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
    }
}
