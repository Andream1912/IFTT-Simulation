package progettose.triggerPackage;

import java.time.LocalTime;

public class TimeTrigger implements Trigger {


    // The specific time at which this trigger should activate
    private LocalTime time;

    // Constructor to initialize a TimeTrigger with the specified LocalTime.
    public TimeTrigger(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
 
    // Evaluates whether the trigger condition is verified.
    // This trigger activates if the current time equals the specified time.
    @Override
    public boolean evaluate() {
        return LocalTime.now().equals(time);
    }

    @Override
    public String toString() {
        return "At " + time;
    }
}
