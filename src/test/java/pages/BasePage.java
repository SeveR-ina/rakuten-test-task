package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TimeOuts;

import java.time.Duration;

/**
 * Parent page for all pages includes initing elements on the page and waiting for elements.
 */
abstract class BasePage {
    protected WebDriver driver;
    private final Duration defaultTime;

    /**
     * Constructor of Base page class.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.defaultTime = Duration.ofSeconds(TimeOuts.DEFAULT_TIMEOUT_IN_SECONDS.getTimeOutValue());
        PageFactory.initElements(driver, this);
    }

    /**
     * Waits for visibility of the page element for default time.
     */
    @Step("Wait for visibility of element: {0}")
    protected void waitForVisibilityOf(WebElement webElement) {
        new WebDriverWait(driver, defaultTime).
                until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Waits for presence of the page element for default time and returns this element.
     */
    @Step("Wait for presence of element: {0}")
    protected WebElement waitForElementPresenceAndReturnIt(By element) {
        return new WebDriverWait(driver, defaultTime).
                until(ExpectedConditions.presenceOfElementLocated(element));
    }

    /**
     * Waits for any page elements for default time.
     */
    @Step("Wait for availability of elements")
    protected void defaultImplicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TimeOuts.DEFAULT_TIMEOUT_IN_SECONDS.getTimeOutValue()));
    }

    /**
     * Performs Mouse Hover action.
     */
    @Step("Perform Mouse Hover action for element: {0}")
    protected void hoverOver(WebElement element) {
        Actions actions = new Actions(driver);

        //Hovering on element
        actions.moveToElement(element);
    }

    /**
     * Fills the inputs with specific text.
     */
    @Step("Enter text: {text} to the input: {field}}")
    protected void sendKeys(WebElement field, String text) {
        field.click();
        field.clear();
        field.sendKeys(text);
    }
}
