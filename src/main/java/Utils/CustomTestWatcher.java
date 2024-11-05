package Utils;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public class CustomTestWatcher implements TestWatcher {

    private final WebDriver driver; // מאגר ה-WebDriver


    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("Test succeeded: " + context.getDisplayName());
        // אפשר להוסיף כאן לוגיקה נוספת במקרה של הצלחה אם צריך
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("Test failed: " + context.getDisplayName());


    }

}
