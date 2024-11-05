package Utils;

import lombok.AllArgsConstructor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotManager is responsible for capturing screenshots during test execution.
 * It allows for saving screenshots with filenames based on the current URL and timestamp.
 */
@AllArgsConstructor
public class ScreenshotManager {

    private WebDriver driver; // The WebDriver instance used for taking screenshots

    /**
     * Takes a screenshot of the current browser window and saves it to a designated directory.
     * The screenshot filename is generated based on the current URL and timestamp.
     */
    public void takeScreenshot() {
        // Get the current timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());

        // Get the current URL and use it to create a file name
        String currentUrl = driver.getCurrentUrl();
        String siteName = currentUrl.replaceAll("https?://(www\\.)?", "").split("/")[0]; // Get the site name
        String fileName = siteName + "_" + timestamp + ".png"; // Create the file name

        // Specify the directory to save the screenshot
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // Save the screenshot to the specified directory
            ImageIO.write(ImageIO.read(screenshotFile), "png", new File("screenshots/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
