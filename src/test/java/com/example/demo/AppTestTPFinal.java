package com.example.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.demo.pages.LoginPage;
import com.example.demo.pages.SignUp;
import com.example.demo.utils.DriverManager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class AppTestTPFinal {

   private WebDriver driver;
   private LoginPage loginPage;
   private SignUp   signUp;

    @BeforeClass
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://automationexercise.com/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLogin() {
        loginPage.login("testDemo123@gmail.com", "demo123");
        

        //Assert.assertTrue(driver.getTitle().contains("My Account"));
    }

    @AfterClass
    public void tearDown() {
      //DriverManager.quitDriver();
    }
}