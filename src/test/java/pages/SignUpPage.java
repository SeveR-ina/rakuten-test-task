package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * User registration page of a web-site.
 */
public class SignUpPage extends BasePage {

    @FindBy(css = "input[aria-label='First name']")
    private WebElement firstNameInput;

    @FindBy(css = "input[aria-label='Last name']")
    private WebElement lastNameInput;

    @FindBy(css = "input[aria-label='Email']")
    private WebElement emailInput;

    @FindBy(css = "input[aria-label='Password']")
    private WebElement passInput;

    @FindBy(xpath = "//div[@role='checkbox']/div/div[contains(text(),'By registering you')]")
    private WebElement divAgreement;

    @FindBy(css = "div[role='button'] div")
    private WebElement createAccButton;

    @FindBy(xpath = "//div[@role='button']/div[contains(text(),'registration')]")
    private WebElement completeRegistrationButton;

    private final By firstNameInputBy = By.cssSelector("input[aria-label='First name']");

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Creates a new account by filling all required fields.
     */
    @Step("Fill a registration form with the test data: {firstName}, {lastName} and {email} / {pass}")
    public void fillRegisterForm(String firstName, String lastName, String email, String pass) {
        defaultImplicitWait();
        while (driver.findElements(firstNameInputBy).isEmpty()) {
            waitForVisibilityOf(firstNameInput);
        }
        sendKeys(firstNameInput, firstName);
        sendKeys(lastNameInput, lastName);
        sendKeys(emailInput, email);
        sendKeys(passInput, pass);
        divAgreement.click();
        createAccButton.click();
    }

    /**
     * Clicks on the Complete Registration button.
     */
    @Step("Complete a new account registration")
    public void completeRegistration() {
        waitForVisibilityOf(completeRegistrationButton);
        completeRegistrationButton.click();
    }
}
