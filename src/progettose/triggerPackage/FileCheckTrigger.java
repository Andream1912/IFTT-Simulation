package progettose.triggerPackage;

import java.io.File;


public class FileCheckTrigger implements Trigger {
    private String directoryPath;
    private String fileName;
    private String type;

    public FileCheckTrigger(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;
        this.type="File Existance Verification";
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean evaluate() {
        //crate a file type and verufy if it exists in the specified directory
        File file = new File(directoryPath, fileName);
        return file.exists() && file.isFile();
    }

    @Override
    public String toString() {
        return "File: " + fileName + " in directory " + directoryPath;
    }
    
    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getToCSV() {
        return this.fileName+this.directoryPath+";";
    }
    
}
