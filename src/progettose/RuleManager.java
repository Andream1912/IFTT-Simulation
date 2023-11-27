package progettose;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class RuleManager {
    private ObservableList<Rule> rules;
    private static RuleManager UniqueInstance=null;
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    
    //invisible constructor
    private RuleManager(){
        this.rules =FXCollections.observableArrayList();     
    }
    
    public static RuleManager getInstance(){
        if(UniqueInstance==null){
            UniqueInstance=new RuleManager();
        }
        return UniqueInstance;
    }
    
    public ObservableList<Rule> getRules(){
        return this.rules;
    }
    
    public void setRules(ObservableList<Rule> r){
        this.rules=r;
    }
    
    public void addRule(Rule r){
        this.rules.add(r);
    }
    
    public void removeRule(Rule r){
        this.rules.remove(r);
    }
    
    public void periodicCheck(){
        //Scheduler for check the rule firing
        scheduler.scheduleAtFixedRate(() -> {
            for (Rule r : rules) {
                if (r.getTrigger().evaluate()) {
                    this.fireRule(r);
                }
            }
        }, 0, 3, TimeUnit.SECONDS);
    }
    
    public void fireRule(Rule r){
        r.getAction().execute();
    }
    
    public static void shutdownScheduler() {
        //Remember to use it everytime a scheduler is created
        scheduler.shutdown();
    }
                
}
