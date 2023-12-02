package progettose.triggerPackage;


import org.junit.Test;
import java.io.IOException;
import java.nio.file.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileSizeCheckerTriggerTest {

    @Test
    public void testEvaluateFileSizeInByte() throws IOException {
        // Create a temporary file
        Path filePath = Files.createTempFile("test", ".txt");
        Files.write(filePath, "Test content".getBytes());

        // Create a FileSizeCheckerTrigger with a specific file size in bytes
        FileSizeCheckerTrigger trigger = new FileSizeCheckerTrigger(filePath, 1, "");

        // Evaluate the trigger
        assertTrue(trigger.evaluate());

        // Delete the temporary file
        Files.delete(filePath);
    }

    @Test
    public void testEvaluateFileSizeInKiloByte() throws IOException {
        // Create a temporary file
        Path filePath = Files.createTempFile("test", ".txt");
        Files.write(filePath, "Test content".getBytes());

        // Create a FileSizeCheckerTrigger with a specific file size in kilobytes
        FileSizeCheckerTrigger trigger = new FileSizeCheckerTrigger(filePath, (long) 0.1, "KB");

        // Evaluate the trigger
        assertTrue(trigger.evaluate());

        // Delete the temporary file
        Files.delete(filePath);
    }

    @Test
    public void testEvaluateFileSizeInMegaByte() throws IOException {
        // Create a temporary file
        Path filePath = Files.createTempFile("test", ".txt");
        Files.write(filePath, "Test content".getBytes());

        // Create a FileSizeCheckerTrigger with a specific file size in megabytes
        FileSizeCheckerTrigger trigger = new FileSizeCheckerTrigger(filePath, (long) 0.0001, "MB");

        // Evaluate the trigger
        assertTrue(trigger.evaluate());

        // Delete the temporary file
        Files.delete(filePath);
    }

    @Test
    public void testEvaluateFileSizeInGigaByte() throws IOException {
        // Create a temporary file
        Path filePath = Files.createTempFile("test", ".txt");
        Files.write(filePath, "Test content".getBytes());

        // Create a FileSizeCheckerTrigger with a specific file size in gigabytes
        FileSizeCheckerTrigger trigger = new FileSizeCheckerTrigger(filePath, (long) 0.0000001, "GB");

        // Evaluate the trigger
        assertTrue(trigger.evaluate());

        // Delete the temporary file
        Files.delete(filePath);
    }

    @Test
    public void testEvaluateInvalidTypeDimension() throws IOException {
        // Create a temporary file
        Path filePath = Files.createTempFile("test", ".txt");
        Files.write(filePath, "Test content".getBytes());

        // Create a FileSizeCheckerTrigger with an invalid type dimension
        FileSizeCheckerTrigger trigger = new FileSizeCheckerTrigger(filePath, 100, "InvalidType");

        // Evaluate the trigger
        assertFalse(trigger.evaluate());

        // Delete the temporary file
        Files.delete(filePath);
    }
}
