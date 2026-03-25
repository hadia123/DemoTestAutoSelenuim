package com.example.demo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class AppTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Timeout augmenté pour moins de flakiness
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void testValidLogin() {
        driver.get("https://practicetestautomation.com/practice-test-login/");

        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        usernameInput.clear();
        usernameInput.isDisplayed();
        usernameInput.sendKeys("student");

        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        passwordInput.clear();
        passwordInput.sendKeys("Password123");

        By submitLocator = By.id("submitt");
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(submitLocator));
        submitButton.click();

        // attendre que le message de succès soit visible
        By successLocator = By.xpath("//h1");
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(successLocator));

        // DEBUG: affiche le texte réel
        String actual = successMsg.getText().trim();
        System.out.println(">> Texte du message trouvé : '" + actual + "'");

        // assertion tolérante (contient)
        Assert.assertTrue(actual.toLowerCase().contains("logged in successfully".toLowerCase()),
                "Le message de succès doit contenir 'Logged In Successfully' mais on a: " + actual);
    }

    // AfterMethod reçoit le résultat du test : on peut capturer l'écran uniquement si échec.
    @AfterMethod()
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            //takeScreenshot(result.getMethod().getMethodName() + "_failure");
        }
        if (driver != null) {
            driver.quit();
            
        }
    }

   
}