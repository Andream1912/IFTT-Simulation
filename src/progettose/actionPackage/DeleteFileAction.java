package progettose.actionPackage;

import java.nio.file.Files;
import java.nio.file.Path;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class DeleteFileAction implements Action {

    //Path for DeleteFileAction
    private final Path filePath;
    //Defining type for action
    private final String type;

    //Costructor of DeleteFileAction
    public DeleteFileAction(Path filePath) {
        this.filePath = filePath;
        this.type = "Delete File";
    }

    //Getter and setter for filePath 
    public Path getFilePath() {
        return this.filePath;
    }

    //ToString for DeleteFileAction
    @Override
    public String toString() {
        return "Delete the file  '" + this.filePath.getFileName() + "'";
    }

    //Implementing execute method from action
    @Override
    public void execute() {
            //Check if file exists
            if (Files.exists(this.filePath)) {
                try {
                    //Deletes the file and notifies user
                    Files.delete(this.filePath);
                    Alert fileDeleteAlert = new Alert(Alert.AlertType.INFORMATION);
                    fileDeleteAlert.setHeaderText("File '" + this.filePath.getFileName() + "' deleted successfully");
                    fileDeleteAlert.showAndWait();
                } catch (Exception e) {
                    //If file delete doesn't work exception is thrown
                    System.out.println("Error while deleting the file " + e.getMessage());
                }
                //Notifies user that file doesn't exists
            } else {
                Alert fileNotFoundAlert = new Alert(Alert.AlertType.ERROR);
                fileNotFoundAlert.setTitle("Error");
                fileNotFoundAlert.setHeaderText("File '" + this.filePath.getFileName().toString() + "' not found");
                fileNotFoundAlert.showAndWait();
            }
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getToCSV() {
        return this.filePath.toString();
    }
}