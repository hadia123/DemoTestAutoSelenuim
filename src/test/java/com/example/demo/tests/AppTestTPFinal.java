package com.example.demo.tests;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.demo.pages.LoginPage;
import com.example.demo.pages.Payment;
import com.example.demo.pages.Products;
import com.example.demo.Pages2.SignUp;
import com.example.demo.utils.DriverManager;

import org.openqa.selenium.*;



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

   @Test()
    public void testValidLogin() {
        //step1 login
        loginPage.loginSignup("testDemo124@gmail.com", "demo123");
        //step 2 inscription
        signUp.inscription("testDemo124@gmail.com","Sdemo123", "July", "2021", "Demo", 
        "DemoLast", 
        "imm9 APP3 CA", "Canada", "MM",
        "Mor", "20211", "0789765433");
        //validation de l'inscription
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