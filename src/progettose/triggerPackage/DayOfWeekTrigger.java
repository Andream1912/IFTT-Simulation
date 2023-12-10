package progettose.triggerPackage;


import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Locale;

public class DayOfWeekTrigger implements Trigger {

    // The day of the week associated with the trigger.
    private String day;

    // The type of the trigger.
    private final String type;
    private boolean evaluation;
    private boolean changed;

    // Constructor to create a DayOfWeekTrigger with a specified day.
    public DayOfWeekTrigger(String day) {
        this.day = day;
        this.type = "Day of Week";
        this.evaluation = false;
        this.changed = false;
    }

    // Getter for obtaining the day of the week associated with the trigger.
    public String getDay() {
        return day;
    }

    // Setter for updating the day of the week associated with the trigger.
    public void setDay(String day) {
        this.day = day;
    }

    // Returns a string representation of the DayOfWeekTrigger, indicating the specific day of the week.
    @Override
    public String toString() {
        return "On " + day;
    }

    // Getter for obtaining the type of the trigger.
    @Override
    public String getType() {
        return this.type;
    }

    // Returns the CSV representation of the DayOfWeekTrigger, which is the trigger day as a string.
    @Override
    public String getToCSV() {
        return this.day;
    }

    // Evaluates the DayOfWeekTrigger by checking if the current day of the week matches the trigger day.
    @Override
    public void evaluate(){
        boolean newEvaluation = LocalDate.now().getDayOfWeek().name().equals(this.day.toUpperCase());
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
