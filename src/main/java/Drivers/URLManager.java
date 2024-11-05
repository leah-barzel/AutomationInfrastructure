package Drivers;

import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;

/**
 * URLManager handles the URL navigation for the WebDriver.
 */
@AllArgsConstructor
public class URLManager {

    private final WebDriver driver;
    private final String url;


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
