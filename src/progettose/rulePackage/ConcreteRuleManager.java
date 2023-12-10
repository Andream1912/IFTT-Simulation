package progettose.rulePackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConcreteRuleManager implements RuleManager {

    private ObservableList<Rule> rules;

    public ConcreteRuleManager() {
        this.rules = FXCollections.observableArrayList();
    }

    public void fireRule(Rule r) {
        r.getAction().execute();
    }

    @Override
    public void addRule(Rule r) {
        this.rules.add(r);
    }

    @Override
    public void removeRule(Rule r) {
        this.rules.remove(r);
    }

    @Override
    public ObservableList<Rule> getRules() {
        return this.rules;
    }

    @Override
    public void activateRule(Rule r) {
        r.setState(true);
        r.getTrigger().reset();
        
    }

    @Override
    public void deactivateRule(Rule r) {
        r.setState(false);
    }

}
