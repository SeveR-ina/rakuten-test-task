package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(css = "div[data-qa-id='profile-dropdown']")
    private WebElement profileDropDown;

    @FindBy(id = "didomi-notice-agree-button")
    private WebElement acceptCookiesButton;

    @FindBy(css = "#wps_popup div div")
    private WebElement closeSuggestionButton;

    @FindBy(id = "emarsys-consent-cancel")
    private WebElement notNowWithNotificationsButton;

    private final By registerButtonBy = By.id("nav-header-register");
    private final By myAccountItemBy = By.cssSelector("div[data-qa-id='profile-dropdown-link-row1-0']");

    private final By closeSuggestionButtonBy = By.cssSelector("#wps_popup div div");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Goes to the Registration page.
     */
    @Step("Go to Sign Up page")
    public void goToSignUpPage() {
        waitForVisibilityOf(profileDropDown);

        hoverOverProfileIconAndClickOn(registerButtonBy);
    }

    /**
     * Goes to the Account page.
     */
    @Step("Go to Account page")
    public void goToAccountPage() {
        defaultImplicitWait();
        waitForVisibilityOf(profileDropDown);

        hoverOverProfileIconAndClickOn(myAccountItemBy);
    }

    /**
     * If a pop-up is visible, click on the 'Not now' button.
     */
    @Step("Reject all notifications if this pop up is visible")
    public void rejectNotificationsIfVisible() {
        defaultImplicitWait();
        if (!driver.findElements(By.id("emarsys-consent-cancel")).isEmpty()) {
            notNowWithNotificationsButton.click();
        }
    }

    /**
     * Performs Mouse Hover action on the Profile icon and then clicks on the necessary menu item.
     */
    @Step("Hover Over Profile icon and click on specific menu option: {0}")
    private void hoverOverProfileIconAndClickOn(By menuItemBy) {
        WebElement menuItem;
        do {
            hoverOver(profileDropDown);
            profileDropDown.click();
            menuItem = waitForElementPresenceAndReturnIt(menuItemBy);
        } while (!waitForElementPresenceAndReturnIt(menuItemBy).isDisplayed());
        menuItem.click();
    }

    /**
     * Clicks on accept cookies button.
     */
    @Step("Accept cookies")
    public void acceptCookies() {
        waitForVisibilityOf(acceptCookiesButton);
        acceptCookiesButton.click();
    }

    /**
     * Closes the pop-up.
     */
    @Step("Close pop-up")
    public void closeSuggestion() {
        defaultImplicitWait();
        while (!waitForElementPresenceAndReturnIt(closeSuggestionButtonBy).isDisplayed()) {
            waitForVisibilityOf(closeSuggestionButton);
        }
        closeSuggestionButton.click();
    }
}
