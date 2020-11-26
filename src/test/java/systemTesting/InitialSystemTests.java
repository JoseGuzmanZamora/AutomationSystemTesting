package systemTesting;


import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InitialSystemTests extends InitDriver {

    @BeforeClass(alwaysRun = true)
    public void initUrl(){
        driver.get(url);
    }

    @Test(groups = {"createAccount", "all"})
    public void createAccount(){
        //Faker setup
        Faker faker = new Faker();
        String genericPass = "automation";

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginTitle")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginNowBtn")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createAccountBtn")));

        driver.findElement(By.id("createAccountBtn")).click();

        // Now we are inside of the create account view
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Sign Up')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Username')]")));

        //Check for validation
        driver.findElement(By.id("signUpBtn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Sign Up')]")));


        // We verified we are in the create account now we send keys to the form
        Name name = faker.name();
        driver.findElement(By.id("signUpUsername")).sendKeys(name.username());
        driver.findElement(By.id("signUpEmail")).sendKeys(faker.internet().emailAddress());
        driver.findElement(By.id("signUpPassword")).sendKeys(genericPass);

        // Finally do sign up
        driver.findElement(By.id("signUpBtn")).click();

        // And check that you got to the front page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Donal Tromp')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'ingles')]")));

        // Finally log out
        genericLogout();
    }
}
