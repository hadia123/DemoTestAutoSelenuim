package com.example.demo.tests;

import com.example.demo.pages.LoginPage;
import com.example.demo.utils.DriverManager;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;



public class AppDemoTest {

    private WebDriver driver;
    private LoginPage loginPage;
   // utilsMethode utils;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://ton-site.com"); // on  remplace par ton URL
        loginPage = new LoginPage(driver);
        //utils= new UtilsMethodes(driver);

    }

    @Test
    public void shouldLoginSuccessfully() {

        String email = "test@mail.com";
        String password = "test123";

        // Action
        loginPage.login(email, password);

        // Vérification 
        String currentUrl = driver.getCurrentUrl();

      //  assertTrue("Login échoué", currentUrl.contains("dashboard"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Vérifier si le test a échoué
        if (!result.isSuccess()) {
            System.out.println("Le test a échoué : " + result.getMethod().getMethodName());
          //  utils.takeScreenshot(result.getMethod().getMethodName() + "_failure");
        } else {
            System.out.println("Test réussi : " + result.getMethod().getMethodName());
        }

        // Fermer le navigateur proprement
        if (driver != null) {
            try {
             //   driver.quit();
             System.out.println("test exist");
            } catch (Exception e) {
                System.err.println("Erreur lors de la fermeture du driver : " + e.getMessage());
            }
            driver = null;
        }
    }
}