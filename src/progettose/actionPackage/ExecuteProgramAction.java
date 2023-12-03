package progettose.actionPackage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class ExecuteProgramAction implements Action {

    //List for adding commands
    private List<String> commandList = new ArrayList<>();
    //Defining type for action
    private final String type;

    //Constructr of ExecuteFileAction
    public ExecuteProgramAction(List<String> commandList) {
        this.commandList = commandList;
        this.type = "Execute Program";
    }
    
    //Getter for commandList
    public List<String> getCommandList(){
        return this.commandList;
    }
    
    //Add and remove for commands list
    public void addCommand(String command) {
        this.commandList.add(command);
    }

    public void removeCommand(String command) {
        this.commandList.remove(command);
    }

    @Override
    public String toString() {

        //Get file path from command list
        Path filePath = Paths.get(this.commandList.get(0));

        //Get command list without file path
        List<String> temp = new ArrayList<>();
        for (String element : this.commandList) {
            if (!element.contentEquals(this.commandList.get(0))) {
                temp.add(element);
            }
        }

        return "Execute the program '" + filePath.getFileName().toString() + "' with arguments '" + temp.toString() + "'";
    }

    @Override
    public void execute() {
        Platform.runLater(() -> {
            if (Files.exists(Paths.get(this.commandList.get(0)))) {
                ProcessBuilder executeFile = new ProcessBuilder(this.commandList);
                try {
                    Process processFile = executeFile.start();
                    int exitCode = processFile.waitFor();
                    Alert execAlert = new Alert(Alert.AlertType.INFORMATION);
                    execAlert.setTitle("Execution successful");
                    execAlert.setHeaderText("The program '" + Paths.get(this.commandList.get(0)).getFileName().toString() + "' has been successfully executed\nreturn/exit code: " + exitCode);
                    execAlert.showAndWait();
                } catch (Exception e) {
                    System.out.println("Error during file execution: " + e.getMessage());
                }
            } else {
                Alert fileNotFoundAlert = new Alert(Alert.AlertType.ERROR);
                fileNotFoundAlert.setTitle("Error");
                fileNotFoundAlert.setHeaderText("File not found");
                fileNotFoundAlert.showAndWait();
            }
        });
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getToCSV() {
        //Add ";" at the end of every command for CSV adaptation
        StringBuilder commandCSV = new StringBuilder();
        for (String element : this.commandList) {
            commandCSV.append(element).append(";");
        }

        //Remove last ";" because it's not needed
        if (!this.commandList.isEmpty()) {
            commandCSV.deleteCharAt(commandCSV.length() - 1);
        }

        //Return the full toString
        return this.commandList.size() + ";" + commandCSV.toString();
    }
}
