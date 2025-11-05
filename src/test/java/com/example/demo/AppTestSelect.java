package com.example.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppTestSelect {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testSelectMenu() {
        // Ouvrir la page
        driver.get("https://demoqa.com/select-menu");

        // Attendre le titre
        wait.until(ExpectedConditions.titleContains("DEMOQA"));

        // Scroller si nécessaire
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300);");

        // Attendre que le select soit présent / cliquable
        By selectLocator = By.id("oldSelectMenu");
        WebElement valueSelect = wait.until(ExpectedConditions.elementToBeClickable(selectLocator));

        // Créer le Select et choisir par texte visible
        Select select = new Select(valueSelect);
        select.selectByVisibleText("Green");

        // Vérifier que l'option sélectionnée est bien "Green"
        String selected = select.getFirstSelectedOption().getText().trim();
        Assert.assertEquals(selected, "Green", "L'option sélectionnée devrait être 'Green'.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Erreur lors de la fermeture du driver : " + e.getMessage());
            }
            driver = null;
        }
    }
}
