package progettose.triggerPackage;

import java.util.ArrayList;
import java.util.List;

public class ExecuteProgramTriggerCreator extends TriggerCreator {

    //Attributes for ExecuteProgramTriggerCreator
    private List<String> commandList = new ArrayList<>();
    private int userValue;

    //Constructor for ExecuteProgramTriggerCreator
    public ExecuteProgramTriggerCreator(List<String> commandList, int userValue) {
        this.commandList = commandList;
        this.userValue = userValue;
    }

    //Returns a new instance of ExecuteProgramTrigger with related attributes
    @Override
    public Trigger createTrigger() {
        return new ExecuteProgramTrigger(this.commandList, this.userValue);
    }
}
