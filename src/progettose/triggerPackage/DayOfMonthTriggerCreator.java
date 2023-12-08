package progettose.triggerPackage;

public class DayOfMonthTriggerCreator extends TriggerCreator {

    private final int day;

    public DayOfMonthTriggerCreator(int day) {
        this.day = day;
    }

    @Override
    public Trigger createTrigger() {
        return new DayOfMonthTrigger(this.day);
    }
}
