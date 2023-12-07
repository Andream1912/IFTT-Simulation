package progettose.rulePackage;

import progettose.rulePackage.RuleState;
import progettose.triggerPackage.Trigger;

public class RuleStateInactive implements RuleState{

    @Override
    public boolean checkTrigger(Trigger t) {
        return false;
    }
    
    @Override 
    public String toString(){
        return "Inactive";
    }
}
