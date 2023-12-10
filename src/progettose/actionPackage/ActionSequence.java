package progettose.actionPackage;
import java.util.ArrayList;
import java.util.List;

public class ActionSequence implements Action {

    private List<Action> actions = new ArrayList<>();
    private final String type;

    public ActionSequence(List<Action> actions, String name) {
        this.actions = actions;
        this.type = "Action Sequence - " + name;
    }

    public List<Action> getActionsList() {
        return this.actions;
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }

    // Executes the sequence of actions in the order they were added.
    @Override
    public void execute() {
        for (Action action : actions) {
            action.execute();
        }
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getToCSV() {
        StringBuilder s = new StringBuilder();
        for (Action action : actions) {
            s.append(action.getType()).append(";").append(action.getToCSV()).append(";");
        }
        if(!actions.isEmpty()){
            s.deleteCharAt(s.length() - 1);
        }
        return s.toString();
    }

}
