package progettose.actionPackage;

public class AddCounterToCounterActionCreator extends ActionCreator{
    
    //Attributes for AddCounterToCounterActionCreator
    private final String counter;
    private final String counterToAdd;
    
    //Constructor for AddCounterToCounterActionCreator
    public AddCounterToCounterActionCreator(String counter, String counterToAdd){
        this.counter = counter;
        this.counterToAdd = counterToAdd;
    }
    
    //Returns a new instance of AddCounterToCounterAction with the specifed values
    @Override
    public Action createAction(){
        return new AddCounterToCounterAction(this.counter, this.counterToAdd);
    }
}
