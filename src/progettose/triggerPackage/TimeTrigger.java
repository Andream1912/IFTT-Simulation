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
    public boolean evaluate() {
        boolean x=true;
        System.out.println("Evaluate not yet available");
        return x;
    }

}
