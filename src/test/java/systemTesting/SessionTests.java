package systemTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SessionTests extends InitDriver {

    @BeforeClass(alwaysRun = true)
    public void initUrl(){
        driver.get("http://localhost:8080");
    }

    @Test(groups = {"loginAndOut"})
    public void loginAndOut(){
        genericLogin();

        // And check that you got to the front page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Donal Tromp')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'ingles')]")));

        genericLogout();
    }

    public void genericLogin(){
        String username = "automation";
        String email = "automation@testing.com";
        String pass = "Automation!";

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginTitle")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginNowBtn")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createAccountBtn")));

        // Try logging without entering information
        driver.findElement(By.id("loginNowBtn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginTitle")));

        // Now enter the login information
        driver.findElement(By.id("loginEmail")).sendKeys(email);

        // Try entering without password
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginTitle")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginNowBtn")));

        // Enter the password and continue
        driver.findElement(By.id("loginPassword")).sendKeys(pass);

        driver.findElement(By.id("loginNowBtn")).click();
    }

    public void genericLogout(){
        // Finally log out
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("profileListItem")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("verticalMenuBtn")));
        driver.findElement(By.id("verticalMenuBtn")).findElement(By.tagName("i")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Log Out')]")));
        driver.findElement(By.xpath("//*[contains(text(),'Log Out')]")).click();

        // Check once again we are at the login page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginTitle")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginNowBtn")));
    }
}
