package progettose.triggerPackage;

import static java.lang.Math.abs;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
        LocalTime now = LocalTime.now();
        long deltaSeconds = abs(Duration.between(now, time).getSeconds());
        return deltaSeconds <= 1;

    }

    @Override
    public String toString() {
        return "At " + time;
    }
}
