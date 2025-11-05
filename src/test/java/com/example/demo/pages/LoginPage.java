package com.example.demo.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@data-qa='signup-name']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    private WebElement loginButton;
    @FindBy(xpath = "//button//p[text()='Autoriser']")
    private WebElement autoriserButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Attendre que le champ email soit visible
        wait.until(ExpectedConditions.visibilityOf(autoriserButton));
        autoriserButton.click();
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}
