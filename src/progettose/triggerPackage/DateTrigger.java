package progettose.triggerPackage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTrigger implements Trigger {

    private final LocalDate date;
    private final String type;
    private boolean evaluation;
    private boolean changed;

    public DateTrigger(LocalDate date) {
        this.date = date;
        this.type = "Date";
        this.evaluation = false;
        this.changed = false;
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

    /*@Override
    public boolean evaluate() {
        return LocalDate.now().equals(this.date);
    }*/
    
    @Override
    public void evaluate(){
        boolean newEvaluation = LocalDate.now().equals(this.date);
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
