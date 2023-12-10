package progettose.actionPackage;

public class SetCounterActionCreator extends ActionCreator{
    
    //Attributes for SetCounterActionCreator
    private final String counter;
    private final int counterValue;
    
    //Constructor for SetCounterActionCreator
    public SetCounterActionCreator(String counter, int counterValue){
        this.counter = counter;
        this.counterValue = counterValue;
    }
    
    //Creates and returns a new instace of SetCounterAction with the specified values
    @Override
    public Action createAction(){
        return new SetCounterAction(this.counter, this.counterValue);
    }
}
