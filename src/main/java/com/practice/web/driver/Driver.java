package com.practice.web.driver;

import com.practice.web.config.ConfigFactory;
import com.practice.web.utils.LoggerUtils;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public final class Driver {

    private Driver() {
    }

    public static void initDriver(String browser) {
        WebDriver driver = DriverFactory.getDriver(browser);
        DriverManager.setDriver(driver);
        long timeOut = ConfigFactory.getConfig().timeout();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeOut));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(timeOut));
        driver.manage().window().maximize();
        driver.get(ConfigFactory.getConfig().url());
        LoggerUtils.info(browser+" driver is created successfully");
    }

    public static void quitDriver() {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            driver.quit();
            DriverManager.setDriver(null);
        }
    }
}



