package progettose.actionPackage;

public interface Action {
    
    
    public void execute();
    
    //it gets the type of the action
    public String getType();
    
    //It's used to store and load from file
    public String getToCSV();
}
