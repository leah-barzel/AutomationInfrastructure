package Drivers;

import Config.ConfigReader;
import Logging.LoggerManager;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

/**
 * WebDriverManager manages the initialization and lifecycle of the WebDriver instance.
 * It reads browser and URL configurations from a properties file.
 */
@Getter
public class WebDriverManager {

    protected WebDriver driver;
    private final ConfigReader configReader; // Reader for configuration properties
    private final String browser; // Browser type to be used
    private final String url; // URL to navigate to

    /**
     * Constructs a WebDriverManager instance with the specified configuration file path.
     *
     * @param configFilePath the path to the configuration properties file
     */
    public WebDriverManager(String configFilePath) {
        this.configReader = new ConfigReader(configFilePath);
        this.browser = configReader.getBrowser();
        this.url = configReader.getUrl();
    }

    /**
     * Initializes the WebDriver instance based on the specified browser type.
     */
    public void initDriver() {
        try {
            driver = DriverFactory.getDriver(browser);
            LoggerManager.info("Driver initialized for browser: " + browser);
        } catch (Exception e) {
            LoggerManager.error("Failed to initialize driver for browser: " + browser, e);
        }
    }


    /**
     * Maximizes the browser window if the WebDriver is initialized.
     */
    public void maximizeWindow() {
        if (driver != null) {
            driver.manage().window().maximize();
        }
    }

    /**
     * Closes the WebDriver and quits the browser if it is initialized.
     */
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
