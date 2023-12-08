package progettose.actionPackage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class FileAppenderAction implements Action {

    private Path filePath;
    private String message;
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

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public void setMessage(String message) {
        this.message = message;
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
        return "Append message: "+message+"\nto: "+filePath.getFileName().toString();
    }
}
