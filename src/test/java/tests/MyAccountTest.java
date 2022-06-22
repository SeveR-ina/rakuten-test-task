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
 * Main test class with checking account functionality.
 */
@Listeners({TestListener.class})
@Epic("Regression Tests")
@Feature("Account Tests")
public class MyAccountTest extends BasicTest {

    private HomePage homePage;

    private AccountInfoData accData;

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
     * Opens Home page and closes all pop-ups.
     *
     * @param browser  can be chosen via parameter and value from testng xml.
     * @param headless can be chosen via parameter and value from testng xml.
     */
    @Parameters({"browser", "headless"})
    @BeforeMethod
    public void doBeforeMethod(String browser, boolean headless) {

        //All activities with drivers:
        doPreparationsFor(browser, headless);

        openHomePageAndCloseAllPopUps();
    }

    @AfterSuite
    public void closeBrowser() {
        quitDriver();
    }

    @Test(description = "Verify /personal-information url and " +
            "account points include zeros after registration.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Story : Verify account page url and account points after registration")
    public void checkPersonalInfoUrlAndAccountPointsAfterRegistration() {

        createNewUser();

        homePage.rejectNotificationsIfVisible();

        //Go to the Account section:
        homePage.goToAccountPage();

        String actualURL = driver.get().getCurrentUrl();
        String accountURL = testProperties.getProperty("accountURL");

        //Assert actual account url and expected url:
        Assert.assertEquals(actualURL, accountURL,
                "Actual page URL: " + actualURL + " is not " + accountURL);

        String personalInformation = testProperties.getProperty("personalInformation");

        //Assert if actual account url contains expected phrase:
        Assert.assertTrue(actualURL.contains(personalInformation),
                "Current URL doesn't contain " + personalInformation);

        PersonalInfoPage personalInfoPage = new PersonalInfoPage(driver.get());

        //Go to Cash and Points page:
        personalInfoPage.openCashAndPointsPage();

        CashbackAndRewardsPage cashbackAndRewardsPage = new CashbackAndRewardsPage(driver.get());

        String actualAvailablePoints = cashbackAndRewardsPage.getValueOfAvailablePoints();
        String defaultPointsValue = testProperties.getProperty("defaultPointsValue");

        //Assert the Available points:
        Assert.assertEquals(actualAvailablePoints, defaultPointsValue,
                "Actual available points ... " + actualAvailablePoints + " don't equal " + defaultPointsValue);

        String actualPendingPoints = cashbackAndRewardsPage.getValueOfPendingPoints();

        //Assert the Pending points:
        Assert.assertEquals(actualPendingPoints, defaultPointsValue,
                "Actual pending points ... " + actualPendingPoints + " don't equal " + defaultPointsValue);
    }

    @Test(description = "Verify First and Last name after registration")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Story : Verify, that after registration, user has the same First and Last name.")
    public void checkFirstAndLastNameAfterRegistration() {

        createNewUser();

        homePage.rejectNotificationsIfVisible();

        //Go to the Account section:
        homePage.goToAccountPage();

        PersonalInfoPage personalInfoPage = new PersonalInfoPage(driver.get());

        String firstNameAfterRegistration = personalInfoPage.getFirstName();
        String usersFirstNameDuringRegistration = accData.getFirstName();

        //Assert if user's first name after registration equal user's first name during a registration
        Assert.assertEquals(firstNameAfterRegistration, usersFirstNameDuringRegistration,
                "Actual First name of user ... " + firstNameAfterRegistration +
                        " doesn't equal " + usersFirstNameDuringRegistration);

        String lastNameAfterRegistration = personalInfoPage.getLastName();
        String usersLastNameDuringRegistration = accData.getLastName();

        //Assert if user's last name after registration equal user's last name during a registration
        Assert.assertEquals(lastNameAfterRegistration, usersLastNameDuringRegistration,
                "Actual Last name of user ... " + lastNameAfterRegistration +
                        " doesn't equal " + usersLastNameDuringRegistration);
    }

    private void openHomePageAndCloseAllPopUps() {
        homePage = new HomePage(driver.get());

        homePage.acceptCookies();

        homePage.closeSuggestion();
    }

    private void createNewUser() {
        //Create test data object
        String domain = testProperties.getProperty("domain");
        accData = getRandomUser(domain);

        homePage.goToSignUpPage();
        SignUpPage signUpPage = new SignUpPage(driver.get());

        //Register a new user:
        signUpPage.fillRegisterForm(accData.getFirstName(), accData.getLastName(),
                accData.getEmail(), accData.getPass());
        signUpPage.completeRegistration();
    }
}
