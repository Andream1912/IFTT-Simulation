package progettose.triggerPackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExecuteProgramTrigger implements Trigger {

    //Attributes for ExecuteProgramTrigger
    private List<String> commandList = new ArrayList<>();
    private int userValue;
    private final String type;

    //Constructor for ExecuteProgramTrigger
    public ExecuteProgramTrigger(List<String> commandList, int userValue) {
        this.commandList = commandList;
        this.userValue = userValue;
        this.type = "Program Exit Status Verification";
    }

    //Getter and setter for userValue
    public int getUserValue() {
        return this.userValue;
    }

    public void setUserValue(int n) {
        this.userValue = n;
    }

    //Getter for commandList
    public List<String> getCommandList() {
        return this.commandList;
    }

    //Add method for commandList
    public void addCommand(String command) {
        this.commandList.add(command);
    }

    //Remove method for commandList
    public void removeCommand(String command) {
        this.commandList.remove(command);
    }

    //toString for ExecuteProgramTrigger
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

        return "Execute the program '" + filePath.getFileName().toString() + "' with arguments '"
                + temp.toString() + "'\nAnd check if exit value is equal to '" + this.userValue + "'";
    }

    @Override
    public boolean evaluate() {
        if (Files.exists(Paths.get(this.commandList.get(0)))) {
            ProcessBuilder executeFile = new ProcessBuilder(this.commandList);
            try {
                Process processFile = executeFile.start();
                int exitCode = processFile.waitFor();
                return exitCode == this.userValue;
            } catch (IOException | InterruptedException e) {
                System.out.println("Error during file execution: " + e.getMessage());
            }
        }
        return false;
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
        return this.commandList.size() + ";" + commandCSV.toString() + ";" + this.userValue;
    }
}
