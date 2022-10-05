package pages;

import utilities.SystemProperty;
import utilities.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountPage extends BasePageObject {

    private final int timeout = 15;
    private By selectListOfAccountsButton = By.xpath("//button[@title='Select a List View']"),
            newAccountButton = By.linkText("New"),
            editAccountDetailsButton = By.xpath("//*[@title='Edit']"),
            accountNameLabelLocator = By.xpath("//*[text()='Account Name']/parent::label/parent::*"),
            accountNameElementInput = By.tagName("input"),
            accountNameElementClearButton = By.cssSelector("a.deleteAction"),
            accountPhoneInput = By.xpath("//*[text()='Phone']/parent::label/following-sibling::input"),
            accountFaxInput = By.xpath("//*[text()='Fax']/parent::label/following-sibling::input"),
            accountWebsiteInput = By.xpath("//*[text()='Website']/parent::label/following-sibling::input"),
            saveAccountRecordButton = By.xpath("//button[@title='Save']"),
            accountNameOutput = By.xpath("//*[text()='Account Name']/parent::div/following-sibling::div//lightning-formatted-text"),
            accountPhoneOutput = By.xpath("//*[text()='Phone']/parent::div/following-sibling::div//lightning-formatted-phone"),
            accountFaxOutput = By.xpath("//*[text()='Fax']/parent::div/following-sibling::div//lightning-formatted-phone"),
            accountWebsiteOutput = By.xpath("//*[text()='Website']/parent::div/following-sibling::div//lightning-formatted-url");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Accounts List Overview Page
     */

    public AccountPage goToAccountListOverview() {
        driver.get(SystemProperty.getValue("ACCOUNTLIST_PAGE"));
        waitForSpinner();
        return this;
    }

    public AccountPage openAllAccountsList() {
        waitFor(selectListOfAccountsButton, WaitUtils.visible, timeout);
        driver.findElement(selectListOfAccountsButton).click();
        waitFor(By.xpath("//a/*[text()='All Accounts']"), WaitUtils.visible, timeout);
        driver.findElement(By.xpath("//a/*[text()='All Accounts']")).click();
        waitFor(By.xpath("//a/*[text()='All Accounts']"), WaitUtils.invisible, timeout);
        waitForSpinner();
        return this;
    }

    public AccountPage openAccountRecord(String accountName) {
        waitFor(By.linkText(accountName), WaitUtils.clickable, timeout);
        driver.findElement(By.linkText(accountName)).click();
        return this;
    }

    public AccountPage clickOnNew() {
        waitFor(newAccountButton, WaitUtils.visible, timeout);
        driver.findElement(newAccountButton).click();
        waitFor(accountNameLabelLocator, WaitUtils.visible, timeout);
        return this;
    }

    /**
     * Set up Account values
     */

    public AccountPage fillAccountName(String accountNameToSet) {
        WebElement accountNameLabel = driver.findElement(accountNameLabelLocator);
        try {
            accountNameLabel.findElement(accountNameElementClearButton).click();
        } catch (NoSuchElementException exception) {
            accountNameLabel.findElement(accountNameElementInput).clear();
        }
        accountNameLabel.findElement(accountNameElementInput).sendKeys(accountNameToSet);
        return this;
    }

    public AccountPage selectAccountName(String accountNameToSelect) {
        WebElement accountNameLabel = driver.findElement(accountNameLabelLocator);
        try {
            accountNameLabel.findElement(accountNameElementClearButton).click();
        } catch (NoSuchElementException exception) {
            accountNameLabel.findElement(accountNameElementInput).clear();
        }
        accountNameLabel.findElement(accountNameElementInput).sendKeys(accountNameToSelect, Keys.ARROW_DOWN, Keys.ENTER);
        return this;
    }

    public AccountPage setAccountPhone(String accountPhoneToSet) {
        setTextValue(accountPhoneInput, accountPhoneToSet);
        return this;
    }

    public AccountPage setAccountFax(String accountFaxToSet) {
        setTextValue(accountFaxInput, accountFaxToSet);
        return this;
    }

    public AccountPage setAccountWebsite(String accountWebsiteToSet) {
        setTextValue(accountWebsiteInput, accountWebsiteToSet);
        return this;
    }

    /**
     * Account button clicks
     */

    public AccountPage clickOnSave() {
        driver.findElement(saveAccountRecordButton).click();
        waitFor(saveAccountRecordButton, WaitUtils.invisible, timeout);
        waitForSpinner();
        return this;
    }

    public AccountPage clickOnEdit() {
        waitFor(editAccountDetailsButton, WaitUtils.clickable, timeout);
        driver.findElement(editAccountDetailsButton).click();
        waitFor(By.xpath("//*[text()='Account Name']"), WaitUtils.visible, timeout);
        return this;
    }

    /**
     * Getting values from Account
     */

    public String getAccountNameFieldValue() {
        waitFor(accountNameOutput, WaitUtils.clickable, timeout);
        return driver.findElement(accountNameOutput).getText();
    }

    public String getAccountFaxFieldValue() {
        waitFor(accountFaxOutput, WaitUtils.clickable, timeout);
        return driver.findElement(accountFaxOutput).getText();
    }

    public String getAccountPhoneFieldValue() {
        waitFor(accountPhoneOutput, WaitUtils.clickable, timeout);
        return driver.findElement(accountPhoneOutput).getText();
    }

    public String getAccountWebsiteFieldValue() {
        waitFor(accountWebsiteOutput, WaitUtils.clickable, timeout);
        return driver.findElement(accountWebsiteOutput).getText();
    }

    private void setTextValue(By inputField, String value) {
        waitFor(inputField, WaitUtils.clickable, timeout);
        driver.findElement(inputField).clear();
        driver.findElement(inputField).sendKeys(value);
    }
}
