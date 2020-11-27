package systemTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;

public class InitDriver {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private String geckoDriver = "";
    private String chromeDriver = "";
    public String url = "https://flashcards-ufm.web.app";

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void openDriver(@Optional("chrome") String browser) throws IOException {
        initDriver(browser);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private WebDriver initDriver(String browser) {
        try {
            selectDriverBasedOnOs();
            if (browser.equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", geckoDriver);

                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            } else {
                if (browser.equals("chrome")) {
                    System.setProperty("webdriver.chrome.driver", chromeDriver);
                    DesiredCapabilities caps = DesiredCapabilities.chrome();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments(new String[]{"--start-maximized"});
                    options.merge(caps);
                    driver = new ChromeDriver(options);

                } else if (browser.equals("ie")) {
                    System.setProperty("webdriver.ie.driver", "drivers/windows/IEDriverServer.exe");
                    driver = new InternetExplorerDriver();
                    driver.manage().window().maximize();
                } else if (browser.equals("safari")) {
                    driver = new SafariDriver();
                    driver.manage().window().maximize();
                }
            }

            wait = new WebDriverWait(driver, 15, 100);
            return driver;
        } catch (Exception var5) {
            var5.printStackTrace();
            Assert.fail("fail to init the browser");
            return null;
        }
    }

    private void selectDriverBasedOnOs(){
        String operativeSystem = System.getProperty("os.name").toLowerCase();
        if(operativeSystem.contains("mac")){
            geckoDriver = "drivers/mac/geckodriver";
            chromeDriver = "drivers/mac/chromedriver";
        }else if(operativeSystem.contains("windows")){
            geckoDriver = "drivers/windows/geckodriver.exe";
            chromeDriver = "drivers/windows/chromedriver.exe";
        }
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
