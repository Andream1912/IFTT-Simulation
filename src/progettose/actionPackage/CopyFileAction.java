package progettose.actionPackage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class CopyFileAction implements Action {

    //Path for the file to copy 
    private Path filePath;
    private Path copyPath;
    //Defining type for action
    private final String type;
    
    //Constructor of CopyFileAction
    public CopyFileAction(Path filePath, Path copyPath) {
        this.filePath = filePath;
        this.copyPath = copyPath;
        this.type = "Copy File";
    }

    //Getter and setter for both filePath and copyPath
    public Path getFilePath() {
        return this.filePath;
    }

    public void setFilePath(Path path) {
        this.filePath = path;
    }

    public Path getCopyPath() {
        return this.copyPath;
    }

    public void setCopyPath(Path path) {
        this.copyPath = path;
    }

    //ToString for CopyFileAction
    @Override
    public String toString() {
        return "Copy the file - " + this.filePath.getFileName() + " - to the directory - " + this.copyPath.getFileName();
    }

    //Implementing execute method from action
    @Override
    public void execute() {

        Platform.runLater(() -> {
            //Setting the file name in the destination directory
            this.copyPath = Paths.get(this.copyPath.toString() + "/" + this.filePath.getFileName().toString());
            //Check if file exits
            if (this.filePath.toFile().exists()) {
                //Check if a file with the same name already exists in destination directory
                if (Files.exists(this.copyPath)) {
                    while (Files.exists(this.copyPath)) {
                        //Add (Copy) to file name in case it already exits
                        this.copyPath = this.copyPath.resolveSibling(this.copyPath.getFileName().toString() + "(Copy)");
                        if (!Files.exists(this.copyPath)) {
                            //File copy in destiantion directory after adding (Copy)
                            try {
                                Files.copy(this.filePath, this.copyPath, StandardCopyOption.REPLACE_EXISTING);
                                Alert fileCopiedAlert = new Alert(Alert.AlertType.INFORMATION);
                                fileCopiedAlert.setHeaderText("File '" + this.copyPath.getFileName().toString() + "' copied successfully");
                                this.copyPath = Paths.get("#");
                                fileCopiedAlert.showAndWait();
                            } catch (Exception e) {
                                //If file copy doesn't work exception is thrown
                                System.out.println("Error while copying the file " + e.getMessage());
                            }
                        }
                    }
                } else {
                    //File copy in destiantion directory in normal condition
                    try {
                        Files.copy(this.filePath, this.copyPath, StandardCopyOption.REPLACE_EXISTING);
                        Alert fileCopiedAlert = new Alert(Alert.AlertType.INFORMATION);
                        fileCopiedAlert.setHeaderText("File '" + this.copyPath.getFileName().toString() + "' copied successfully");
                        fileCopiedAlert.showAndWait();
                    } catch (Exception e) {
                        //If file copy doesn't work exception is thrown
                        System.out.println("Error while copying the file " + e.getMessage());
                    }
                }
            } //Show message of file not found on screen
            else {
                Alert fileNotFoundAlert = new Alert(Alert.AlertType.ERROR);
                fileNotFoundAlert.setTitle("Error");
                fileNotFoundAlert.setHeaderText("File not found");
                fileNotFoundAlert.showAndWait();
            }
        });
    }

    @Override
    public String getType() {
        return "Copy File";
    }

    @Override
    public String getToCSV() {
        return this.filePath.toString() + ";" + this.copyPath.toString();
    }
}
