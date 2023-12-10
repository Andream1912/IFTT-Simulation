package progettose.triggerPackage;

public class CheckCounterToCounterTriggerCreator extends TriggerCreator{
    
    //Attributes for checkCounterToCounterTriggerCreator
    private final String counter1;
    private final String counter2;
    private final String checkOperation;
    
    //Constructor for checkCounterToCounterTriggerCreator
    public CheckCounterToCounterTriggerCreator(String counter1, String counter2, String checkOperation){
        this.counter1 = counter1;
        this.counter2 = counter2;
        this.checkOperation = checkOperation;
    }
    
    //Returns a new instance of checkCounterToCounterTrigger with specified values
    @Override
    public Trigger createTrigger(){
        return new CheckCounterToCounterTrigger(this.counter1, this.counter2, this.checkOperation);
    }
}
