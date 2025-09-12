package com.practice.web.driver;

import com.practice.web.enums.Browser;
import com.practice.web.exceptions.BrowserNotImplementedException;
import com.practice.web.utils.LoggerUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverFactory {

    private DriverFactory(){}

    public static WebDriver getDriver(String browser){
        if(browser.equalsIgnoreCase(Browser.CHROME.name())) {
            LoggerUtils.info("Chrome driver is initialized");
            return new ChromeDriver(getChromeOptions());
        }
        else if(browser.equalsIgnoreCase(Browser.FIREFOX.name())) {
            LoggerUtils.info("FireFox driver is initialized");
            return new FirefoxDriver(getFireFoxOptions());
        }
        else
            try {
                throw new BrowserNotImplementedException("Driver Not Yet Implemented for "+browser);
            } catch (BrowserNotImplementedException e) {
                LoggerUtils.error(e.getMessage());
                throw new RuntimeException(e);
            }

    }

    private static ChromeOptions getChromeOptions(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-plugins");
        chromeOptions.addArguments("--disable-images");
        chromeOptions.addArguments("--disable-javascript");  // Remove if JS is needed
        chromeOptions.addArguments("--disable-web-security");
        chromeOptions.addArguments("--disable-background-networking");
        chromeOptions.addArguments("--disable-background-timer-throttling");
        chromeOptions.addArguments("--disable-renderer-backgrounding");
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless"));
        if(isHeadless) {
            chromeOptions.addArguments("--headless");
        }
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--start-maximized");

        return chromeOptions;
    }

    private static FirefoxOptions getFireFoxOptions(){
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--no-sandbox");
        firefoxOptions.addArguments("--disable-gpu");
        firefoxOptions.addArguments("--disable-extensions");
        firefoxOptions.addArguments("--disable-plugins");
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless"));
        if(isHeadless){
            firefoxOptions.addArguments("--headless");
        }
        firefoxOptions.addArguments("--width=1920");
        firefoxOptions.addArguments("--height=1080");

        return firefoxOptions;
    }
}
