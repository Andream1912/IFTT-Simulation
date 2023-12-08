package progettose.actionPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class FileAppenderAction implements Action {

    private final Path filePath;
    private final String message;
    private final String type;

    // Constructor
    public FileAppenderAction(Path filePath, String message) {
        this.filePath = filePath;
        this.message = message;
        this.type = "Append String to Textfile";
    }

    // Getter for filePath
    public Path getFilePath() {
        return filePath;
    }

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Execute the action
    @Override
    public void execute() {
        final String[] dialogMessage = {""};
            try {
                // Check if the file exists
                File file = new File(this.filePath.toString());
                if (!file.exists()) {
                    // The file doesn't exist, handle file creation here
                    boolean fileCreated = file.createNewFile();
                    dialogMessage[0] = "File not found!\nNew file created";
                    if (!fileCreated) {
                        // Unable to create the file, handle the error accordingly
                        throw new IOException("Unable to create the file.");
                    }
                } else {
                    // File exists, update dialog message
                    dialogMessage[0] = "Message correctly added to file";
                } 
                // Write message to the file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                    writer.write(this.message);

                    // Show an information dialog
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText(dialogMessage[0]);
                    alert.showAndWait();

                } catch (IOException ex) {
                    // Handle file writing error
                    Logger.getLogger(FileAppenderAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException e) {
                // Handle file creation or other IO error
                e.printStackTrace(); // or handle the exception appropriately for your application
            }
    }

    // Getter for action type
    @Override
    public String getType() {
        return this.type;
    }

    // Convert action details to CSV format
    @Override
    public String getToCSV() {
        return this.filePath.toString() + ";" + this.message;
    }

    // Convert action details to string
    @Override
    public String toString() {
        return filePath.getFileName().toString() + "\nmessage=" + message;
    }
}