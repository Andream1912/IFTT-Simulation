package progettose.triggerPackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileSizeCheckerTrigger implements Trigger {

    private final String type;
    private final Path filePath;
    private final long value;
    private final String typeDimension;

    public FileSizeCheckerTrigger(Path file, long value, String typeDimension) {
        this.type = "File Dimension Verification";
        this.filePath = file;
        this.typeDimension = typeDimension;
        this.value = value;
    }

    public long getValue() {
        return this.value;
    }

    public Path getFile() {
        return this.filePath;
    }

    @Override
    public boolean evaluate() {
        try {
            long fileSizeInByte = Files.size(this.filePath); //file dimension in byte
            long fileSizeInKiloByte = fileSizeInByte / 1024; //file dimension in KiloByte
            long fileSizeInMegabyte = fileSizeInKiloByte / 1024; //file dimension in MegaByte
            long fileSizeInGigabyte = fileSizeInMegabyte / 1024; //file dimension in Gigabyte
            switch (typeDimension) {
                case "KB":
                    return (fileSizeInKiloByte >= this.value);
                case "MB":
                    return (fileSizeInMegabyte >= this.value);
                case "GB":
                    return (fileSizeInGigabyte >= this.value);
                default:
                    return (fileSizeInByte >= this.value);

            }
        } catch (IOException ex) {
            Logger.getLogger(FileSizeCheckerTrigger.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getToCSV() {
        return filePath.toString() + ";" + Long.toString(this.value) + ";" + typeDimension;
    }

    @Override
    public String toString() {
        return this.filePath.getFileName().toString() + "\nValore:" + this.value + this.typeDimension;
    }
}
