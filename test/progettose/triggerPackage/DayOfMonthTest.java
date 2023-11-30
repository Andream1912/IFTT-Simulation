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
        assertTrue(trigger.evaluate());
    }

    @Test
    public void testEvaluateDifferentDay() {
        // Set up the DayOfMonthTrigger for a day different from the current day
        DayOfMonthTrigger trigger = new DayOfMonthTrigger(1); // Assuming the current day is not the first day of the month

        // Evaluate the trigger
        assertFalse(trigger.evaluate());
    }

    @Test
    public void testToString() {
        // Set up the DayOfMonthTrigger for a specific day
        int day = 15;
        DayOfMonthTrigger trigger = new DayOfMonthTrigger(day);

        // Check the toString representation
        assertEquals("On " + day, trigger.toString());
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
    }
}

