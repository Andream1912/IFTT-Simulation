package progettose.rulePackage;

import java.util.Observable;
import progettose.actionPackage.Action;
import progettose.triggerPackage.Trigger;

public class Rule extends Observable{

    private String name;
    private Trigger trigger;
    private Action action;
    private RuleState state;
    private String ruleTypeDescription;

    public Rule(String n, Action a, Trigger t) {
        this.name = n;
        this.action = a;
        this.trigger = t;
        this.state = new RuleStateActive();
        this.ruleTypeDescription = "Simple Rule";
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
    
    public RuleState getState() {
        return this.state;
    }
    
    public String getRuleTypeDescription(){
        return this.ruleTypeDescription;
    }
    
    public void setRuleTypeDescription(String s){
        this.ruleTypeDescription = s;
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
    
    public boolean isActive(){
        return state instanceof RuleStateActive;
    }
    
    public boolean evaluateTrigger(){
        return state.checkTrigger(this.trigger);
    }
    
    public void setState(boolean active){
        if (active){
            state = new RuleStateActive();
        } else {
            state = new RuleStateInactive();
        }  
    }
}
