package progettose.actionPackage;
import java.util.ArrayList;
import java.util.List;

public class ActionSequence implements Action{
    private List<Action> actions;
    private final String type;
    
    public ActionSequence(){
        this.actions=new ArrayList<>();
        this.type="Action Sequence";
    }
    
    public List<Action> getActionsList(){
        return this.actions;
    }
    
    public void addAction(Action action){
        this.actions.add(action);
    }

    @Override
    public void execute() {
        for(Action action: actions){
            action.execute();
        }
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getToCSV() {
        String s = "";
        for(Action action: actions){
            s=s.concat(action.getType()+":"+action.getToCSV()+";");
        }
        return s;
    }
    
}
