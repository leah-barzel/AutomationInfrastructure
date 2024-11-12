package Drivers;

import Config.ConfigReader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

/**
 * URLManager handles the URL navigation for the WebDriver.
 */
@Getter
public class URLManager {

    private final WebDriver driver; // WebDriver injected via constructor
    private final ConfigReader configReader; // Reader for configuration properties
    private final String url;

    /**
     * Constructor that accepts a WebDriver instance and a configuration file path.
     */
    public URLManager(WebDriver driver, String configFilePath) {
        this.driver = driver;
        this.configReader = new ConfigReader(configFilePath);
        this.url = configReader.getUrl();
    }

    /**
     * Navigates to the specified URL.
     */
    public void navigateTo() {
        if (driver != null) {
            driver.get(url);
        } else {
            throw new IllegalStateException("Driver is not initialized.");
        }
    }

    /**
     * Retrieves the current URL from the WebDriver.
     *
     * @return the current URL.
     */
    public String getCurrentUrl() {
        if (driver != null) {
            return driver.getCurrentUrl();
        } else {
            throw new IllegalStateException("Driver is not initialized.");
        }
    }
}
