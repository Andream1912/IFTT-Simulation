package progettose.actionPackage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class MoveFileAction implements Action {

    //Paths for the file to be moved
    private final Path filePath;
    private Path movePath;
    //Defining type for action
    private final String type;

    //Constructor for MoveFileAction
    public MoveFileAction(Path filePath, Path movePath) {
        this.filePath = filePath;
        this.movePath = movePath;
        this.type = "Move File";
    }

    //Getter and setter for both filePath and movePath
    public Path getFilePath() {
        return this.filePath;
    }

    public Path getMovePath() {
        return this.movePath;
    }

    //ToString for MoveFileAction
    @Override
    public String toString() {
        return "Move the file  '" + filePath.getFileName() + "'  to the directory  '" + this.movePath.getFileName() + "'";
    }

    //Implementig execute method from action
    @Override
    public void execute() {

       Platform.runLater(() -> {
            //Setting the fileName in the destination path
            this.movePath = Paths.get(this.movePath.toString() + "/" + this.filePath.getFileName().toString());
            //Check if file exits
            if (this.filePath.toFile().exists()) {
                //Check if a file with the same name already exists in destination directory
                if (Files.exists(this.movePath)) {
                    while (Files.exists(this.movePath)) {
                        //Alert to inform the user a file with same name already exists
                        Alert messageBox = new Alert(Alert.AlertType.NONE);
                        //Defining buttons of alert
                        ButtonType overwriteButton = new ButtonType("Overwrite");
                        ButtonType renameButton = new ButtonType("Rename");
                        messageBox.getButtonTypes().setAll(overwriteButton, renameButton);
                        messageBox.setTitle("Warning");
                        messageBox.setContentText("A file with the same name as '" + this.movePath.getFileName().toString() + "' already exists");

                        // Display the Alert and wait for user to decide if overwriting the file or renaming it
                        messageBox.showAndWait().ifPresent(buttonType -> {
                            if (buttonType == overwriteButton) {
                                //Move the file overwriting the already existing one
                                try {
                                    Files.move(this.filePath, this.movePath, StandardCopyOption.REPLACE_EXISTING);
                                    Alert fileMovedAlert = new Alert(Alert.AlertType.INFORMATION);
                                    fileMovedAlert.setHeaderText("File " + this.movePath.getFileName().toString() + " moved successfully");
                                    this.movePath = Paths.get("#");
                                    fileMovedAlert.showAndWait();
                                } catch (Exception e) {
                                    //If file move doesn't work exception is thrown
                                    System.out.println("Error while moving the file " + e.getMessage());
                                }
                            }
                            //Alert changes to let user input new name for the file
                            if (buttonType == renameButton) {
                                Alert renameAlert = new Alert(Alert.AlertType.NONE);
                                renameAlert.setTitle("Rename the file");
                                ButtonType okButton = new ButtonType("Ok");
                                ButtonType cancelButton = new ButtonType("Cancel");
                                TextField renameTextField = new TextField();
                                renameTextField.setPromptText("Write file name here...");                               
                                renameAlert.getButtonTypes().setAll(okButton, cancelButton);
                                renameAlert.getDialogPane().setContent(renameTextField);
                                renameAlert.getDialogPane().lookupButton(okButton).disableProperty().bind(renameTextField.textProperty().isEmpty());
                                renameAlert.getDialogPane().setPrefWidth(300);                               
                                renameAlert.showAndWait().ifPresent(buttonType2 -> {
                                    if (buttonType2 == okButton) {
                                        this.movePath = this.movePath.resolveSibling(renameTextField.getText());
                                        if (!Files.exists(this.movePath)) {
                                            //Move the file after the rename
                                            try {
                                                Files.move(this.filePath, this.movePath, StandardCopyOption.REPLACE_EXISTING);
                                                Alert fileMovedAlert = new Alert(Alert.AlertType.INFORMATION);
                                                fileMovedAlert.setHeaderText("File " + this.movePath.getFileName().toString() + " moved successfully");
                                                this.movePath = Paths.get("#");
                                                fileMovedAlert.showAndWait();
                                            } catch (Exception e) {
                                                //If file move doesn't work exception is thrown
                                                System.out.println("Error while moving the file " + e.getMessage());
                                            }
                                        }
                                    }
                                });
                            }
                        });
                    }
                }
                else{
                    //Move the file in normal condition
                    try {
                        Files.move(this.filePath, this.movePath, StandardCopyOption.REPLACE_EXISTING);
                        Alert fileCopiedAlert = new Alert(Alert.AlertType.INFORMATION);
                        fileCopiedAlert.setHeaderText("File " + this.movePath.getFileName().toString() + " moved successfully");
                        this.movePath = Paths.get("#");
                        fileCopiedAlert.showAndWait();
                    } catch (Exception e) {
                    //If file move doesn't work exception is thrown
                    System.out.println("Error while moving the file " + e.getMessage());
                }
                }
            }    
            //Show message of file not found on screen
            else {
                Alert fileNotFoundAlert = new Alert(Alert.AlertType.ERROR);
                fileNotFoundAlert.setTitle("Error");
                fileNotFoundAlert.setHeaderText("File '" + this.filePath.getFileName().toString() + "' not found");
                fileNotFoundAlert.showAndWait();
            }
       });
    }

    //Implementig getType and getToCSV for MoveFileAction
    @Override

    public String getType() {
        return this.type;
    }

    @Override
    public String getToCSV() {
        return this.filePath.toString() + ";" + this.movePath.toString();
    }
}