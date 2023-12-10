package progettose.triggerPackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileSizeCheckerTrigger implements Trigger {

    private final Path filePath;
    private final long value;
    private final String typeDimension;
    private final String type;
    private boolean evaluation;
    private boolean changed;

    public FileSizeCheckerTrigger(Path file, long value, String typeDimension) {
        this.type = "File Dimension Verification";
        this.filePath = file;
        this.typeDimension = typeDimension;
        this.value = value;
        this.evaluation = false;
        this.changed = false;
    }

    public long getValue() {
        return this.value;
    }

    public Path getFile() {
        return this.filePath;
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
    
    /*@Override
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
    }*/
    
    @Override
    public void evaluate(){
        boolean newEvaluation = false;
        try {
            long fileSizeInByte = Files.size(this.filePath); //file dimension in byte
            long fileSizeInKiloByte = fileSizeInByte / 1024; //file dimension in KiloByte
            long fileSizeInMegabyte = fileSizeInKiloByte / 1024; //file dimension in MegaByte
            long fileSizeInGigabyte = fileSizeInMegabyte / 1024; //file dimension in Gigabyte
            switch (typeDimension) {
                case "KB":
                    newEvaluation = (fileSizeInKiloByte >= this.value);
                    break;
                case "MB":
                    newEvaluation = (fileSizeInMegabyte >= this.value);
                    break;
                case "GB":
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
