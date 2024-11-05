package Utils;

import Strategies.ModalStrategy;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public class ModalAndAlertHandler {

    private WebDriver driver;

    /**
     * Handles the modal or alert using the provided strategy.
     *
     * @param modalStrategy The strategy to handle the modal or alert.
     */
    public void handleModal(ModalStrategy modalStrategy) {
        modalStrategy.handleModal();
    }
}
