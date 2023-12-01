package progettose.triggerPackage;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class DateTriggerTest {

    @Test
    public void testDateTriggerCreation() {
        LocalDate testDate = LocalDate.of(2023, 1, 1);
        DateTrigger dateTrigger = new DateTrigger(testDate);
        
        assertEquals(testDate, dateTrigger.getDate());
        assertEquals("Date", dateTrigger.getType());
        assertEquals(testDate.toString(), dateTrigger.getToCSV());
        assertEquals("On " + testDate, dateTrigger.toString());
    }

    @Test
    public void testDateTriggerEvaluation() {
        // Test for a date in the future
        LocalDate futureDate = LocalDate.now().plusDays(1);
        DateTrigger futureDateTrigger = new DateTrigger(futureDate);
        assertFalse(futureDateTrigger.evaluate());

        // Test for the current date
        DateTrigger currentDateTrigger = new DateTrigger(LocalDate.now());
        assertTrue(currentDateTrigger.evaluate());

        // Test for a date in the past
        LocalDate pastDate = LocalDate.now().minusDays(1);
        DateTrigger pastDateTrigger = new DateTrigger(pastDate);
        assertFalse(pastDateTrigger.evaluate());
    }
}
