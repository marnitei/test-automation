package baseUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import pages.OrgLoginPage;
import pages.AccountPage;
import utilities.SystemProperty;

import java.time.Duration;

public class BaseWebTest {
    private WebDriver driver;

    protected OrgLoginPage orgLoginPage;
    protected AccountPage accountPage;

    @BeforeSuite
    protected void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        orgLoginPage = new OrgLoginPage(driver);
        accountPage = new AccountPage(driver);
    }

    @BeforeClass
    protected void login() {
        orgLoginPage.setUsername(SystemProperty.getValue("USER_NAME"))
                .setPassword(SystemProperty.getValue("PASSWORD"))
                .clickOnLogin();
    }

    @AfterClass
    protected void closeWindow() {
        driver.close();
    }

    @AfterSuite(alwaysRun = true)
    protected void tearDown() {
        driver.quit();
        ProcessHandle.allProcesses()
                .filter(p -> p.info().commandLine().map(c -> c.contains("chromedriver")).orElse(false))
                .findFirst()
                .ifPresent(ProcessHandle::destroy);
    }
}
