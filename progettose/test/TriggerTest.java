/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import progettose.triggerPackage.TimeTrigger;
import progettose.triggerPackage.TimeTriggerCreator;
import progettose.triggerPackage.Trigger;


public class TriggerTest {
    @Test
    public void testTimeTriggerCreator(){
        //Creating an instance of TimeTriggerCreator with a specific time stamp
        LocalTime triggerTime = LocalTime.of(12, 30);
        TimeTriggerCreator timeTriggerCreator = new TimeTriggerCreator(triggerTime);
        
        //Trigger creation using the creator
        Trigger trigger = timeTriggerCreator.createTrigger();
        
    }
    @Test
    public void testTimeTriggerInitialization(){
        //Trigger creation with a specif time 
        LocalTime time = LocalTime.of(12,30);
        TimeTrigger timeTrigger = new TimeTrigger(time);
        //Verify that the time has been initialized correctly
        assertEquals(time,timeTrigger.getTime());
    }
    
}
