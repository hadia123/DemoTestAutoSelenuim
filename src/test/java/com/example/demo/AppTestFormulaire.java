package com.example.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class AppTestFormulaire {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Timeout augmenté pour moins de flakiness
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // Optional: implicit wait (use with caution)
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void testOpenGoogle() {
        driver.get("https://demoqa.com/automation-practice-form");

        // attendre que le titre contienne "DEMOQA" (ou "ToolsQA" selon la page)
        wait.until(ExpectedConditions.titleContains("DEMOQA"));

        try {
            // attendre la présence/visibilité des champs clés
            WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
            WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lastName")));
            WebElement userEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userEmail")));
            WebElement userNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userNumber")));

            // Cliquer sur le label du radio button si l'input est caché
            WebElement genderLabel = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='gender-radio-3']")));
            WebElement subjects = driver.findElement(By.id("subjectsInput"));


            // actions
            firstName.clear();
            firstName.sendKeys("demo");
            lastName.clear();
            lastName.sendKeys("demoN");
            userEmail.clear();
            userEmail.sendKeys("demo@gmail.com");
            genderLabel.click();
            userNumber.sendKeys("06777543333");


            //saisir de la date 
            
            WebElement dateField = driver.findElement(By.id("dateOfBirthInput"));
            
            //Saisie date 
            dateField.sendKeys(Keys.CONTROL+ "a");
            //dateField.clear();//ne marche pas dans le cas type date 
            dateField.sendKeys("05 Nov 2025");
            dateField.sendKeys(Keys.ENTER);



            //champs subjects saisir la valeur qui existe dans l'autocomplete
            subjects.sendKeys("biology");
            //selectionner la valeur via le click enter du clavier
            subjects.sendKeys(Keys.ENTER);
            //champs subjects saisir la valeur qui existe dans l'autocomplete
            subjects.sendKeys("Arts");
            //selectionner la valeur via le click enter du clavier
            subjects.sendKeys(Keys.ENTER);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 500);");
            

        /**gestion d'un object avec label for  */
            By hobbiesLabel = By.cssSelector("label[for='hobbies-checkbox-1']");
            WebElement label = new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.elementToBeClickable(hobbiesLabel));
            label.click();

            //specifier le chemin du fichier 
            //user.dir recupere chemin racine du dossier 
            String filePath = System.getProperty("user.dir") + "demoUploadFile.txt";
            System.out.println("chemin fichier "+filePath);
           //definition input upload 
            WebElement uploadInput = driver.findElement(By.id("uploadPicture"));
            uploadInput.sendKeys(filePath);
            //scrolls
            js.executeScript("window.scrollBy(0, 500);");

            WebElement statelist = driver.findElement(By.id("react-select-3-input"));
            WebElement city = driver.findElement(By.id("react-select-4-input"));
            
            
            statelist.sendKeys("NCR");
            statelist.sendKeys(Keys.ENTER);

            city.sendKeys("Delhi");
            city.sendKeys(Keys.ENTER);

            WebElement btnsubmit = driver.findElement(By.id("submit"));
            btnsubmit.click();


            




            // Petite assertion de base : vérifier que le champ firstName contient la valeur
            Assert.assertEquals(firstName.getAttribute("value"), "demo", "Le prénom n'a pas été saisi correctement.");

        } catch (Exception e) {
            // Afficher la pile d'erreurs pour le debug
            e.printStackTrace();
            // Rethrow pour que TestNG traite le test comme failed (et appelle @AfterMethod)
            throw e instanceof RuntimeException ? (RuntimeException) e : new RuntimeException(e);
        }
        // NE PAS fermer le driver ici : laisser @AfterMethod s'en charger (capture d'écran si fail)
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Vérifier si le test a échoué
        if (!result.isSuccess()) {
            System.out.println("Le test a échoué : " + result.getMethod().getMethodName());
            takeScreenshot(result.getMethod().getMethodName() + "_failure");
        } else {
            System.out.println("Test réussi : " + result.getMethod().getMethodName());
        }

        // Fermer le navigateur proprement
        if (driver != null) {
            try {
             //   driver.quit();
             System.out.println("test exist");
            } catch (Exception e) {
                System.err.println("Erreur lors de la fermeture du driver : " + e.getMessage());
            }
            driver = null;
        }
    }

    private void takeScreenshot(String name) {
        try {
            if (driver == null) return;
            if (!(driver instanceof TakesScreenshot)) return;
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path screenshotsDir = Path.of(System.getProperty("user.dir"), "screenshots");
            if (!Files.exists(screenshotsDir)) Files.createDirectories(screenshotsDir);
            String filename = name + "_" + System.currentTimeMillis() + ".png";
            Path dest = screenshotsDir.resolve(filename);
            Files.copy(src.toPath(), dest);
            System.out.println("Screenshot saved: " + dest.toAbsolutePath());
        } catch (IOException ioe) {
            System.err.println("Failed to save screenshot: " + ioe.getMessage());
        } catch (WebDriverException wde) {
            System.err.println("Screenshot failed: " + wde.getMessage());
        }
    }
}
