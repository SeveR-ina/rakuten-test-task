package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalInfoPage extends BasePage {
    @FindBy(css = "a[data-qa-id='side-menu-cashbackAndPoints']")
    private WebElement cashbackAndPointsItem;

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    public PersonalInfoPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Cash and Points page in the Account section.
     */
    @Step("Click on 'Cash and Points' on the left side panel")
    public void openCashAndPointsPage() {
        waitForVisibilityOf(cashbackAndPointsItem);
        cashbackAndPointsItem.click();
    }

    /**
     * Gets value of first name input.
     */
    @Step("Get First name from disabled input on Personal Information tab")
    public String getFirstName() {
        return firstNameInput.getAttribute("value");
    }

    /**
     * Gets value of last name input.
     */
    @Step("Get Last name from disabled input on Personal Information tab")
    public String getLastName() {
        return lastNameInput.getAttribute("value");
    }
}
