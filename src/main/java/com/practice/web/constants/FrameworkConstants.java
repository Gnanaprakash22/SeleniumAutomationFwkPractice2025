package com.practice.web.constants;

public class FrameworkConstants {

    private FrameworkConstants(){}

    private static final String REPORT_PATH = System.getProperty("user.dir")+"/target/artifacts/index.html";

    public static String getReportPath(){
        return REPORT_PATH;
    }
}
