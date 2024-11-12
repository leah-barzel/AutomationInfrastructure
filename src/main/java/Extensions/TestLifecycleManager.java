package Extensions;

import Drivers.URLManager;
import Drivers.WebDriverManager;
import Logging.LoggerManager;
import Utils.ScreenshotManager;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;

import java.util.Optional;


public class TestLifecycleManager implements BeforeEachCallback, AfterEachCallback, BeforeAllCallback, AfterAllCallback, TestWatcher {

    private LoggerManager loggerManager;
    private WebDriverManager webDriverManager;
    private URLManager urlManager;
    private WebDriver driver;
    private ScreenshotManager screenshotManager;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        loggerManager = new LoggerManager();
        loggerManager.info("Initializing WebDriverManager...");

        webDriverManager = new WebDriverManager("src/main/resources/config.properties");
        webDriverManager.initDriver();
        driver = webDriverManager.getDriver();

        // Now we inject the driver into URLManager
        urlManager = new URLManager(driver, "src/main/resources/config.properties");

        loggerManager.info("WebDriver initialized.");
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        if (driver == null) {
            loggerManager.error("WebDriver is not initialized.");
            throw new IllegalStateException("WebDriver not initialized.");
        }

        // Maximize browser window
        webDriverManager.maximizeWindow();
        loggerManager.info("Browser window maximized.");

        urlManager.navigateTo();

        // Initialize ScreenshotManager after WebDriver is initialized
        screenshotManager = new ScreenshotManager(driver);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            loggerManager.error("Test failed. Taking screenshot.");
            screenshotManager.takeScreenshot();
        }
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        loggerManager.info("Closing WebDriver...");
        webDriverManager.closeDriver();
        loggerManager.info("WebDriver closed.");
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        loggerManager.info("Test disabled: " + context.getDisplayName() + " | Reason: " + reason.orElse("No reason provided"));
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        loggerManager.info("Test passed: " + context.getDisplayName());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        loggerManager.warn("Test aborted: " + context.getDisplayName() + " | Reason: " + cause.getMessage());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        loggerManager.error("Test failed: " + context.getDisplayName() + " | Error: " + cause.getMessage());
        screenshotManager.takeScreenshot();
    }
}
