package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CashbackAndRewardsPage extends BasePage {

    @FindBy(css = "span[data-qa-id='rewards-banner-points-amount-left-side']")
    private WebElement availablePoints;

    @FindBy(css = "span[data-qa-id='rewards-banner-points-amount-right-side']")
    private WebElement pendingPoints;

    public CashbackAndRewardsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Waits and gets the value of the Available points.
     */
    @Step("Wait and get the value of the available points")
    public String getValueOfAvailablePoints() {
        defaultImplicitWait();
        while (availablePoints.getText().equals("--")) {
            defaultImplicitWait();
        }
        return availablePoints.getText();
    }

    /**
     * Gets the value of the Pending points.
     */
    @Step("Get the value of the pending points")
    public String getValueOfPendingPoints() {
        return pendingPoints.getText();
    }
}
