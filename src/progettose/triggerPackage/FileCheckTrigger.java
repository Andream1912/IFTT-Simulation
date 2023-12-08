package progettose.triggerPackage;

import java.io.File;

public class FileCheckTrigger implements Trigger {

    private final String directoryPath;
    private final String fileName;
    private final String type;

    public FileCheckTrigger(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;
        this.type = "File Existance Verification";
    }

    public String getDirectoryPath() {
        return this.directoryPath;
    }

    public String getFileName() {
        return this.fileName;
    }

    @Override
    public boolean evaluate() {
        File file = new File(this.directoryPath, this.fileName);
        return file.exists() && file.isFile();
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

}
