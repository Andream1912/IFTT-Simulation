package progettose.triggerPackage;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.Locale;

public class DayOfWeekTrigger implements Trigger {

    private String day;
    private final String type;

    public DayOfWeekTrigger(String day) {
        this.day = day;
        this.type = "Day of Week";
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public boolean evaluate() {
        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
        return day.toUpperCase(Locale.ITALY).equals(currentDay.toString());
    }

    @Override
    public String toString() {
        return "On " + day;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getToCSV() {
        return this.day;
    }

}
