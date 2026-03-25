package com.example.demo.pages;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.example.demo.pages.*;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@data-qa='signup-name']")
    private WebElement nameInput;
    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement emailInput;
    @FindBy(xpath = "//button[@data-qa='signup-button']")
     private WebElement btnSignup;
    //@FindBy(xpath = "//button//p[text()='Autoriser']")
    //private WebElement autoriserButton;


    /***
     * login signup methode pour saisir l'email et le name pour 
     * l'inscription
     * @param email
     * @param name
     */
    public void loginSignup(String email, String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Attendre que le champ name soit visible
          WebElement autoriserBtn = null;
        try {

        autoriserBtn=driver.findElement(By.xpath("//button//p[text()='Autoriser']"));
           if(autoriserBtn.isDisplayed()){
             autoriserBtn.click();
           }  
        } 
       catch (Exception e) {
            System.out.println("bouton autoriser non présent");
        }
       
        
        wait.until(ExpectedConditions.visibilityOf(nameInput));
        nameInput.clear();
        nameInput.sendKeys(name);
        //Saisir email
        emailInput.sendKeys(email);
        btnSignup.click();
    }




   



}
