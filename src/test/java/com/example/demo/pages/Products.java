package com.example.demo.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.demo.pages.BasePage;

public class Products extends BasePage {
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @FindBy(xpath = "(//a[contains(.,' Products')])[1]")
    private WebElement lienProducts;

    @FindBy(xpath = "(//p[contains(text(),'Blue Top')])[1]")
    private WebElement product;

    @FindBy(xpath = "(//a[@data-product-id='1'])[1]")
    private WebElement addTocarte;
    @FindBy(xpath = "//*[@id=\'cartModal\']//u[text()='View Cart']")
    private WebElement viewCarte;

    @FindBy(xpath = "//a[text()='Proceed To Checkout']")
    private WebElement processCheckout; 

    @FindBy(xpath = "//*[@id=\"ordermsg\"]//textarea")
    private WebElement textareacomment;

    @FindBy(xpath = "//a[contains(.,'Place Order')]")
    private WebElement bntPlaceOrder;

    public Products(WebDriver driver) {
        super(driver);
    }

    public void ajoutPanier() {
        Actions actions = new Actions(driver);

        lienProducts.click();

        // Scroller la page si nécessaire
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");
        // actions.moveToElement(lienProducts).clickAndHold().moveByOffset
        // (50, 0).release().perform();
        // actions.moveToElement(lienProducts).perform();
        wait.until(ExpectedConditions.visibilityOf(addTocarte));
        addTocarte.click();
        wait.until(ExpectedConditions.visibilityOf(viewCarte));
        viewCarte.click();
        wait.until(ExpectedConditions.visibilityOf(processCheckout));
        processCheckout.click();

        js.executeScript("window.scrollBy(0, 500);");
        textareacomment.sendKeys("je veux ce pull en bleu");

        bntPlaceOrder.click();

    }

}
