package rulePackage;

import rulePackage.Rule;
import progettose.actionPackage.Action;
import progettose.triggerPackage.Trigger;

public class FireOnceRule extends Rule {

    public FireOnceRule(String n, Action a, Trigger t) {
        super(n, a, t);
        this.setRuleTypeDescription("Fire Only Once");
    }

    @Override
    public boolean evaluateTrigger() {
        if (this.getState().checkTrigger(this.getTrigger())) {
            this.setState(false);
            return true;
        }
        return false;
    }
}
