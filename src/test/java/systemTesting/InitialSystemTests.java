package systemTesting;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InitialSystemTests extends InitDriver {

    @BeforeClass(alwaysRun = true)
    public void initUrl(){
        driver.get("http://localhost:8080");
    }

    @Test(groups = {"createAccount"})
    public void createAccount(){
    }
}
