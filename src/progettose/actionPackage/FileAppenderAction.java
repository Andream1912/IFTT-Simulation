package progettose.actionPackage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class FileAppenderAction implements Action {

    private final Path filePath;
    private final String message;
    private final String type = "Append String to Textfile";

    public FileAppenderAction(Path filePath, String message) {
        this.filePath = filePath;
        this.message = message;
    }

    public Path getFilePath() {
        return filePath;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void execute() {
        Platform.runLater(() -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath.toString(), true))) {
                writer.write(this.message);
                // Mostra una finestra di dialogo di conferma

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Conferma");
                alert.setHeaderText(null);
                alert.setContentText("Messaggio aggiunto correttamente al file.");
                alert.showAndWait();

            } catch (IOException ex) {
                Logger.getLogger(FileAppenderAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override

    public String getType() {
        return this.type;
    }

    @Override
    public String getToCSV() {
        return this.filePath.toString() + ";" + this.message;
    }

    @Override
    public String toString() {
        return filePath.getFileName().toString() + " \nmessage=" + message;
    }
}
