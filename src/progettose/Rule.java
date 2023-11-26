package progettose;

import progettose.actionPackage.Action;
import progettose.triggerPackage.Trigger;

public class Rule {

    private String name;
    private Trigger trigger;
    private Action action;

    public Rule(String n, Action a, Trigger t) {
        this.name = n;
        this.action = a;
        this.trigger = t;
    }

    public Action getAction() {
        return this.action;
    }

    public Trigger getTrigger() {
        return this.trigger;
    }

    public String getName() {
        return this.name;
    }

    public void setAction(Action a) {
        this.action = a;
    }

    public void setTrigger(Trigger t) {
        this.trigger = t;
    }

    public void setName(String n) {
        this.name = n;
    }

}
