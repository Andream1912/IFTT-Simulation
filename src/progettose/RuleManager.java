package progettose;

import javafx.collections.ObservableList;

public interface RuleManager {

    public void addRule(Rule r);

    public void removeRule(Rule r);

    public ObservableList<Rule> getRules();
}
