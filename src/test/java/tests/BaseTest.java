package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.LoginPage;
import utils.ConfigReader;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static WebDriver driver;
    protected static String baseUrl;

    @BeforeAll
    public static void setup() {

        ConfigReader.loadProperties();
        baseUrl = ConfigReader.getProperty("baseUrl");

        System.out.println("Base URL loaded: " + baseUrl);

        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new IllegalStateException("Base URL is not properly set in the config.properties file.");
        }

        String browser = ConfigReader.getProperty("browser");

        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get(baseUrl);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
    }



    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
