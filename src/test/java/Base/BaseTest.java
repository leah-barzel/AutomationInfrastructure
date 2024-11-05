package Base;

import Drivers.URLManager;
import Drivers.WebDriverManager;
import Logging.LoggerManager;
import Strategies.ModalStrategy;
import Utils.*;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import Strategies.AcceptAlertStrategy;
import Strategies.DismissAlertStrategy;
import Utils.ModalAndAlertHandler;

/**
 * BaseTest serves as a base class for automated tests.
 * It handles the initialization and cleanup of WebDriver,
 * allowing for a consistent setup and teardown process for each test.

@ExtendWith(CustomTestWatcher.class) */
public class BaseTest {

    protected WebDriver driver; // The WebDriver instance used for testing
    private WebDriverManager webDriverManager; // Manages the WebDriver lifecycle
    private URLManager urlManager; // Manages URL navigation
    private ScreenshotManager screenshotManager; // Manages screenshots
    private TabManager tabManager; // ניהול טאבים
    ModalAndAlertHandler modalAndAlertHandler = new ModalAndAlertHandler(driver);


    /**
     * Sets up the test environment before each test case.
     * Initializes WebDriverManager and URLManager,
     * maximizes the browser window, and navigates to the specified URL.
     */
    @BeforeEach
    public void setUp() {
        // Initialize WebDriverManager with the configuration file path
        webDriverManager = new WebDriverManager("src/main/resources/config.properties");
        webDriverManager.initDriver();

        // Retrieve the WebDriver instance
        driver = webDriverManager.getDriver();

        // Maximize the browser window
        webDriverManager.maximizeWindow();

        // Initialize URLManager with the URL from the configuration file
        urlManager = new URLManager(driver, webDriverManager.getUrl());

        // Navigate to the URL from the configuration file
        urlManager.navigateTo();

        // Initialize ScreenshotManager
        screenshotManager = new ScreenshotManager(driver);

        // Initialize TabManager as a singleton
        tabManager = TabManager.getInstance(driver);

        Allure.step("הגדרת הסביבה לבדיקה");
    }

    /**
     * Cleans up the test environment after each test case.
     * Closes the WebDriver instance to free up resources.
     */
    @AfterEach
    public void tearDown() {
        // Close the WebDriver instance
        webDriverManager.closeDriver();
    }

    /**
     * Example test case to demonstrate the usage of the framework.
     * Prints the current URL to the console.
     */
    @Test
    public void exampleTest() {
        // Example assertion (or log) to display the current URL
        System.out.println("Current URL: " + urlManager.getCurrentUrl());

        // Example for logging
        LoggerManager.info("Testing logger");

        // Take a screenshot if the test fails
        screenshotManager.takeScreenshot();

        // Opening a new tab
        tabManager.openNewTab(urlManager.getCurrentUrl());

        // Switching to the new tab (assuming it's the first one)
        tabManager.switchToTab(1);

        // Optionally, print the title of the new tab
        System.out.println("New Tab Title: " + tabManager.getCurrentTabTitle());

        // Closing the new tab
        tabManager.closeCurrentTab();

        // דוגמה לקבלת אלרט
        ModalStrategy acceptAlertStrategy = new AcceptAlertStrategy(driver);
        modalAndAlertHandler.handleModal(acceptAlertStrategy); // מקבלת את האלט

        // דוגמה לדחיית אלרט
        ModalStrategy dismissAlertStrategy = new DismissAlertStrategy(driver);
        modalAndAlertHandler.handleModal(dismissAlertStrategy); // דוחה את האלט

    }

}
