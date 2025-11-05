package com.example.demo.pages;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUp  extends BasePage{

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement emailInput;

     public SignUp(WebDriver driver) {
        super(driver);
    }

     public void login(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Attendre que le champ email soit visible
       
    }

}
