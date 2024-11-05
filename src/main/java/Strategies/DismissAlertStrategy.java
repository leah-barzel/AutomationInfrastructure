package Strategies;

import lombok.AllArgsConstructor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@AllArgsConstructor
public class DismissAlertStrategy implements ModalStrategy {

    private WebDriver driver;

    @Override
    public void handleModal() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            System.out.println("Alert dismissed: " + alert.getText());
        } catch (Exception e) {
            System.out.println("No alert present.");
        }
    }
}
