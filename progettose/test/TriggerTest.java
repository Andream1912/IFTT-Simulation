import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.time.LocalTime;
import progettose.triggerPackage.TimeTrigger;
import progettose.triggerPackage.TimeTriggerCreator;
import progettose.triggerPackage.Trigger;

public class TriggerTest {

    @Test
    public void testTimeTriggerExecution() {
        // Creating an instance of TimeTrigger with a specific time stamp
        LocalTime time = LocalTime.of(12, 30);

        TimeTrigger timeTrigger = new TimeTrigger(time);

        // Trigger Execution
        timeTrigger.evaluate();
    }

    @Test
    public void testTimeTriggerCreator() {
        //Creating an instance of TimeTriggerCreator with a specific time stamp
        LocalTime time = LocalTime.of(12, 30);
        TimeTriggerCreator timeTriggerCreator = new TimeTriggerCreator(time);

        //Trigger creation using the creator
        Trigger trigger = timeTriggerCreator.createTrigger();

    }

    @Test
    public void testTimeTriggerInitialization() {
        //Trigger creation with a specif time 
        LocalTime time = LocalTime.of(12, 30);
        TimeTrigger timeTrigger = new TimeTrigger(time);
        //Verify that the time has been initialized correctly
        assertEquals(time, timeTrigger.getTime());
    }

}
