package Utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestWatcher;

public class RetryExtension implements TestWatcher, TestExecutionExceptionHandler {

    private static final int MAX_RETRIES = 3; // קבע כאן את מספר הניסיונות החוזרים
    private int retryCount = 0;

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        if (retryCount < MAX_RETRIES) {
            retryCount++;
            System.out.println("Retrying test " + context.getDisplayName() + " (Attempt " + retryCount + ")");
            context.getRequiredTestMethod().invoke(context.getRequiredTestInstance());
        } else {
            throw throwable; // אם מספר הניסיונות הסתיים, נזרוק את השגיאה
        }
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("Test failed after " + retryCount + " retries.");
        retryCount = 0; // איפוס ספירת הניסיונות
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        retryCount = 0; // איפוס ספירת הניסיונות במידה והבדיקה עברה
    }
}
