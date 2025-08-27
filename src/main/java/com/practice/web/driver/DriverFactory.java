package com.practice.web.driver;

import com.practice.web.enums.Browser;
import com.practice.web.exceptions.BrowserNotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class DriverFactory {

    private DriverFactory(){}

    public static WebDriver getDriver(String browser){
        if(browser.equalsIgnoreCase(Browser.CHROME.name()))
            return new ChromeDriver();
        else if(browser.equalsIgnoreCase(Browser.FIREFOX.name()))
            return new FirefoxDriver();
        else
            try {
                throw new BrowserNotImplementedException("Driver Not Yet Implemented for "+browser);
            } catch (BrowserNotImplementedException e) {
                throw new RuntimeException(e);
            }

    }
}
