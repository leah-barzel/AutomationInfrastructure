package Reports;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class AllureReportManager {

    @Step("Start test report")
    public void startTest(String testName) {
        Allure.step("Starting test: " + testName);
    }

    @Step("Log message")
    public void logMessage(String message) {
        Allure.addAttachment("Log Message", new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8)));
    }

    @Step("End test report")
    public void endTest(String testName) {
        Allure.step("Ending test: " + testName);
    }
}
