package progettose.actionPackage;

public class AddValueToCounterActionCreator extends ActionCreator{
    
    //Attributes for AddValueToCounterCreator
    private final String counter;
    private final int valueToAdd;
    
    //Constructor for AddValueToCounterCreator
    public AddValueToCounterActionCreator(String counter, int valueToAdd){
        this.counter = counter;
        this.valueToAdd = valueToAdd;
    }
    
    //Returns a new instance of AddValueToCounterAction with the specified values
    @Override 
    public Action createAction(){
        return new AddValueToCounterAction(this.counter, this.valueToAdd);
    }
}
