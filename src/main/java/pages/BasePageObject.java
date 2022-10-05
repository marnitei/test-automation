package pages;

import utilities.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

abstract class BasePageObject {

    public WebDriver driver;

    final By alertBox = By.cssSelector(".forceToastMessage");

    /**
     * Constructor
     */
    public BasePageObject(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Custom Wait method based on Fluent Wait
     * Waits for element to be in condition till
     *
     * @param locator          By element locator
     * @param conditions       WaitUtils conditions
     * @param timeoutOfSeconds int amount of seconds
     * @return true if WaitUtils condition is met
     */
    public boolean waitFor(By locator, WaitUtils conditions, int timeoutOfSeconds) {
        Wait<WebDriver> waitForTests = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutOfSeconds))
                .ignoring(NoSuchElementException.class);
        try {
            waitForTests.until(conditions.getType().apply(locator));
            return true;
        } catch (TimeoutException ex) {
            System.out.println("!!!Warning: Element isn't got exception condition. Element: " + locator);
            return false;
        }
    }

    public String getAlert() {
        waitFor(alertBox, WaitUtils.visible, 3);
        return driver.findElement(alertBox).getText();
    }

    public void waitForSpinner() {
        waitFor(By.className("lightning-spinner"), WaitUtils.invisible, 20);
    }

}
