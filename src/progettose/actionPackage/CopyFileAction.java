package progettose.actionPackage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class CopyFileAction implements Action{
    
    //Path of the file to copy 
    private Path filePath;
    //Path of the direcotry where to copy the file
    private Path copyPath;
    
    //Constructor of COpyFileAction
    public CopyFileAction(Path filePath, Path copyPath){
        this.filePath = filePath;
        this.copyPath = copyPath;
    }
    
    //Getter and setter for both filePath and copyPath
    public Path getFilePath(){
        return this.filePath;
    }
    
    public void setFilePath(Path path){
        this.filePath = path;
    }
    
    public Path getCopyPath(){
        return this.copyPath;
    }
    
    public void setCopyPath(Path path){
        this.copyPath = path;
    }
    
    @Override 
    public String toString(){
        return "Copy the file - " + this.filePath.getFileName() + " - to the directory - " + this.copyPath.getFileName();
    }
    
    @Override
    public void execute(){
            
        Platform.runLater(() -> {
            //Check if file exits
            if(this.filePath.toFile().exists()){
                //Check if a file with the same name already exists in destination directory
                if(Files.exists(Paths.get(this.copyPath.toString()+ "/" +this.filePath.getFileName().toString()))){

                    //Alert to inform the user a file with same name already exists
                    Alert messageBox = new Alert(Alert.AlertType.NONE);

                    //Defining buttons of alert
                    ButtonType confButton = new ButtonType("Yes");
                    ButtonType cancelButton = new ButtonType("No");
                    messageBox.getButtonTypes().setAll(confButton, cancelButton);
                    messageBox.setTitle("Warning");
                    messageBox.setContentText("A file with the same name already exists, do you want to overwrite it?");
                    
                    // Display the Alert and wait for user to decide if overwriting the file or not
                    messageBox.showAndWait().ifPresent(buttonType -> {
                        if(buttonType == confButton){
                            try{
                                //Copy the file and notify user that copy is successful
                                Files.copy(this.filePath, Paths.get(this.copyPath.toString()+"/"+this.filePath.getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
                                Alert fileCopiedAlert = new Alert(Alert.AlertType.INFORMATION);
                                fileCopiedAlert.setHeaderText("File "+ this.filePath.getFileName().toString() +  " copied successfully");
                                fileCopiedAlert.showAndWait();
                            }
                            catch (Exception e){
                                //If file copy doesn't work exception is thrown
                                System.out.println("Error while overwriting the file " +e.getMessage());
                            }
                        }
                    });
                }           
                else{
                    //File copy if there is no existing file
                    try{
                    Files.copy(this.filePath, Paths.get(this.copyPath.toString()+"/"+this.filePath.getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
                    Alert fileCopiedAlert = new Alert(Alert.AlertType.INFORMATION);
                    fileCopiedAlert.setHeaderText("File "+ this.filePath.getFileName().toString() +  " copied successfully");
                    fileCopiedAlert.showAndWait();
                    }
                    catch (Exception e){
                    //If file copy doesn't work exception is thrown
                    System.out.println("Error while copying the file " +e.getMessage());
                    }
                }
            }
            //Show message of file not found on screen
            else{
                Alert fileNotFoundAlert = new Alert(Alert.AlertType.ERROR);
                fileNotFoundAlert.setTitle("Error");
                fileNotFoundAlert.setHeaderText("File not found");
                fileNotFoundAlert.showAndWait();
            }
        });
    }
}
