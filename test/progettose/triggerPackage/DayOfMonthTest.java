package progettose.triggerPackage;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class DayOfMonthTest {

    @Test
    public void testEvaluateSameDay() {
        // Set up the DayOfMonthTrigger for the current day
        DayOfMonthTrigger trigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth());

        // Evaluate the trigger
        trigger.evaluate();
        assertTrue(trigger.returnEvaluation());

        trigger.setDay(LocalDate.now().minusDays(1).getDayOfMonth());
        trigger.evaluate();
        assertFalse(trigger.returnEvaluation());

    }

    @Test
    public void testEvaluateDifferentDay() {
        // Set up the DayOfMonthTrigger for a day different from the current day
        DayOfMonthTrigger trigger = new DayOfMonthTrigger(1); // Assuming the current day is not the first day of the month

        // Evaluate the trigger
        trigger.evaluate();
        assertFalse(trigger.returnEvaluation());

        trigger.setDay(LocalDate.now().getDayOfMonth());
        trigger.evaluate();
        assertTrue(trigger.returnEvaluation());
    }

    @Test
    public void testSetDay() {
        // Set up the DayOfMonthTrigger with an initial day
        DayOfMonthTrigger trigger = new DayOfMonthTrigger(10);

        // Change the day
        int newDay = 20;
        trigger.setDay(newDay);

        // Check if the day was set correctly
        assertEquals(newDay, trigger.getDay());

        newDay = 10;
        trigger.setDay(newDay);
        assertEquals(newDay, trigger.getDay());
    }
}
