package com.practice.web.driver;

import com.practice.web.config.ConfigFactory;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public final class Driver {

    private Driver() {
    }

    public static void initDriver() {
        WebDriver driver = DriverFactory.getDriver();
        DriverManager.setDriver(driver);
        long timeOut = ConfigFactory.getConfig().timeout();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeOut));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(timeOut));
        driver.manage().window().maximize();
        driver.get(ConfigFactory.getConfig().url());
    }

    public static void quitDriver() {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            driver.quit();
            DriverManager.setDriver(null);
        }
    }
}



