package progettose.triggerPackage;

import java.time.LocalDate;

public class DayOfWeekTrigger implements Trigger {

    private String day;
    private final String type;
    private boolean evaluation;
    private boolean changed;

    public DayOfWeekTrigger(String day) {
        this.day = day;
        this.type = "Day of Week";
        this.evaluation = false;
        this.changed = false;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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
    
    /*@Override
    public boolean evaluate() {
        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
        return day.toUpperCase(Locale.ITALY).equals(currentDay.toString());
    }*/
    
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
