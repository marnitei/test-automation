package pages;

import utilities.SystemProperty;
import utilities.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrgLoginPage extends BasePageObject {
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("Login");

    public OrgLoginPage(WebDriver driver) {
        super(driver);
        driver.get(SystemProperty.getValue("ORG_LOGIN_PAGE"));
        waitForSpinner();
    }

    public OrgLoginPage setUsername(String username) {
        waitFor(usernameField, WaitUtils.visible, 10);
        driver.findElement(usernameField).sendKeys(username);
        return this;
    }

    public OrgLoginPage setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public void clickOnLogin() {
        driver.findElement(loginButton).click();
        waitFor(loginButton,WaitUtils.invisible, 10);
    }
}
