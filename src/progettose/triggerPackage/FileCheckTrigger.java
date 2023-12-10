package progettose.triggerPackage;

import java.io.File;

public class FileCheckTrigger implements Trigger {

    private final String directoryPath;
    private final String fileName;
    private final String type;
    private boolean evaluation;
    private boolean changed;

    public FileCheckTrigger(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;
        this.type = "File Existance Verification";
        this.evaluation = false;
        this.changed = false;
    }

    public String getDirectoryPath() {
        return this.directoryPath;
    }

    public String getFileName() {
        return this.fileName;
    }

    @Override
    public String toString() {
        return "File: " + this.fileName + " in directory " + this.directoryPath;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getToCSV() {
        return this.directoryPath + ";" + this.fileName;
    }

    /*@Override
    public boolean evaluate() {
        //crate a file type and verufy if it exists in the specified directory
        File file = new File(this.directoryPath, this.fileName);
        return file.exists() && file.isFile();
    }*/
    
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
