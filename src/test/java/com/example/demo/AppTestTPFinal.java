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
import com.example.demo.pages.Payment;
import com.example.demo.pages.Products;
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
   private Products  product;
   private Payment  payment;

    @BeforeClass
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://automationexercise.com/login");
        loginPage = new LoginPage(driver);
        signUp=new  SignUp(driver);
        product=new Products(driver);
        payment=new Payment(driver);

    }

    @Test
    public void testInscription() {

        loginPage.login("testDemo123@gmail.com", "demo123");
        signUp.inscription("Sdemo123", "July", "2021", "Demo", 
        "DemoLast", 
        "imm9 APP3 CA", "Canada", "MM",
         "Mor", "20211", "0789765433");
        signUp.submitAccount();
        signUp.creationSucess();
        product.ajoutPanier();
        payment.paiementFinal("Demo User", "123440000877", "123", "03", "2021");
        payment.suppressionCompte();
        //Assert.assertTrue(driver.getTitle().contains("My Account"));
    }

    @AfterClass
    public void tearDown() {
      //DriverManager.quitDriver();
    }
}