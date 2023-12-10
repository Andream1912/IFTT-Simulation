package progettose.triggerPackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

// Represents a trigger that checks the size of a specified file against a given threshold.
public class FileSizeCheckerTrigger implements Trigger {

    // Type of the trigger.
    private final String type;
    // Path to the file whose size will be checked.
    private final Path filePath;
    // Size threshold for the file.
    private long value;
    // Type of dimension for the threshold (e.g., bytes, kilobytes, megabytes, gigabytes).
    private final String typeDimension;
    private boolean evaluation;
    private boolean changed;

    // Constructor to create a FileSizeCheckerTrigger with a file path, value, and type of dimension.
    public FileSizeCheckerTrigger(Path file, long value, String typeDimension) {
        this.type = "File Dimension Verification";
        this.filePath = file;
        this.typeDimension = typeDimension;
        this.value = value;
        this.evaluation = false;
        this.changed = false;
    }

    public void setValue(long value) {
        this.value = value;
    }

    // Getter for obtaining the size threshold value.
    public long getValue() {
        return this.value;
    }

    // Getter for obtaining the file path.
    public Path getFile() {
        return this.filePath;
    }

    // Evaluates whether the size of the specified file meets the specified criteria.
    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void evaluate() {
        boolean newEvaluation = false;
        try {
            long fileSizeInByte = Files.size(this.filePath); //file dimension in byte
            long fileSizeInKiloByte; //file dimension in KiloByte
            long fileSizeInMegabyte; //file dimension in MegaByte
            long fileSizeInGigabyte; //file dimension in Gigabyte
            switch (typeDimension) {
                case "KB":
                    fileSizeInKiloByte = fileSizeInByte / 1024;
                    newEvaluation = (fileSizeInKiloByte >= this.value);
                    break;
                case "MB":
                    fileSizeInKiloByte = fileSizeInByte / 1024;
                    fileSizeInMegabyte = fileSizeInKiloByte / 1024;
                    newEvaluation = (fileSizeInMegabyte >= this.value);
                    break;
                case "GB":
                    fileSizeInKiloByte = fileSizeInByte / 1024;
                    fileSizeInMegabyte = fileSizeInKiloByte / 1024;
                    fileSizeInGigabyte = fileSizeInMegabyte / 1024;
                    newEvaluation = (fileSizeInGigabyte >= this.value);
                    break;
                default:
                    newEvaluation = (fileSizeInByte >= this.value);
            }
            this.changed = this.evaluation != newEvaluation;
            this.evaluation = newEvaluation;
        } catch (IOException ex) {
            Logger.getLogger(FileSizeCheckerTrigger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean returnEvaluation() {
        if (this.changed) {
            if (this.evaluation) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void reset() {
        this.evaluation = false;
        this.changed = false;
    }

    // Returns the CSV representation of the FileSizeCheckerTrigger, including the file path, value, and type of dimension.
    @Override
    public String getToCSV() {
        return filePath.toString() + ";" + Long.toString(this.value) + ";" + typeDimension;
    }

    // Returns a string representation of the FileSizeCheckerTrigger, providing details about the file and threshold.
    @Override
    public String toString() {
        return this.filePath.getFileName().toString() + "\nValue: " + this.value + " " + this.typeDimension;
    }
}
