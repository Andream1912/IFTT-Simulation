package progettose.triggerPackage;

import java.time.LocalTime;

public class TimeTriggerCreator extends TriggerCreator {

    private LocalTime time;

    public TimeTriggerCreator(LocalTime time) {
        this.time = time;
    }

    @Override
    public Trigger createTrigger() {
        return new TimeTrigger(this.time);
    }

}
