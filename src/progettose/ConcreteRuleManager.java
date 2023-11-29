package progettose;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConcreteRuleManager implements RuleManager {

    private ObservableList<Rule> rules;
    private static ConcreteRuleManager UniqueInstance = null;
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    private ConcreteRuleManager() {
        this.rules = FXCollections.observableArrayList();
    }

    public static ConcreteRuleManager getInstance() {
        //Singleton pattern to Rulemanager
        if (UniqueInstance == null) {
            UniqueInstance = new ConcreteRuleManager();
        }
        return UniqueInstance;
    }

    public void setRules(ObservableList<Rule> r) {
        this.rules = r;
    }

    public void periodicCheck() {
        //Scheduler check the rule firing
        scheduler.scheduleAtFixedRate(() -> {
            for (Rule r : rules) {
                if (r.getTrigger().evaluate()) {
                    this.fireRule(r);
                }
            }
        }, 0, 3, TimeUnit.SECONDS);
    }

    public void fireRule(Rule r) {
        r.getAction().execute();
    }

    public static void shutdownScheduler() {
        //Use it everytime a scheduler is created
        scheduler.shutdown();
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

}
