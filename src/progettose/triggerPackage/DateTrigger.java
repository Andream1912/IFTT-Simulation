package progettose.triggerPackage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTrigger implements Trigger {

    private final LocalDate date;
    private final String type;

    public DateTrigger(LocalDate date) {
        this.date = date;
        this.type = "Date";
    }

    public LocalDate getDate() {
        return this.date;
    } 
    
    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getToCSV() {
        return this.date.toString();
    }

    @Override
    public String toString() {
        return "On " + this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    @Override
    public boolean evaluate() {
        return LocalDate.now().equals(this.date);
    }
    
    

}
