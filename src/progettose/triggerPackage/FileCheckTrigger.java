package progettose.triggerPackage;

import java.io.File;

// Represents a trigger that verifies the existence of a specified file in a given directory.
public class FileCheckTrigger implements Trigger {

    // Directory path where the file is expected to be present.
    private final String directoryPath;
    // Name of the file to be checked for existence.
    private final String fileName;
    // The type of the trigger.
    private final String type;
    private boolean evaluation;
    private boolean changed;

    // Constructor to create a FileCheckTrigger with a directory path and file name.
    public FileCheckTrigger(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;
        this.type = "File Existance Verification";
        this.evaluation = false;
        this.changed = false;
    }

    // Getter for obtaining the directory path.
    public String getDirectoryPath() {
        return this.directoryPath;
    }

    // Getter for obtaining the file name.
    public String getFileName() {
        return this.fileName;
    }

    // Returns a string representation of the FileCheckTrigger, providing details about the checked file and directory.
    @Override
    public String toString() {
        return "File: " + this.fileName + " in directory " + this.directoryPath;
    }

    // Getter for obtaining the type of the trigger.
    @Override
    public String getType() {
        return this.type;
    }

    // Returns the CSV representation of the FileCheckTrigger, including the directory path and file name.
    @Override
    public String getToCSV() {
        return this.directoryPath + ";" + this.fileName;
    }

    // Evaluates the FileCheckTrigger by checking if the specified file exists in the specified directory.
    @Override
    public void evaluate(){
        File file = new File(this.directoryPath, this.fileName);
        boolean newEvaluation = file.exists() && file.isFile();
        this.changed = this.evaluation != newEvaluation;
        this.evaluation = newEvaluation;
        
    }
    
    @Override 
    public boolean returnEvaluation(){
        if(this.changed)
            if(this.evaluation)
                return true;
        return false;
    }
    
    @Override
    public void reset() {
        this.evaluation = false;
        this.changed = false;
    }
}
