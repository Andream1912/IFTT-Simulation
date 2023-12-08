package progettose.triggerPackage;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.Test;
import static org.junit.Assert.*;


public class CompositeTriggerTest {
     
    @Test
    public void testORCompositeTrigger() {
        // Creating basic triggers
        DateTrigger trigger1 = new DateTrigger(LocalDate.now());
        DayOfWeekTrigger trigger2 = new DayOfWeekTrigger("Friday");

        // Creating a composite trigger with OR operator
        CompositeTrigger compositeTrigger = new CompositeTrigger("Test", trigger1, trigger2, "OR");

        // The result should be true because both triggers is true
        assertTrue(compositeTrigger.evaluate());
        
        // Setting tomorrow as Date of the DateTrigger 
        trigger1.setDate(LocalDate.now().plusDays(1));
        // The result should be true because at least one trigger is true
        assertTrue(compositeTrigger.evaluate());   
        
        // Setting today as Date of the DateTrigger 
        trigger1.setDate(LocalDate.now());
        // Setting Sunday in the DayOfWeekTrigger
        trigger2.setDay("Sunday");
        // The result should be true because at least one trigger is true
        assertTrue(compositeTrigger.evaluate());   
        
        // Setting today as Date of the DateTrigger 
        trigger1.setDate(LocalDate.now().plusDays(1));
        // Setting Sunday in the DayOfWeekTrigger
        trigger2.setDay("Sunday");
        // The result should be false because both triggers are false
        assertFalse(compositeTrigger.evaluate());   
        
    }

    @Test
    public void testANDCompositeTrigger() {
        // Creating basic triggers
        DayOfWeekTrigger trigger1 = new DayOfWeekTrigger("Friday");
        DateTrigger trigger2 = new DateTrigger(LocalDate.now());
        // Creating a composite trigger with AND operator
        CompositeTrigger compositeTrigger = new CompositeTrigger("Test", trigger1, trigger2, "AND");
        
        // The result should be true because both triggers are true
        assertTrue(compositeTrigger.evaluate());    // Today is Friday 8th so the trigger is true
        
        // Setting tomorrow as Date of the DateTrigger 
        trigger2.setDate(LocalDate.now().plusDays(1));
        // The result should be false because only one trigger is true
        assertFalse(compositeTrigger.evaluate());   
        
        // Setting today as Date of the DateTrigger 
        trigger2.setDate(LocalDate.now());
        // Setting Sunday in the DayOfWeekTrigger
        trigger1.setDay("Sunday");
        // The result should be false because only one trigger is true
        assertFalse(compositeTrigger.evaluate());   
        
        // Setting today as Date of the DateTrigger 
        trigger2.setDate(LocalDate.now().plusDays(1));
        // Setting Sunday in the DayOfWeekTrigger
        trigger1.setDay("Sunday");
        // The result should be false because both triggers are false
        assertFalse(compositeTrigger.evaluate());   
    }

    @Test
    public void testNOTCompositeTrigger() {
        // Creating basic triggers
        Trigger trigger = new DayOfWeekTrigger("Friday");

        // Creating a composite trigger with NOT operator
        CompositeTrigger compositeTrigger = new CompositeTrigger("Test", trigger, null, "NOT");

        // The result should be false because the basic trigger is true and the operator is NOT
        assertFalse(compositeTrigger.evaluate());
        
        // Creating a composite trigger with NOT operator of the previous composite trigger
        CompositeTrigger compositeTrigger1 = new CompositeTrigger("Test", compositeTrigger, null, "NOT");
        // The result should be true because the composite trigger is false and the operator is NOT
        assertTrue(compositeTrigger1.evaluate());
        
    }

    @Test
    public void testComplexCompositeTrigger() {
        // Creating basic triggers
        Trigger trigger1 = new TimeTrigger(LocalTime.now());
        Trigger trigger2 = new DayOfWeekTrigger("Friday");
        Trigger trigger3 = new DateTrigger(LocalDate.now());

        // Creating composite triggers
        CompositeTrigger compositeTrigger1 = new CompositeTrigger("Test1", trigger1, trigger2, "AND");
        CompositeTrigger compositeTrigger2 = new CompositeTrigger("Test2", trigger3, null, "NOT");

        // Creating a new composite trigger made up from the OR of the two composite trigger created before
        CompositeTrigger finalCompositeTrigger = new CompositeTrigger("TestFinal", compositeTrigger1, compositeTrigger2, "OR");

        // The result should be true because at least one of the two composite trigger is true
        assertTrue(finalCompositeTrigger.evaluate());
        
        
        // Setting TimeTrigger time to 5 minutes from now
        ((TimeTrigger)compositeTrigger1.getLeftTrigger()).setTime(LocalTime.now().plusMinutes(5));
        // The result should be false because one of the two triggers of the first composite trigger is false
        assertFalse(finalCompositeTrigger.evaluate());
        
        
        // Setting TimeTrigger time to 5 minutes from now
        ((TimeTrigger)compositeTrigger1.getLeftTrigger()).setTime(LocalTime.now());
        // Setting DayOfWeekTrigger day to Sunday
        ((DayOfWeekTrigger)compositeTrigger1.getRightTrigger()).setDay("Sunday");
        // The result should be false because one of the two triggers of the first composite trigger is false
        assertFalse(finalCompositeTrigger.evaluate());
        
        
        // Setting TimeTrigger time to 5 minutes from now
        ((TimeTrigger)compositeTrigger1.getLeftTrigger()).setTime(LocalTime.now().plusMinutes(5));
        // Setting DayOfWeekTrigger day to Sunday
        ((DayOfWeekTrigger)compositeTrigger1.getRightTrigger()).setDay("Sunday");
        // The result should be false because both triggers of the first composite trigger are false
        assertFalse(finalCompositeTrigger.evaluate());
        
        
        // Setting tomorrow as Date of the DateTrigger 
        ((DateTrigger)compositeTrigger2.getLeftTrigger()).setDate(LocalDate.now().plusDays(1));
        // The result should be true because at least one composite trigger is true
        assertTrue(finalCompositeTrigger.evaluate());   
        
    }
}
