package progettose.triggerPackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

// Represents a trigger that executes a program and checks the exit value.
public class ExecuteProgramTrigger implements Trigger {

    // Command list to execute the program.
    private List<String> commandList = new ArrayList<>();

    // User-specified exit value to check.
    private int userValue;

    // The type of the trigger.
    private final String type;
    private boolean evaluation;
    private boolean changed;
    private boolean isThreadRunning;
    private boolean alreadyVerified;

    // Constructor to create an ExecuteProgramTrigger with a command list and user-specified exit value.
    public ExecuteProgramTrigger(List<String> commandList, int userValue) {
        this.commandList = commandList;
        this.userValue = userValue;
        this.type = "Program Exit Status Verification";
        this.evaluation = false;
        this.changed = false;
        this.isThreadRunning = false;
        this.alreadyVerified = false;
    }

    // Getter and setter for the user-specified exit value.
    public int getUserValue() {
        return this.userValue;
    }

    public void setUserValue(int userValue) {
        this.userValue = userValue;
    }

    // Getter for the command list.
    public List<String> getCommandList() {
        return this.commandList;
    }

    // Add method to append a command to the command list.
    public void addCommand(String command) {
        this.commandList.add(command);
    }

    // Remove method to remove a command from the command list.
    public void removeCommand(String command) {
        this.commandList.remove(command);
    }

    // Returns a string representation of the ExecuteProgramTrigger, providing details about the executed program and exit value check.
    @Override
    public String toString() {
        Path filePath = Paths.get(this.commandList.get(0));
        List<String> temp = new ArrayList<>(this.commandList.subList(1, this.commandList.size()));
        return "Execute the program '" + filePath.getFileName().toString() + "' with arguments '"
                + temp.toString() + "'\nAnd check if the exit value is equal to '" + this.userValue + "'";
    }

    // Getter for obtaining the type of the trigger.
    @Override
    public String getType() {
        return this.type;
    }

    // Returns the CSV representation of the ExecuteProgramTrigger, including the command list and user-specified exit value.
    @Override
    public String getToCSV() {
        // Join the command list using ";" and add the user-specified exit value.
        String commandCSV = String.join(";", this.commandList.subList(1, this.commandList.size()));
        return this.commandList.size() - 1 + ";" + commandCSV + ";" + this.userValue;
    }

    // Evaluates the ExecuteProgramTrigger by executing the program and checking the exit value.
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
