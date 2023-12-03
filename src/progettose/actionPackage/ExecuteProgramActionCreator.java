package progettose.actionPackage;

import java.util.ArrayList;
import java.util.List;

public class ExecuteProgramActionCreator extends ActionCreator{
    
    //List for adding commands and file path
    private List<String> commandList = new ArrayList<>();
    
    //Constructor for ExecuteFileActionCreator
    public ExecuteProgramActionCreator(List<String> commandList){
        this.commandList = commandList;
    }
    
    //Returns a new instance of ExecuteFileAction with the specified path and commands for the file to execute
    @Override
    public Action createAction(){
        return new ExecuteProgramAction( this.commandList);
    }
}
