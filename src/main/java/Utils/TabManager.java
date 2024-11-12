package Utils;

import lombok.AllArgsConstructor;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.util.Set;

@AllArgsConstructor
public class TabManager {
    private static TabManager instance;
    private WebDriver driver; // דרייבר של סלניום


    // מתודה לקבלת המופע הייחודי של TabManager
    public static TabManager getInstance(WebDriver driver) {
        if (instance == null) {
            instance = new TabManager(driver);
        }
        return instance;
    }

    // מתודה לפתיחת טאב חדש
    public void openNewTab(String url) {
        ((JavascriptExecutor) driver).executeScript("window.open('" + url + "','_blank');");
    }

    // מתודה למעבר בין טאבים
    public void switchToTab(int tabIndex) {
        Set<String> windowHandles = driver.getWindowHandles();
        String[] handles = windowHandles.toArray(new String[0]);
        if (tabIndex < handles.length) {
            driver.switchTo().window(handles[tabIndex]);
        } else {
            throw new IllegalArgumentException("Tab index out of range: " + tabIndex);
        }
    }

    // מתודה לסגירת טאב
    public void closeCurrentTab() {
        driver.close();
        switchToTab(0); // לעבור לטאב הראשון לאחר סגירה
    }

    // מתודה לקבלת הכותרת של הטאב הנוכחי
    public String getCurrentTabTitle() {
        return driver.getTitle();
    }

    // מתודה למעבר חזרה לטאב הקודם
    public void switchBackToPreviousTab(String previousHandle) {
        driver.switchTo().window(previousHandle);
    }
}
