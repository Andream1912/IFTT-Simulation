package progettose.rulePackage;

import progettose.triggerPackage.Trigger;

public class RuleStateActive implements RuleState {

    @Override
    public boolean checkTrigger(Trigger t) {
        t.evaluate();
        return t.returnEvaluation();
    }

    @Override
    public String toString() {
        return "Active";
    }
}
