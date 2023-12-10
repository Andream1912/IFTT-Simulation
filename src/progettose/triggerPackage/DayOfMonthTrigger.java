package progettose.triggerPackage;

import java.time.LocalDate;

// Represents a trigger based on a specific day of the month.
public class DayOfMonthTrigger implements Trigger {

    // The day of the month associated with the trigger.
    private int day;

    // The type of the trigger.
    private String type;

    // Constructor to create a DayOfMonthTrigger with a specified day.
    public DayOfMonthTrigger(int day) {
        this.day = day;
        this.type = "Day of Month";
    }

    // Getter for obtaining the day of the month associated with the trigger.
    public int getDay() {
        return day;
    }

    // Setter for updating the day of the month associated with the trigger.
    public void setDay(int day) {
        this.day = day;
    }

    // Evaluates the DayOfMonthTrigger by checking if the current day of the month is equal to the trigger day.
    @Override
    public boolean evaluate() {
        int currentDay = LocalDate.now().getDayOfMonth();
        return day == currentDay;
    }

    // Returns a string representation of the DayOfMonthTrigger, indicating the specific day.
    @Override
    public String toString() {
        switch (day) {
            case 1:
                return "On " + day + "st of Month";
            case 2:
                return "On " + day + "nd of Month";
            case 3:
                return "On " + day + "rd of Month";
            default:
                return "On " + day + "th of Month";
        }
    }

    // Getter for obtaining the type of the trigger.
    @Override
    public String getType() {
        return this.type;
    }

    // Returns the CSV representation of the DayOfMonthTrigger, which is the trigger day as a string.
    @Override
    public String getToCSV() {
        return Integer.toString(this.day);
    }
}
