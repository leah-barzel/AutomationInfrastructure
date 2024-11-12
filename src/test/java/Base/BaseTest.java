package Base;

import Drivers.URLManager;
import Drivers.WebDriverManager;
import Extensions.TestLifecycleManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.Assertions;

@ExtendWith(TestLifecycleManager.class)
public class BaseTest {

    protected WebDriver driver;
    private URLManager urlManager;
    private WebDriverManager webDriverManager;

    @AfterEach
    public void tearDown() {
        // This can be handled by the extension
    }

    @Test
    public void exampleTest() {

        // Initialize URLManager and navigate to the URL
/**
        boolean randomSuccess = Math.random() > 0.2; // 20% chance of failure
        Assertions.assertTrue(randomSuccess, "Random failure example");
*/
    }
}
