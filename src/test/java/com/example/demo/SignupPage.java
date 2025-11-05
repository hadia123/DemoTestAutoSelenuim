/*package com.example.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignupPage extends BasePage {

    // Titre
    @FindBy(id = "id_gender1")
    private WebElement radioMr;

    @FindBy(id = "id_gender2")
    private WebElement radioMrs;

    // Champs de base
    @FindBy(id = "name")
    private WebElement inputName;

    @FindBy(id = "email")
    private WebElement inputEmail;

    @FindBy(id = "password")
    private WebElement inputPassword;

    // Date de naissance
    @FindBy(id = "days")
    private WebElement selectDay;

    @FindBy(id = "months")
    private WebElement selectMonth;

    @FindBy(id = "years")
    private WebElement selectYear;

    // Cases à cocher
    @FindBy(id = "newsletter")
    private WebElement checkNewsletter;

    @FindBy(id = "optin")
    private WebElement checkOffers;

    // Bouton de soumission
    @FindBy(css = "button[type='submit']")
    private WebElement btnCreateAccount;

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    public void fillAccountForm(String gender, String name, String email, String password,
                                String day, String month, String year, boolean newsletter, boolean offers) {
        if (gender.equalsIgnoreCase("Mr")) {
            radioMr.click();
        } else {
            radioMrs.click();
        }

        inputName.clear();
        inputName.sendKeys(name);
        inputEmail.clear();
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);

        selectDay.sendKeys(day);
        selectMonth.sendKeys(month);
        selectYear.sendKeys(year);

        if (newsletter) checkNewsletter.click();
        if (offers) checkOffers.click();
    }

    public void submitAccount() {
        btnCreateAccount.click();
    }
}*/
