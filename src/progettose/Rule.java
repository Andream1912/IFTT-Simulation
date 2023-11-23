/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import progettose.actionPackage.Action;
import progettose.triggerPackage.Trigger;


/**
 *
 * @author manue
 */
public class Rule {
    private String name;
    private Trigger trigger;
    private Action action;
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    
    public Rule(String n, Action a, Trigger t){
        this.name=n;
        this.action=a;
        this.trigger=t;
        //constructor of the single rule scheduler
        if (!scheduler.isShutdown() && scheduler.isTerminated()) {
            periodicCheck();
        }
    }
    
    public Action getAction(){
        return this.action;
    }
    
    public Trigger getTrigger(){
        return this.trigger;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setAction(Action a){
        this.action=a;
    }
    
    public void setTrigger(Trigger t) {
        this.trigger=t;
    }
    
    public void setName(String n){
        this.name=n;
    }
    
        private void periodicCheck(){
        //Scheduler for check the rule firing
        scheduler.scheduleAtFixedRate(() -> {
            if (this.trigger.evaluate()) {
                fireRule();
            } 
        }, 0, 3, TimeUnit.SECONDS);
    }
    
    public void fireRule(){
        action.execute();
    }
    
    public static void shutdownScheduler() {
        //Remember to use it everytime a scheduler is created
        scheduler.shutdown();
    }
    
}
