package com.example.demo.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.demo.pages.BasePage;

public class SignUp extends BasePage {

     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // définition des webElement utilisé dans le formulaire inscription

    @FindBy(id = "id_gender1")
    private WebElement genderMr;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(id = "days")
    private WebElement days;
    @FindBy(id = "months")
    private WebElement months;
    @FindBy(id = "years")
    private WebElement years;

    // champs obligatoire
    @FindBy(id = "first_name")
    private WebElement first_name;
    @FindBy(id = "last_name")
    private WebElement last_name;
    @FindBy(id = "address1")
    private WebElement address1;
    @FindBy(id = "country")
    private WebElement country;

    @FindBy(id = "state")
    private WebElement state;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "zipcode")
    private WebElement zipcode;
    @FindBy(id = "mobile_number")
    private WebElement mobile_number;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement bnt_continue;
    // button valider
    @FindBy(xpath = "//button[contains(text(),'Create Account')]")
    private WebElement button_create;

    //text creation compte avec sucées
      @FindBy(xpath = "//b[text()=\'Account Created!\']")
    private WebElement msg_creation_sucess;

    

    // constructeur de la class
    public SignUp(WebDriver driver) {
        super(driver);
    }

    public void inscription(String motdepass, String mois, String annee,
            String fname, String lname, String adresse, String pays,
            String region, String ville, String codePostal, String telephone) {
       
        // Attendre que le champ email soit visible

        wait.until(ExpectedConditions.visibilityOf(genderMr));
        genderMr.click();
        wait.until(ExpectedConditions.visibilityOf(password));
        password.sendKeys(motdepass);
        wait.until(ExpectedConditions.visibilityOf(password));
        password.sendKeys(motdepass);

        // Créer le Select et choisir par valeur
        Select select = new Select(days);
        select.selectByValue("2");
        Select select2 = new Select(months);
        select2.selectByVisibleText(mois);
        Select select3 = new Select(years);
        select3.selectByValue(annee);

        wait.until(ExpectedConditions.visibilityOf(first_name));
        first_name.sendKeys(fname);
        wait.until(ExpectedConditions.visibilityOf(last_name));
        last_name.sendKeys(lname);
        wait.until(ExpectedConditions.visibilityOf(address1));
        address1.sendKeys(adresse);

        wait.until(ExpectedConditions.visibilityOf(country));
        country.sendKeys(pays);

        wait.until(ExpectedConditions.visibilityOf(state));
        state.sendKeys(region);
        wait.until(ExpectedConditions.visibilityOf(city));
        city.sendKeys(ville);
        wait.until(ExpectedConditions.visibilityOf(zipcode));
        zipcode.sendKeys(codePostal);
        wait.until(ExpectedConditions.visibilityOf(mobile_number));
        mobile_number.sendKeys(telephone);

    }

    public void submitAccount() {
         JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");  
       wait.until(ExpectedConditions.visibilityOf(button_create));
        button_create.click();
    }
    public void creationSucess(){

          wait.until(ExpectedConditions.visibilityOf( msg_creation_sucess));
           msg_creation_sucess.isDisplayed();  
           bnt_continue.click();

    }


}
