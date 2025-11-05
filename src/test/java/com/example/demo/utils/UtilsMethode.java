package com.example.demo.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.example.demo.pages.BasePage;

public class UtilsMethode extends BasePage{

     public UtilsMethode(WebDriver driver) {
        super(driver);
    }

    public void scroll_javascript(){

         JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);"); 
    }

}
