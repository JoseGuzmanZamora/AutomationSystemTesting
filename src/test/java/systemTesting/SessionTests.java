package systemTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SessionTests extends InitDriver {

    @BeforeClass(alwaysRun = true)
    public void initUrl(){
        driver.get(url);
    }

    @Test(groups = {"loginAndOut", "all"})
    public void loginAndOut(){
        genericLogin();

        // And check that you got to the front page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Donal Tromp')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'ingles')]")));

        genericLogout();
    }
}
