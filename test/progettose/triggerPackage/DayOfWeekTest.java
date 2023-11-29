package progettose.triggerPackage;
import java.time.DayOfWeek;
import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;

public class DayOfWeekTest {

    @Test
    public void testEvaluateSameDay() {
        // Set up the DayOfWeekTrigger for the current day
        DayOfWeekTrigger trigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek().toString());

        // Evaluate the trigger
        assertTrue(trigger.evaluate());
    }

    @Test
    public void testEvaluateDifferentDay() {
        // Set up the DayOfWeekTrigger for a day different from the current day
        DayOfWeekTrigger trigger = new DayOfWeekTrigger(DayOfWeek.MONDAY.toString());

        // Evaluate the trigger
        assertFalse(trigger.evaluate());
    }

    @Test
    public void testToString() {
        // Set up the DayOfWeekTrigger for a specific day
        String day = DayOfWeek.WEDNESDAY.toString();
        DayOfWeekTrigger trigger = new DayOfWeekTrigger(day);

        // Check the toString representation
        assertEquals("On " + day, trigger.toString());
    }

    @Test
    public void testSetDay() {
        // Set up the DayOfWeekTrigger with an initial day
        DayOfWeekTrigger trigger = new DayOfWeekTrigger(DayOfWeek.FRIDAY.toString());

        // Change the day
        String newDay = DayOfWeek.SUNDAY.toString();
        trigger.setDay(newDay);

        // Check if the day was set correctly
        assertEquals(newDay, trigger.getDay());
    }
}
