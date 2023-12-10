package progettose.triggerPackage;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class DateTriggerTest {

    @Test
    public void testEvaluateSameDate() {
        // Set up the DateTrigger for the current day
        DateTrigger trigger = new DateTrigger(LocalDate.now());
        
        // Evaluate the trigger
        trigger.evaluate();
        assertTrue(trigger.returnEvaluation());
        
        trigger.setDate(LocalDate.now().plusDays(8));
        trigger.evaluate();
        assertFalse(trigger.returnEvaluation());
    }
    
    @Test
    public void testEvaluateDifferentDate() {
        // Set up the DateTrigger for the current day
        DateTrigger trigger = new DateTrigger(LocalDate.now().minusDays(3));
        
        // Evaluate the trigger
        trigger.evaluate();
        assertFalse(trigger.returnEvaluation());
        
        trigger.setDate(LocalDate.now());
        trigger.evaluate();
        assertTrue(trigger.returnEvaluation());
    }
    
    @Test
    public void testDateTriggerCreation() {
        LocalDate testDate = LocalDate.of(2023, 1, 1);
        DateTrigger dateTrigger = new DateTrigger(testDate);

        assertEquals(testDate, dateTrigger.getDate());
        assertEquals("Date", dateTrigger.getType());
        assertEquals(testDate.toString(), dateTrigger.getToCSV());
    }
}
