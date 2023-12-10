package progettose.triggerPackage;

public class CheckValueToCounterTriggerCreator extends TriggerCreator{
    
    //Attributes for checkValueToCounterTriggerCreator
    private final String counter;
    private final String checkOperation;
    private final int checkValue;
    
    //Constructor for checkValueToCounterTriggerCreator
    public CheckValueToCounterTriggerCreator(String counter, String checkOperation,int checkValue){
        this.counter = counter;
        this.checkOperation = checkOperation;
        this.checkValue = checkValue;
    }
    
    //Returns a new instance of trigger with the specified values
    @Override 
    public Trigger createTrigger(){
        return new CheckValueToCounterTrigger(this.counter, this.checkOperation,this.checkValue);
    }
}
