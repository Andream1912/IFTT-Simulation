package progettose.triggerPackage;

// Represents a trigger based on a specific date.
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTrigger implements Trigger {

    // The date associated with the trigger.
    private LocalDate date;

    // The type of the trigger.
    private final String type;

    // Constructor to create a DateTrigger with a specified date.
    public DateTrigger(LocalDate date) {
        this.date = date;
        this.type = "Date";
    }

    // Setter for updating the date associated with the trigger.
    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Getter for obtaining the date associated with the trigger.
    public LocalDate getDate() {
        return this.date;
    }

    // Getter for obtaining the type of the trigger.
    @Override
    public String getType() {
        return this.type;
    }

    // Returns the CSV representation of the DateTrigger.
    @Override
    public String getToCSV() {
        return this.date.toString();
    }

    // Returns a string representation of the DateTrigger.
    @Override
    public String toString() {
        return "On " + this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    // Evaluates the DateTrigger by checking if the current date is equal to the trigger date.
    @Override
    public boolean evaluate() {
        return LocalDate.now().equals(this.date);
    }
}
