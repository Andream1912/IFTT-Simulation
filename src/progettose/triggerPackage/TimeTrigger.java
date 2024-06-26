package progettose.triggerPackage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// Represents a trigger that activates at a specific time.
public class TimeTrigger implements Trigger {

    // The specific time at which this trigger should activate
    private LocalTime time;

    // Type of the trigger.
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

    // Getter for obtaining the specific time.
    public LocalTime getTime() {
        return this.time;
    }

    // Setter for updating the specific time.
    public void setTime(LocalTime time) {
        this.time = time;
    }

    // Getter for obtaining the type of the trigger.
    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "At " + time;
    }

    // Returns the CSV representation of the TimeTrigger, providing the specific time.
    @Override
    public String getToCSV() {
        return this.time.toString();
    }
    
    // Evaluates whether the trigger condition is verified.
    // This trigger activates if the current time equals the specified time.
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
