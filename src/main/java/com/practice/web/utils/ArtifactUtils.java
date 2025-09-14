package com.practice.web.utils;

import com.practice.web.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Base64;
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
        try {
            // Get screenshot as file first to compress it
            File screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            
            // Read and compress the image
            BufferedImage originalImage = ImageIO.read(screenshot);
            
            // Resize to reduce file size (adjust dimensions as needed)
            int newWidth = Math.min(originalImage.getWidth(), 800);  // Max width 800px
            int newHeight = (int) ((double) newWidth / originalImage.getWidth() * originalImage.getHeight());
            
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g2d.dispose();
            
            // Convert to Base64
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "png", baos);
            byte[] imageBytes = baos.toByteArray();
            
            return Base64.getEncoder().encodeToString(imageBytes);
            
        } catch (Exception e) {
            // Fallback to original method if compression fails
            return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
        }
    }




}
