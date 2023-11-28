package progettose.actionPackage;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ShowMessageAction implements Action {

    // The message to be displayed
    private String message;

    // Constructor to initialize ShowMessageAction with the specified message.
    public ShowMessageAction(String message) {
        this.message = message;
    }

    // Method for getting message
    public String getMessage() {
        return this.message;
    }

    // Method for setting message
    public void setMessage(String temp) {
        this.message = temp;
    }

    // Executes the action by displaying the configured message in a JavaFX Alert.
    @Override
    public void execute() {
        // Create an Alert with a single "Close" button
        Platform.runLater(() -> {
            Alert messageBox = new Alert(AlertType.NONE);
            ButtonType confButton = new ButtonType("Ok");
            messageBox.getButtonTypes().setAll(confButton);
            messageBox.setTitle("Message");
            messageBox.setContentText(this.message);

            // Display the Alert and wait for user interaction
            messageBox.showAndWait();
        });
    }

    @Override
    public String toString() {
        return "A message will be shown, it says: " + message;
    }
}
