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
    private final long value;

    // Type of dimension for the threshold (e.g., bytes, kilobytes, megabytes, gigabytes).
    private final String typeDimension;

    // Constructor to create a FileSizeCheckerTrigger with a file path, value, and type of dimension.
    public FileSizeCheckerTrigger(Path file, long value, String typeDimension) {
        this.type = "File Dimension Verification";
        this.filePath = file;
        this.typeDimension = typeDimension;
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
    public boolean evaluate() {
        try {
            // Obtains the size of the file in bytes.
            long fileSizeInByte = Files.size(this.filePath);

            // Converts file size to kilobytes, megabytes, and gigabytes for easier comparison.
            long fileSizeInKiloByte = fileSizeInByte / 1024;
            long fileSizeInMegabyte = fileSizeInKiloByte / 1024;
            long fileSizeInGigabyte = fileSizeInMegabyte / 1024;

            // Compares the file size based on the specified typeDimension.
            switch (typeDimension) {
                case "KB":
                    // Returns true if the file size in kilobytes is greater than or equal to the specified value.
                    return (fileSizeInKiloByte >= this.value);
                case "MB":
                    // Returns true if the file size in megabytes is greater than or equal to the specified value.
                    return (fileSizeInMegabyte >= this.value);
                case "GB":
                    // Returns true if the file size in gigabytes is greater than or equal to the specified value.
                    return (fileSizeInGigabyte >= this.value);
                default:
                    // Returns true if the file size in bytes is greater than or equal to the specified value.
                    return (fileSizeInByte >= this.value);
            }
        } catch (IOException ex) {
            // Logs an error if an IOException occurs while obtaining the file size.
            Logger.getLogger(FileSizeCheckerTrigger.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Returns false in case of an exception or if the evaluation cannot be performed.
        return false;
    }

    // Getter for obtaining the type of the trigger.
    @Override
    public String getType() {
        return this.type;
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
