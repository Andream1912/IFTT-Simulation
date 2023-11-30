package progettose.triggerPackage;


public class FileCheckTriggerCreator extends TriggerCreator{
    private String directoryPath;
    private String fileName;
    
     public FileCheckTriggerCreator (String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;
    }
    @Override
    public Trigger createTrigger() {
        return new FileCheckTrigger(this.directoryPath,this.fileName);
    }
    
    
}
