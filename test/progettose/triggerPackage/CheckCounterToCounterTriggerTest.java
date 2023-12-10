package progettose.triggerPackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import progettose.counterPackage.CounterList;

public class CheckCounterToCounterTriggerTest {
    
     //CheckCounterToCounterTrigger testing
    //Attributes for CheckCounterToCounterTrigger testing
    CounterList countList = new CounterList();

    // The original file for testing
    static File testFile = new File(System.getProperty("user.dir") + "/counter.csv");

    // Backup copy of the original file
    static File testFileBackup = new File(System.getProperty("user.dir") + "/counter_backup.csv");

    @BeforeClass
    public static void setUp() {
        // Deletes the test file if it exists

        try {
            // Copies the file to the current location with a new name (e.g., counter_backup.csv)
            Files.copy(testFile.toPath(), testFileBackup.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Deletes the original test file
            if (testFile.exists()) {
                testFile.delete();
            }
        } catch (IOException e) {
            System.out.println("Error SETUP CounterList");
        }
    }
    
    //Testing for initialization with CheckCounterToCounterTriggerCreator
    @Test
    public void testCheckCounterToCounterTriggerCreator(){
        CheckCounterToCounterTriggerCreator checkCountTC = new CheckCounterToCounterTriggerCreator("counter1", "=", "counter2");
        Trigger checkCount = checkCountTC.createTrigger();
        assertTrue(checkCount instanceof CheckCounterToCounterTrigger);
    }
    
    //Testing for evaluate method for CheckCounterToCounterTrigger
    @Test
    public void testCounterValueToCounterTriggerEvaluate() {
        try {
            //Adding counter and creating all trigger scenarios
            countList.addCounter("counter1", 1);
            countList.addCounter("counter2", 1);
            countList.addCounter("counter3", 0);
            countList.addCounter("counter4", 4);
            CheckCounterToCounterTrigger checkEqual = new CheckCounterToCounterTrigger("counter1", "counter2", "=");
            CheckCounterToCounterTrigger checkGreater = new CheckCounterToCounterTrigger("counter1", "counter3", ">");
            CheckCounterToCounterTrigger checkGreaterEqual = new CheckCounterToCounterTrigger("counter1", "counter2", ">=");
            CheckCounterToCounterTrigger checkLesser = new CheckCounterToCounterTrigger("counter1", "counter4", "<");
            CheckCounterToCounterTrigger checkLesserEqual = new CheckCounterToCounterTrigger("counter1", "counter2", "<=");
            
            //Starting evaluate for every trigger scenarios
            checkEqual.evaluate();
            checkGreater.evaluate();
            checkGreaterEqual.evaluate();
            checkLesser.evaluate();
            checkLesserEqual.evaluate();
            
            //Check if return values are correct
            assertTrue(checkEqual.returnEvaluation());
            assertTrue(checkGreater.returnEvaluation());
            assertTrue(checkGreaterEqual.returnEvaluation());
            assertTrue(checkLesser.returnEvaluation());
            assertTrue(checkLesserEqual.returnEvaluation());

        } catch (Exception e) {
            System.out.println("Error while testing: " + e.getMessage());
        }
    }

    @AfterClass
    public static void restoreOriginalFile() throws IOException {
        // Restores the original file at the end of all tests
        Files.copy(testFileBackup.toPath(), testFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        // Deletes the backup file
        Files.delete(testFileBackup.toPath());
    }
    
}
