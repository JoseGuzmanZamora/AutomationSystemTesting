package systemTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MainNavigation extends InitDriver {


    @BeforeClass(alwaysRun = true)
    public void initUrl(){
        driver.get(url);
    }

    @Test(groups = {"dashboardNavigation", "all"})
    public void dashboardNavigation() throws InterruptedException {
        genericLogin();

        // And check that you got to the front page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Donal Tromp')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'ingles')]")));

        allFlashcards(0);

        genericLogout();
    }

    @Test(groups = {"cursosNavigation", "all"})
    public void cursosNavigation() {
        genericLogin();

        // And check that you got to the front page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Donal Tromp')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'ingles')]")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/onecard/courses/list']")));
        driver.findElement(By.xpath("//a[@href='/onecard/courses/list']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Cursos')]")));


        genericLogout();
    }

    @Test(groups = {"temasNavigation", "all"})
    public void temasNavigation() {
        genericLogin();

        // And check that you got to the front page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Donal Tromp')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'ingles')]")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/onecard/subjects/list']")));
        driver.findElement(By.xpath("//a[@href='/onecard/subjects/list']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Temas')]")));


        genericLogout();
    }

    @Test(groups = {"flashcardNavigation", "all"})
    public void flashcardNavigation() {
        genericLogin();

        // And check that you got to the front page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Donal Tromp')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'ingles')]")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/onecard/cards/list']")));
        driver.findElement(By.xpath("//a[@href='/onecard/cards/list']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Temas')]")));


        genericLogout();
    }

    public void allFlashcards(int i) throws InterruptedException {
        String value = "temasBtn" + i;
        // Start by entering the first in the dashboard
        driver.findElement(By.id(value)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Algebra')]")));

        driver.findElement(By.id("algebraBtn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'FlashCards')]")));

        // Now go back
        Thread.sleep(1000);
        driver.findElement(By.id("flashcardsClose")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Temas')]")));
        driver.findElement(By.id("temasClose")).click();
    }

}
