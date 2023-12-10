package progettose.triggerPackage;

// Represents a trigger based on a specific day of the week.
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Locale;

public class DayOfWeekTrigger implements Trigger {

    // The day of the week associated with the trigger.
    private String day;

    // The type of the trigger.
    private final String type;

    // Constructor to create a DayOfWeekTrigger with a specified day.
    public DayOfWeekTrigger(String day) {
        this.day = day;
        this.type = "Day of Week";
    }

    // Getter for obtaining the day of the week associated with the trigger.
    public String getDay() {
        return day;
    }

    // Setter for updating the day of the week associated with the trigger.
    public void setDay(String day) {
        this.day = day;
    }

    // Evaluates the DayOfWeekTrigger by checking if the current day of the week matches the trigger day.
    @Override
    public boolean evaluate() {
        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
        return day.toUpperCase(Locale.ITALY).equals(currentDay.toString());
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
}
