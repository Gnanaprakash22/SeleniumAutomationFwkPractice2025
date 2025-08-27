package com.practice.web.utils;

import com.practice.web.config.ConfigFactory;
import com.practice.web.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class SeleniumUtils {

    private SeleniumUtils(){}

    public static void sendKeys(By by,String value){
        WebElement element = waitUntilElementToBePresent(by);
        element.sendKeys(value);
    }

    public static void click(By by){
        click(waitUntilElementToBePresent(by));
    }

    public static void click(WebElement element){
        element.click();
    }

    public static WebElement waitUntilElementToBePresent(By by){
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(ConfigFactory.getConfig().timeout()));
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void scrollToElementUsingActions(By by){
        WebDriver driver = DriverManager.getDriver();
        new Actions(driver).scrollToElement(driver.findElement(by));
    }

    public static void scrollToElementUsingJs(By by){
        WebDriver driver = DriverManager.getDriver();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", driver.findElement(by));
    }
}
