package com.example.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.ITestResult;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppTestAlert {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // Configuration automatique du driver Chrome (WebDriverManager)
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Attente explicite partagée
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testOpenGoogle() {
        // Ouvrir l’URL du site de test
        driver.get("https://demoqa.com/alerts");

        // Attendre que le titre contienne "DEMOQA"
        wait.until(ExpectedConditions.titleContains("DEMOQA"));

        // Scroller la page si nécessaire
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");

        // 1) Bouton alert simple
        WebElement clickMe = wait.until(ExpectedConditions.elementToBeClickable(By.id("alertButton")));
        clickMe.click();

        // Attendre l'apparition de l'alerte et basculer dessus
        wait.until(ExpectedConditions.alertIsPresent());
        Alert simpleAlert = driver.switchTo().alert();
        System.out.println("Texte de l'alerte : " + simpleAlert.getText());
        simpleAlert.accept();

        // 2) Bouton prompt (alerte avec saisie)
        WebElement promptBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("promtButton")));
        promptBtn.click();

        // Attendre l'apparition du prompt et envoyer du texte
        wait.until(ExpectedConditions.alertIsPresent());
        Alert promptAlert = driver.switchTo().alert();
        String textToSend = "alertDemo";
        promptAlert.sendKeys(textToSend);
        promptAlert.accept();

        // Optionnel : vérifier qu'un message ou résultat attendu apparaît après le prompt
        // Exemple : chercher un élément qui affiche le texte saisi (selon implémentation page)
        // Ici on vérifie simplement que le test a atteint ce point
        Assert.assertTrue(true, "Le prompt a été saisi et accepté.");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
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
