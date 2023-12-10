package progettose.triggerPackage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeTrigger implements Trigger {

    // The specific time at which this trigger should activate
    private LocalTime time;
    private final String type;
    private boolean evaluation;
    private boolean changed;

    // Constructor to initialize a TimeTrigger with the specified LocalTime.
    public TimeTrigger(LocalTime time) {
        this.time = time;
        this.type = "Time";
        this.evaluation = false;
        this.changed = false;
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

    @Override
    public String toString() {
        return "At " + time;
    }

    @Override
    public String getToCSV() {
        return this.time.toString();
    }
    
    /*// Evaluates whether the trigger condition is verified.
    // This trigger activates if the current time equals the specified time.
    @Override
    public boolean evaluate() {
        return time.format(DateTimeFormatter.ofPattern("HH::mm")).equals(LocalTime.now().format(DateTimeFormatter.ofPattern("HH::mm")));
    }*/
    
    @Override
    public void evaluate(){
        boolean newEvaluation = time.format(DateTimeFormatter.ofPattern("HH::mm")).equals(LocalTime.now().format(DateTimeFormatter.ofPattern("HH::mm")));
        this.changed = this.evaluation != newEvaluation;
        this.evaluation = newEvaluation;
        
    }
    
    @Override 
    public boolean returnEvaluation(){
        if(this.changed)
            if(this.evaluation)
                return true;
        return false;
    }
    
    @Override
    public void reset() {
        this.evaluation = false;
        this.changed = false;
    }
}
