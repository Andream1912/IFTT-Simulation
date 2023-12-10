package progettose.triggerPackage;

public interface Trigger {

    public void evaluate();
    
    public boolean returnEvaluation();
    
    public void reset();
    
    public String getType();

    public String getToCSV();
}
