package progettose.triggerPackage;

/**
 *
 * @author manue
 */
public class DayOfWeekTriggerCreator extends TriggerCreator{
    private String day;
   
    public DayOfWeekTriggerCreator(String day) {
        this.day = day;
    }

    @Override
    public Trigger createTrigger() {
        return new DayOfWeekTrigger(this.day);
    }
}
