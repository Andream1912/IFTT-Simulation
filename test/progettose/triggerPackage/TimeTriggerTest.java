package progettose.triggerPackage;

import org.junit.Test;
import java.time.LocalTime;
import static org.junit.Assert.*;

public class TimeTriggerTest {

    @Test
    public void testTimeTriggerEvaluation() {
        // Creating an instance of TimeTrigger with a specific time stamp
        LocalTime time = LocalTime.now();

        TimeTrigger timeTrigger = new TimeTrigger(time);

        // Trigger Execution
        timeTrigger.evaluate();
        //The results should be true because returnEvalutaion compares the actual time to his time attribute
        assertTrue(timeTrigger.returnEvaluation());
        timeTrigger.setTime(LocalTime.now().minusMinutes(1));
        // Trigger Execution
        timeTrigger.evaluate();
        //The results should be false because the actual time is different from the trigger time attribute
        assertFalse(timeTrigger.returnEvaluation());

    }

    @Test
    public void testTimeTriggerCreator() {
        //Creating an instance of TimeTriggerCreator with a specific time stamp
        LocalTime time = LocalTime.of(12, 30);
        TimeTriggerCreator timeTriggerCreator = new TimeTriggerCreator(time);

        //Trigger creation using the creator
        Trigger trigger = timeTriggerCreator.createTrigger();
        //Check if the method "createTrigger()" of TimeTriggerCreator class returns a TimeTrigger
        assertTrue(trigger instanceof TimeTrigger);

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
