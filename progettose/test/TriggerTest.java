/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.time.LocalTime;
import progettose.triggerPackage.TimeTrigger;
import progettose.triggerPackage.TimeTriggerCreator;
import progettose.triggerPackage.Trigger;


public class TriggerTest {
    
    
    @Test
    public void testTimeTriggerExecution() {
        // Creazione di un'istanza di TimeTrigger con un orario specifico
        LocalTime time = LocalTime.of(12, 30);
        
        TimeTrigger timeTrigger = new TimeTrigger(time);

        // Esecuzione del trigger
        timeTrigger.evaluate();

        // Puoi aggiungere ulteriori asserzioni se necessario
    }
    @Test
    public void testTimeTriggerCreator(){
        //Creating an instance of TimeTriggerCreator with a specific time stamp
        LocalTime time = LocalTime.of(12, 30);
        TimeTriggerCreator timeTriggerCreator = new TimeTriggerCreator(time);
        
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
