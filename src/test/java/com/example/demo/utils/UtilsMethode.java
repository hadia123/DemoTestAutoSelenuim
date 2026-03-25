package com.example.demo.utils;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.example.demo.pages.BasePage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class UtilsMethode extends BasePage{

     public UtilsMethode(WebDriver driver) {
        super(driver);
    }

    public void scroll_javascript(){

         JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);"); 
    }

     public void takeScreenshot(String name) {
        try {
            if (driver == null) return;
            if (!(driver instanceof TakesScreenshot)) return;
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path screenshotsDir = Path.of(System.getProperty("user.dir"), "screenshots");
            if (!Files.exists(screenshotsDir)) Files.createDirectories(screenshotsDir);
            String filename = name + "_" + System.currentTimeMillis() + ".png";
            Path dest = screenshotsDir.resolve(filename);
            Files.copy(src.toPath(), dest);
            System.out.println("Screenshot saved: " + dest.toAbsolutePath());
        } catch (IOException ioe) {
            System.err.println("Failed to save screenshot: " + ioe.getMessage());
        }
    }

}
