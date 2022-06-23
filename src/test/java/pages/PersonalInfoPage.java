package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalInfoPage extends BasePage {
    @FindBy(css = "a[data-qa-id='side-menu-cashbackAndPoints']")
    private WebElement cashbackAndPointsItem;

    public PersonalInfoPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Open Cash and Points page in the Account section.
     */
    @Step("Click on 'Cash and Points' on the left side panel")
    public void openCashAndPointsPage() {
        waitForVisibilityOf(cashbackAndPointsItem);
        cashbackAndPointsItem.click();
    }

}
