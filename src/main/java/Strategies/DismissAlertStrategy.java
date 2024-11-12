package Strategies;

import Logging.LoggerManager;
import lombok.AllArgsConstructor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@AllArgsConstructor
public class DismissAlertStrategy implements ModalStrategy {

    private WebDriver driver;
    private LoggerManager loggerManager; // Use the injected logger


    @Override
    public void handleModal() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            loggerManager.info("Alert dismissed: " + alert.getText());
        } catch (Exception e) {
            loggerManager.debug("No alert present.");
        }
    }
}
