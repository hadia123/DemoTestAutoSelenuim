package com.example.demo.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.demo.utils.UtilsMethode;

public class Payment extends BasePage {
    UtilsMethode methodeutils = new UtilsMethode(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    @FindBy(xpath = "//input[@data-qa='name-on-card']")
    private WebElement nomCarteBank;

    @FindBy(xpath = "//input[@data-qa='card-number']")
    private WebElement cardNumber;

    @FindBy(xpath = "//input[@data-qa='cvc']")
    private WebElement cvc;
    @FindBy(xpath = "//input[@data-qa='expiry-month']")
    private WebElement expire_month;
    @FindBy(xpath = "//input[@data-qa='expiry-year']")
    private WebElement expire_year;
    @FindBy(id = "submit")
    private WebElement btn_confirm_pay;
    @FindBy(xpath = "//b[contains(.,'Order Placed!')]")
    private WebElement msg_succes_payment;

    @FindBy(xpath = "//a[contains(.,'Delete Account')]")
    private WebElement delete_account;

    @FindBy(xpath = "//b[contains(.,'Account Deleted!')]")
    private WebElement delete_account_msg_sucess;

    public Payment(WebDriver driver) {
        super(driver);
    }

    public void paiementFinal(String nomCard, String numeroCarte,
            String Cvc_num, String exp_month, String exp_year) {
        wait.until(ExpectedConditions.visibilityOf(nomCarteBank));
        nomCarteBank.sendKeys(nomCard);

        wait.until(ExpectedConditions.visibilityOf(cardNumber));
        cardNumber.sendKeys(numeroCarte);

        wait.until(ExpectedConditions.visibilityOf(cvc));
        cvc.sendKeys(Cvc_num);

        wait.until(ExpectedConditions.visibilityOf(expire_month));
        expire_month.sendKeys(exp_month);

        wait.until(ExpectedConditions.visibilityOf(expire_year));
        expire_year.sendKeys(exp_year);

        methodeutils.scroll_javascript();
        wait.until(ExpectedConditions.visibilityOf(btn_confirm_pay));
        btn_confirm_pay.click();
        wait.until(ExpectedConditions.visibilityOf(msg_succes_payment));
        msg_succes_payment.isDisplayed();

    }

    public void suppressionCompte() {
        wait.until(ExpectedConditions.visibilityOf(delete_account));

        delete_account.click();
        wait.until(ExpectedConditions.visibilityOf(delete_account_msg_sucess));
        delete_account_msg_sucess.isDisplayed();

    }

}
