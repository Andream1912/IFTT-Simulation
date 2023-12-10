package progettose.triggerPackage;

import java.time.LocalDate;

public class DayOfMonthTrigger implements Trigger {

    private int day;
    private String type;
    private boolean evaluation;
    private boolean changed;

    public DayOfMonthTrigger(int day) {
        this.day = day;
        this.type = "Day of Month";
        this.evaluation = false;
        this.changed = false;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

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

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getToCSV() {
        return Integer.toString(this.day);
    }
    
    /*@Override
    public boolean evaluate() {
        int currentDay = LocalDate.now().getDayOfMonth();
        return day == currentDay;
    }*/
    
    @Override
    public void evaluate(){
        boolean newEvaluation = LocalDate.now().getDayOfMonth() == this.day;
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
