package progettose.triggerPackage;

// Interface representing a trigger.
public interface Trigger {

    public void evaluate();
    
    public boolean returnEvaluation();
    
    public void reset();
    
    public String getType();

    public String getToCSV();
}
