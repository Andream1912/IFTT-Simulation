package progettose.rulePackage;

import progettose.rulePackage.Rule;
import java.time.LocalDateTime;
import progettose.actionPackage.Action;
import progettose.triggerPackage.Trigger;

public class SleepingTimeRule extends Rule{

    private LocalDateTime lastTimeFired;
    private int numDays;
    private int numHours;
    private int numMinutes;
    

    public SleepingTimeRule(String n, Action a, Trigger t, int day, int hour, int minute) {
        super(n, a, t);
        this.numDays = day;
        this.numHours = hour;
        this.numMinutes = minute;
        this.lastTimeFired=null; 
        this.setRuleTypeDescription("Fire again after\n"+numDays+"d, "+numHours+"h, "+numMinutes+"m");
    }
    

    public LocalDateTime getLastTimeFired() {
        return lastTimeFired;
    }

    public void setLastTimeFired(LocalDateTime lastTimeFired) {
        this.lastTimeFired = lastTimeFired;
    }

    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    public int getNumHours() {
        return numHours;
    }

    public void setNumHours(int numHours) {
        this.numHours = numHours;
    }

    public int getNumMinutes() {
        return numMinutes;
    }

    public void setNumMinutes(int numMinutes) {
        this.numMinutes = numMinutes;
    }

    public boolean isAfterSleepingTime(){
        if(!(this.lastTimeFired == null))
            return LocalDateTime.now().isAfter(lastTimeFired.plusDays(numDays).plusHours(numHours).plusMinutes(numMinutes));
        else 
            return true;
    }
    
    
    @Override
    public boolean evaluateTrigger(){
        if(this.isAfterSleepingTime())
            if(this.getState().checkTrigger(this.getTrigger())){
                lastTimeFired = LocalDateTime.now();
                return true;
            }
        return false;
    }
}

