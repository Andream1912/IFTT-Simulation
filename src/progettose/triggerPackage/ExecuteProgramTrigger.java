package progettose.triggerPackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

public class ExecuteProgramTrigger implements Trigger {

    //Attributes for ExecuteProgramTrigger
    private List<String> commandList = new ArrayList<>();
    private int userValue;
    private final String type;
    private boolean evaluation;
    private boolean changed;
    private boolean isThreadRunning;
    private boolean alreadyVerified;

    //Constructor for ExecuteProgramTrigger
    public ExecuteProgramTrigger(List<String> commandList, int userValue) {
        this.commandList = commandList;
        this.userValue = userValue;
        this.type = "Program Exit Status Verification";
        this.evaluation = false;
        this.changed = false;
        this.isThreadRunning = false;
        this.alreadyVerified = false;
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

    /*@Override
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
    }*/
    @Override
    public void evaluate() {
        synchronized (this) {
            if (!isThreadRunning) {
                Thread myThread = new Thread(() -> {
                    if (Files.exists(Paths.get(this.commandList.get(0)))) {
                        ProcessBuilder executeFile = new ProcessBuilder(this.commandList);
                        try {
                            boolean newEvaluation = false;
                            Process processFile = executeFile.start();
                            int exitCode = processFile.waitFor();
                            newEvaluation = exitCode == this.userValue;
                            this.changed = this.evaluation != newEvaluation;
                            this.evaluation = newEvaluation;
                            this.alreadyVerified = !this.changed;
                        } catch (IOException | InterruptedException e) {
                            System.out.println("Error during file execution: " + e.getMessage());
                        }
                        isThreadRunning = false;
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Execute Program Trigger ");
                        alert.setHeaderText("File not found!");
                        alert.showAndWait();
                    }
                });
                isThreadRunning = true;
                myThread.setDaemon(true);
                myThread.start();
            }
        }
    }

    @Override
    public boolean returnEvaluation() {
        if (this.changed && !this.alreadyVerified) {
            if (this.evaluation) {
                this.alreadyVerified = true;
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void reset() {
        this.evaluation = false;
        this.changed = false;
        this.alreadyVerified = false;
    }
}
