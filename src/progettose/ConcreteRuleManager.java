package progettose;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConcreteRuleManager implements RuleManager {

    private ObservableList<Rule> rules;
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public ConcreteRuleManager() {
        this.rules = FXCollections.observableArrayList();
    }

    public void setRules(ObservableList<Rule> r) {
        this.rules = r;
    }
    
    public void periodicCheck() {
        //Scheduler check the rule firing
        scheduler.scheduleAtFixedRate(() -> {
            for (Rule r : rules) {
                if(r.getFiredOnce()){
                  if(r.getLastTimeFired() == null && r.evaluateTrigger()){
                      this.fireRule(r);
                  }  
                }
                if (!r.getFiredOnce() && r.evaluateTrigger()) {
                    this.fireRule(r);
                }
            }
        }, 0, 3, TimeUnit.SECONDS);
    }

    public void fireRule(Rule r) {
        r.getAction().execute();
        r.setLastTimeFired(LocalDateTime.now());
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
    
    public void activateRule(Rule r) {
        r.setState(true);
    }
    
    public void deactivateRule(Rule r) {
        r.setState(false);
    }
    
}
