package progettose.triggerPackage;

import static java.lang.Math.abs;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeTrigger implements Trigger {

    // The specific time at which this trigger should activate
    private LocalTime time;
    private final String type;

    // Constructor to initialize a TimeTrigger with the specified LocalTime.
    public TimeTrigger(LocalTime time) {
        this.time = time;
        this.type = "Time";
    }

    public LocalTime getTime() {
        return this.time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String getType() {
        return this.type;
    }

    // Evaluates whether the trigger condition is verified.
    // This trigger activates if the current time equals the specified time.
    @Override
    public boolean evaluate() {
        return time.format(DateTimeFormatter.ofPattern("HH::mm")).equals(LocalTime.now().format(DateTimeFormatter.ofPattern("HH::mm")));
    }

    @Override
    public String toString() {
        return "At " + time;
    }

    @Override
    public String getToCSV() {
        return this.time.toString();
    }
}
