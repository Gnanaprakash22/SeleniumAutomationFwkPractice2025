package com.practice.web.loggers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.practice.web.constants.FrameworkConstants;

public class ExtentReport {

    private ExtentReport(){}

    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    public static void initReports(){
        extentReports = new ExtentReports();
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(FrameworkConstants.getReportPath());
        extentReports.attachReporter(extentSparkReporter);
    }

    public static void flushReports(){
        extentReports.flush();
    }

    public static void createTest(String testCaseName){
        extentTest = extentReports.createTest(testCaseName);
        ExtentManager.setExtentTest(extentTest);
    }
}
