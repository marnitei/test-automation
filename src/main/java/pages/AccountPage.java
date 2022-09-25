package pages;

import utilities.SystemProperty;
import utilities.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountPage extends BasePageObject {

    private final By selectListOfAccountsButton = By.xpath("//button[@title='Select a List View']"),
            newAccountButton = By.linkText("New"),
            editAccountDetailsButton = By.xpath("//*[@title='Edit']"),
            accountNameLabelLocator = By.xpath("//*[text()='Account Name']/parent::label/following-sibling::*"),
            accountNameElementInput = By.tagName("input"),
            accountNameElementClearButton = By.className("deleteAction"),
            accountPhoneInput = By.xpath("//*[text()='Phone']/parent::label/following-sibling::input"),
            accountFaxInput = By.xpath("//*[text()='Fax']/parent::label/following-sibling::input"),
            accountWebsiteInput = By.xpath("//*[text()='Website']/parent::label/following-sibling::input"),
            saveAccountRecordButton = By.xpath("//button[@title='Save']");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Accounts List Overview Page
     */

    public AccountPage goToAccountListOverview() {
        driver.get(SystemProperty.getValue("ACCOUNTLIST_PAGE"));
        return this;
    }

    public AccountPage openAllAccountsList() {
        waitFor(selectListOfAccountsButton, WaitUtils.visible, 10);
        driver.findElement(selectListOfAccountsButton).click();
        waitFor(By.xpath("//a/*[text()='All Accounts']"), WaitUtils.visible, 10);
        driver.findElement(By.xpath("//a/*[text()='All Accounts']")).click();
        waitForSpinner();
        return this;
    }

    public AccountPage openAccountRecord(String accountName) {
        waitFor(By.linkText(accountName), WaitUtils.clickable, 10);
        driver.findElement(By.linkText(accountName)).click();
        return this;
    }

    public AccountPage clickOnNew() {
        waitFor(newAccountButton, WaitUtils.visible, 10);
        driver.findElement(newAccountButton).click();
        waitFor(accountNameLabelLocator, WaitUtils.visible, 10);
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
        waitFor(saveAccountRecordButton, WaitUtils.invisible, 10);
        waitForSpinner();
        return this;
    }

    public AccountPage clickOnEdit() {
        waitFor(editAccountDetailsButton, WaitUtils.clickable, 10);
        driver.findElement(editAccountDetailsButton).click();
        waitFor(By.xpath("//*[text()='Account Name']"), WaitUtils.visible, 10);
        return this;
    }

    /**
     * Getting values from Account
     */

    public String getAccountNameFieldValue() {
        waitFor(By.xpath("//*[text()='Account Name']/parent::div/following-sibling::div//lightning-formatted-text"), WaitUtils.clickable, 10);
        return driver.findElement(By.xpath("//*[text()='Account Name']/parent::div/following-sibling::div//lightning-formatted-text")).getText();
    }

    public String getAccountFaxFieldValue() {
        return driver.findElement(By.xpath("//*[text()='Fax']/parent::div/following-sibling::div//lightning-formatted-phone")).getText();
    }

    public String getAccountPhoneFieldValue() {
        return driver.findElement(By.xpath("//*[text()='Phone']/parent::div/following-sibling::div//lightning-formatted-phone")).getText();
    }

    public String getAccountWebsiteFieldValue() {
        return driver.findElement(By.xpath("//*[text()='Website']/parent::div/following-sibling::div//lightning-formatted-url")).getText();
    }

    private void setTextValue(By inputField, String value) {
        driver.findElement(inputField).clear();
        driver.findElement(inputField).sendKeys(value);
    }
}
