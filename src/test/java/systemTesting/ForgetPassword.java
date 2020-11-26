package systemTesting;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ForgetPassword extends InitDriver {

    @BeforeClass(alwaysRun = true)
    public void initUrl(){
        driver.get(url);
    }

    @Test(groups = {"forgetPassword", "all"})
    public void forgetPassword(){
        //Faker setup
        Faker faker = new Faker();
        String genericPass = "automation";

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginTitle")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginNowBtn")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createAccountBtn")));

        // Find the forgot password element
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Forgot Password?')]")));
        driver.findElement(By.xpath("//*[contains(text(),'Forgot Password?')]")).click();

        // Once we ar in, check for the important signs that it is working
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'reset link')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resetPasswordBtn")));

        driver.findElement(By.id("resetEmail")).sendKeys(faker.internet().emailAddress());
        driver.findElement(By.id("resetPasswordBtn")).click();

        // Now enter the information
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Reset Password')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resetNewPassword")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resetNewPassword2")));

        driver.findElement(By.id("resetNewPassword")).sendKeys(faker.name().username());
        driver.findElement(By.id("resetNewPassword2")).sendKeys(faker.name().username());
    }
}
