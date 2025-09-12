package com.practice.web.loggers;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {

    private ExtentManager(){}

    private static final ThreadLocal<ExtentTest>threadLocal = new ThreadLocal<>();

    public static ExtentTest getExtentTest(){
        return threadLocal.get();
    }

    public static void setExtentTest(ExtentTest extentTest){
        threadLocal.set(extentTest);
    }
}
