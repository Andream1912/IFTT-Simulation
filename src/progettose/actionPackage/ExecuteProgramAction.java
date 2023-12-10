package progettose.actionPackage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
    public List<String> getCommandList() {
        return this.commandList;
    }

    //Add and remove for commands list
    public void addCommand(String command) {
        this.commandList.add(command);
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
// Executes a command specified in the commandList, which represents a list of arguments for a process.
    public void execute() {
        // Checks if the specified file exists before attempting execution.
        if (Files.exists(Paths.get(this.commandList.get(0)))) {
            // Configures and starts a process using the specified commandList.
            ProcessBuilder executeFile = new ProcessBuilder(this.commandList);
            try {
                // Starts the process and waits for its completion.
                Process processFile = executeFile.start();
                int exitCode = processFile.waitFor();

                // Displays an information alert indicating successful execution, including the return/exit code.
                Alert execAlert = new Alert(Alert.AlertType.INFORMATION);
                execAlert.setTitle("Execution successful");
                execAlert.setHeaderText("The program '" + Paths.get(this.commandList.get(0)).getFileName().toString()
                        + "' has been successfully executed\nreturn/exit code: " + exitCode);
                execAlert.showAndWait();
            } catch (Exception e) {
                // Handles exceptions that may occur during file execution, displaying an error message.
                System.out.println("Error during file execution: " + e.getMessage());
            }
        } else {
            // Displays an error alert if the specified file is not found.
            Alert fileNotFoundAlert = new Alert(Alert.AlertType.ERROR);
            fileNotFoundAlert.setTitle("Error");
            fileNotFoundAlert.setHeaderText("File '"
                    + Paths.get(this.commandList.get(0)).getFileName().toString() + "' not found");
            fileNotFoundAlert.showAndWait();
        }
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
