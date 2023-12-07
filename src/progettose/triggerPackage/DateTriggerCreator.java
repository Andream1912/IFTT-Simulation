package progettose.triggerPackage;

import java.time.LocalDate;

public class DateTriggerCreator extends TriggerCreator {

    private final LocalDate date;

    public DateTriggerCreator(LocalDate date) {
        this.date = date;
    }

    @Override
    public Trigger createTrigger() {
        return new DateTrigger(this.date);
    }

}
