package progettose.rulePackage;

import java.time.LocalDateTime;
import progettose.actionPackage.Action;
import progettose.triggerPackage.Trigger;

// Represents a rule that fires again after a specified sleeping time.
public class SleepingTimeRule extends Rule {

    // Time when the rule was last fired.
    private LocalDateTime lastTimeFired;
    private int numDays;
    private int numHours;
    private int numMinutes;

    // Constructor to initialize the rule with a name, action, trigger, and sleeping time components.
    public SleepingTimeRule(String n, Action a, Trigger t, int day, int hour, int minute) {
        super(n, a, t);
        this.numDays = day;
        this.numHours = hour;
        this.numMinutes = minute;
        this.lastTimeFired = null;
        this.setRuleTypeDescription("Fire again after\n" + numDays + "d, " + numHours + "h, " + numMinutes + "m");
    }

    // Getter for the last time the rule was fired.
    public LocalDateTime getLastTimeFired() {
        return lastTimeFired;
    }

    // Setter for the last time the rule was fired.
    public void setLastTimeFired(LocalDateTime lastTimeFired) {
        this.lastTimeFired = lastTimeFired;
    }

    // Getter for the number of days in the sleeping time.
    public int getNumDays() {
        return numDays;
    }

    // Setter for the number of days in the sleeping time.
    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    // Getter for the number of hours in the sleeping time.
    public int getNumHours() {
        return numHours;
    }

    // Setter for the number of hours in the sleeping time.
    public void setNumHours(int numHours) {
        this.numHours = numHours;
    }

    // Getter for the number of minutes in the sleeping time.
    public int getNumMinutes() {
        return numMinutes;
    }

    // Setter for the number of minutes in the sleeping time.
    public void setNumMinutes(int numMinutes) {
        this.numMinutes = numMinutes;
    }

    // Checks if the current time is after the sleeping time.
    public boolean isAfterSleepingTime() {
        if (!(this.lastTimeFired == null)) {
            return LocalDateTime.now().isAfter(lastTimeFired.plusDays(numDays).plusHours(numHours).plusMinutes(numMinutes));
        } else {
            return true;
        }
    }

    // Overrides the evaluateTrigger method to check if the rule can be triggered after the sleeping time.
    @Override
    public boolean evaluateTrigger() {
        if (this.isAfterSleepingTime()) {
            if (this.getState().checkTrigger(this.getTrigger())) {
                lastTimeFired = LocalDateTime.now();
                this.getTrigger().reset();
                return true;
            }
        }
        return false;
    }
}
