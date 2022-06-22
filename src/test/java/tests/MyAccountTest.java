package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CashbackAndRewardsPage;
import pages.HomePage;
import pages.PersonalInfoPage;
import pages.SignUpPage;
import testData.AccountInfoData;
import utils.listeners.TestListener;
import static testData.UserGenerator.getRandomUser;

/**
 * Main test class with checking registration, profile url and points.
 */
@Listeners({TestListener.class})
@Epic("Regression Tests")
@Feature("Account Tests")
public class MyAccountTest extends BasicTest {

    private HomePage homePage;

    /**
     * Sets and loads test properties.
     */
    @BeforeTest
    public void doBeforeTest() {
        initializeProperties();
        loadPropertiesFromFile();
    }

    /**
     * Opens Browser, goes to start URL, accepts cookies, asserts that start page is not null.
     *
     * @param browser  can be chosen via parameter and value from testng xml.
     * @param headless can be chosen via parameter and value from testng xml.
     */
    @Parameters({"browser", "headless"})
    @BeforeMethod
    public void doBeforeMethod(String browser, boolean headless) {
        doPreparationsFor(browser, headless);

        homePage = new HomePage(driver);

        homePage.acceptCookies();

        homePage.closeSuggestion();
    }

    @AfterMethod
    public void closeBrowser() {
        quitDriver();
    }

    @Test(description = "Verify successful registration," +
            "/personal-information url and zero points include zeros.", threadPoolSize = 6)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Story : Verify the sign up and account page functionality")
    public void checkRegistrationAndPersonalInfoURLAndPoints() {

        //Create test data object
        String domain = testProperties.getProperty("domain");
        AccountInfoData accData = getRandomUser(domain);

        homePage.goToSignUpPage();
        SignUpPage signUpPage = new SignUpPage(driver);

        //Register a new user
        signUpPage.fillRegisterForm(accData.getFirstName(), accData.getLastName(),
                accData.getEmail(), accData.getPass());
        signUpPage.completeRegistration();

        homePage.rejectNotificationsIfVisible();

        //Go to the Account section
        homePage.goToAccountPage();

        String actualURL = driver.getCurrentUrl();
        String accountURL = testProperties.getProperty("accountURL");

        Assert.assertEquals(actualURL, accountURL,
                "Actual page URL: " + actualURL + " is not " + accountURL);

        String personalInformation = testProperties.getProperty("personalInformation");

        Assert.assertTrue(actualURL.contains(personalInformation),
                "Current URL doesn't contain " + personalInformation);

        PersonalInfoPage personalInfoPage = new PersonalInfoPage(driver);

        //Go to Cash and Points page
        personalInfoPage.openCashAndPointsPage();

        CashbackAndRewardsPage cashbackAndRewardsPage = new CashbackAndRewardsPage(driver);

        String actualAvailablePoints = cashbackAndRewardsPage.getValueOfAvailablePoints();
        String defaultPointsValue = testProperties.getProperty("defaultPointsValue");

        Assert.assertEquals(actualAvailablePoints, defaultPointsValue,
                "Actual available points ... " + actualAvailablePoints + " don't equal " + defaultPointsValue);

        String actualPendingPoints = cashbackAndRewardsPage.getValueOfPendingPoints();
        Assert.assertEquals(actualPendingPoints, defaultPointsValue,
                "Actual pending points ... " + actualPendingPoints + " don't equal " + defaultPointsValue);
    }
}
