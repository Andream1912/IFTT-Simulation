package progettose.rulePackage;

import progettose.rulePackage.RuleState;
import progettose.triggerPackage.Trigger;

public class RuleStateActive implements RuleState {

    @Override
    public boolean checkTrigger(Trigger t) {
        return t.evaluate();
    }
    
    @Override 
    public String toString(){
        return "Active";
    }
}
