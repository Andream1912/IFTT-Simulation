package progettose.actionPackage;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ShowMessageAction implements Action {

    // The message to be displayed
    private final String message;
    private final String type;

    // Constructor to initialize ShowMessageAction with the specified message.
    public ShowMessageAction(String message) {
        this.message = message;
        this.type = "Show Message";
    }

    // Method for getting message
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getType() {
        return this.type;
    }

    // Executes the action by displaying the configured message in a JavaFX Alert.
    @Override
    public void execute() {
        // Create an Alert with a single "Close" button
            Alert messageBox = new Alert(AlertType.NONE);
            ButtonType confButton = new ButtonType("Ok");
            messageBox.getButtonTypes().setAll(confButton);
            messageBox.setTitle("Message");
            messageBox.setContentText(this.message);

            // Display the Alert and wait for user interaction
            messageBox.showAndWait();
    }

    @Override
    public String toString() {
        return "Message: \n" + message;
    }

    @Override
    public String getToCSV() {
        return this.message;
    }
}
