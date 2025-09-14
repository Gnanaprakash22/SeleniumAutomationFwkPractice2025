package com.practice.web.utils;

import com.practice.web.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArtifactUtils {

    private static final String ARTIFACTS_DIR = System.getProperty("user.dir")+"/target/artifacts";

    private ArtifactUtils(){}

    public static String saveScreenShot(WebDriver driver,String fileName){
        Path dir = createDirectoryIfNotExists("screenshots");
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Path dest = dir.resolve(fileName+"_"+timeStamp()+".png");
        try {
            Files.copy(src.toPath(),dest, StandardCopyOption.REPLACE_EXISTING);
            return dest.toAbsolutePath().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String saveScreenShot(WebElement element, String fileName){
        Path dir = createDirectoryIfNotExists("screenshots");
        File src = element.getScreenshotAs(OutputType.FILE);
        Path dest = dir.resolve(fileName+"_"+timeStamp()+".png");
        try {
            Files.copy(src.toPath(),dest, StandardCopyOption.REPLACE_EXISTING);
            return dest.toAbsolutePath().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String timeStamp(){
        return new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
    }

    private static Path createDirectoryIfNotExists(String subDirectory) {
        Path dir = Paths.get(ARTIFACTS_DIR,subDirectory);
        try {
            Files.createDirectories(dir);
            return dir;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getScreenShot(){
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
    }




}
