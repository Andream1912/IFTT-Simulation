package progettose.triggerPackage;

import java.time.LocalTime;

public class TimeTriggerCreator extends TriggerCreator {

    // The specific time to be verified by the TimeTrigger
    private final LocalTime time;

    // Constructor to initialize a TimeTriggerCreator with the specified LocalTime.
    public TimeTriggerCreator(LocalTime time) {
        this.time = time;
    }

    // Creates and returns a TimeTrigger instance based on the configured LocalTime.
    @Override
    public Trigger createTrigger() {
        return new TimeTrigger(this.time);
    }
}
