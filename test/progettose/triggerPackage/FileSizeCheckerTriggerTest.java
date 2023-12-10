package progettose.triggerPackage;

import org.junit.Test;
import java.io.IOException;
import java.nio.file.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

public class FileSizeCheckerTriggerTest {

    private Path tempFilePath;

    @Before
    public void setUp() throws IOException {
        // Create a temporary file for testing
        tempFilePath = Files.createTempFile("test", ".txt");
        Files.write(tempFilePath, "Test content".getBytes());
    }

    @Test
    public void testEvaluateFileSizeInByte() throws IOException {
        // Create a trigger with a specific file size in bytes
        FileSizeCheckerTrigger trigger = new FileSizeCheckerTrigger(tempFilePath, 1, "");

        // Evaluate the trigger for different file sizes and test cases
        trigger.evaluate();
        assertTrue(trigger.returnEvaluation()); // Expecting true
        trigger.setValue(2);
        trigger.evaluate();
        assertFalse(trigger.returnEvaluation()); // Expecting false
        trigger.setValue(-2);
        trigger.evaluate();
        assertFalse(trigger.returnEvaluation()); // Expecting false
        trigger.setValue(0);
        trigger.evaluate();
        assertFalse(trigger.returnEvaluation()); // Expecting false

        // Delete the temporary file created for testing
        Files.delete(tempFilePath);
    }

    @Test
    public void testEvaluateFileSizeInKiloByte() throws IOException {
        // Create a trigger with a specific file size in kilobytes
        FileSizeCheckerTrigger trigger = new FileSizeCheckerTrigger(tempFilePath, (long) 0.1, "KB");

        // Evaluate the trigger for different file sizes and test cases
        trigger.evaluate();
        assertTrue(trigger.returnEvaluation()); // Expecting true
        trigger.setValue(9);
        trigger.evaluate();
        assertFalse(trigger.returnEvaluation()); // Expecting false
        trigger.setValue(-6);
        trigger.evaluate();
        assertTrue(trigger.returnEvaluation()); // Expecting true
        trigger.setValue(0);
        trigger.evaluate();
        assertFalse(trigger.returnEvaluation()); // Expecting false

        // Delete the temporary file created for testing
        Files.delete(tempFilePath);
    }

    @Test
    public void testEvaluateFileSizeInMegaByte() throws IOException {
        // Create a trigger with a specific file size in megabytes
        FileSizeCheckerTrigger trigger = new FileSizeCheckerTrigger(tempFilePath, (long) 0.0001, "MB");

        // Evaluate the trigger for different file sizes and test cases
        trigger.evaluate();
        assertTrue(trigger.returnEvaluation()); // Expecting true
        trigger.setValue(8);
        trigger.evaluate();
        assertFalse(trigger.returnEvaluation()); // Expecting false
        trigger.setValue(-12);
        trigger.evaluate();
        assertTrue(trigger.returnEvaluation()); // Expecting true
        trigger.setValue(0);
        trigger.evaluate();
        assertFalse(trigger.returnEvaluation()); // Expecting false

        // Delete the temporary file created for testing
        Files.delete(tempFilePath);
    }

    @Test
    public void testEvaluateFileSizeInGigaByte() throws IOException {
        // Create a trigger with a specific file size in gigabytes
        FileSizeCheckerTrigger trigger = new FileSizeCheckerTrigger(tempFilePath, (long) 0.0000001, "GB");

        // Evaluate the trigger for different file sizes and test cases
        trigger.evaluate();
        assertTrue(trigger.returnEvaluation()); // Expecting true
        trigger.setValue(23);
        trigger.evaluate();
        assertFalse(trigger.returnEvaluation()); // Expecting false
        trigger.setValue(-102);
        trigger.evaluate();
        assertTrue(trigger.returnEvaluation()); // Expecting true
        trigger.setValue(0);
        trigger.evaluate();
        assertFalse(trigger.returnEvaluation()); // Expecting false

        // Delete the temporary file created for testing
        Files.delete(tempFilePath);
    }
}
