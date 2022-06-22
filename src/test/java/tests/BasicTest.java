package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.TimeOuts;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

/**
 * Basic class for all tests: creates driver instances,
 * works with properties and capabilities, manages drivers.
 */
public abstract class BasicTest {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private final static String TEST_PROPERTIES_PATH = "./src/test/resources/test.properties";
    protected Properties testProperties;

    /**
     * Sets capabilities for browsers.
     * Creates driver instances.
     * Opens browser with start URL, maximizes the window, and deletes all cookies.
     */
    protected void doPreparationsFor(String browser, boolean headless) {
        createAndSetCapabilities(browser);
        uploadDriverAndInitializeBaseDriver(browser, headless);
        driver.get().get((testProperties.getProperty("homeURL")));
        manageDriver();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Creates and initializes properties.
     */
    protected void initializeProperties() {
        testProperties = new Properties();
    }

    /**
     * Loads/Reads all properties.
     */
    protected void loadPropertiesFromFile() {
        try {
            testProperties.load(new FileInputStream(TEST_PROPERTIES_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates and sets capabilities for browser.
     *
     * @param browser can be chosen via parameter and value from testng xml.
     */
    private void createAndSetCapabilities(String browser) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browser", browser);
    }

    /**
     * Quits this driver and closes its window.
     */
    protected void quitDriver() {
        driver.get().quit();
    }

    /**
     * Uploads driver and, if necessary, adds options to it.
     * Initializes default driver.
     *
     * @param browser  can be chosen via parameter and value from testng xml.
     * @param headless can be chosen via parameter and value from testng xml.
     */
    private void uploadDriverAndInitializeBaseDriver(String browser, boolean headless) {
        switch (browser) {
            case "Chrome":
                uploadChromeDriverAndInitBaseDriver(headless);
                break;
            case "FireFox":
                uploadFireFoxDriverAndInitBaseDriver(headless);
                break;
            case "Edge":
                uploadEdgeDriverAndInitBaseDriver(headless);
                break;
            default:
                System.out.println("Wrong Browser's name");
                break;
        }
    }

    /**
     * Maximizes the window, waits, and deletes cookies.
     */
    private void manageDriver() {
        driver.get().manage().window().maximize();
        driver.get().manage().timeouts().implicitlyWait(
                Duration.ofSeconds(TimeOuts.DEFAULT_TIMEOUT_IN_SECONDS.getTimeOutValue()));
        driver.get().manage().deleteAllCookies();
    }

    /**
     * Uploads Chrome driver.
     * If necessary, creates Chrome options.
     * Initializes default driver.
     */
    private void uploadChromeDriverAndInitBaseDriver(boolean headless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        driver.set(new ChromeDriver(options));
    }

    /**
     * Uploads Edge driver.
     * If necessary, creates Edge options.
     * Initializes default driver.
     */
    private void uploadEdgeDriverAndInitBaseDriver(boolean headless) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        driver.set(new EdgeDriver(options));
    }

    /**
     * Uploads Firefox driver.
     * If necessary, creates Firefox options.
     * Initializes default driver.
     */
    private void uploadFireFoxDriverAndInitBaseDriver(boolean headless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        driver.set(new FirefoxDriver(options));
    }
}
