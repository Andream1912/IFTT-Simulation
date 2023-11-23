package progettose.triggerPackage;

import java.time.LocalTime;

public class TimeTrigger implements Trigger {

    private LocalTime time;

    public TimeTrigger(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public void evaluate() {
        System.out.println("Evaluate not yet available");

    }

}
