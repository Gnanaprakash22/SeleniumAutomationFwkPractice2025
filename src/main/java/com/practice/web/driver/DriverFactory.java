package com.practice.web.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public final class DriverFactory {

    private DriverFactory(){}

    public static WebDriver getDriver(){
        return new ChromeDriver();
    }
}
