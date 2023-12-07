package progettose.rulePackage;

import javafx.collections.ObservableList;

public interface RuleManager {

    public void addRule(Rule r);

    public void removeRule(Rule r);

    public void activateRule(Rule r);

    public void deactivateRule(Rule r);

    public ObservableList<Rule> getRules();

}
