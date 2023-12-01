package progettose.triggerPackage;

import org.junit.Test;
import java.io.File;
import static org.junit.Assert.*;

public class FileCheckTest {
    //change this variable for test dynamically
    private String TestingDirectory= "";
    
   @Test
    public void testEvaluateFileExists() {
    // Set up the FileCheckTrigger for an existing file
    FileCheckTrigger trigger = new FileCheckTrigger(TestingDirectory, "existingFile.txt");

    // Create the file for testing
    File file = new File(TestingDirectory, "existingFile.txt");
    try {
        file.createNewFile();
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Evaluate the trigger
    assertTrue(trigger.evaluate());
}

    @Test
    public void testEvaluateFileDoesNotExist() {
        // Set up the FileCheckTrigger for a non-existing file
        FileCheckTrigger trigger = new FileCheckTrigger(TestingDirectory, "nonExistingFile.txt");

        // Ensure the file does not exist

        // Evaluate the trigger
        assertFalse(trigger.evaluate());
    }

    @Test
    public void testToString() {
        // Set up the FileCheckTrigger for a specific file
        FileCheckTrigger trigger = new FileCheckTrigger(TestingDirectory, "example.txt");

        // Check the toString representation
        assertEquals("File: example.txt in directory "+ TestingDirectory, trigger.toString());
    }

    @Test
    public void testGetType() {
        // Set up the FileCheckTrigger
        FileCheckTrigger trigger = new FileCheckTrigger(TestingDirectory, "example.txt");

        // Check the trigger type
        assertEquals("File Existance Verification", trigger.getType());
    }

    @Test
    public void testGetToCSV() {
        // Set up the FileCheckTrigger for a specific file
        FileCheckTrigger trigger = new FileCheckTrigger(TestingDirectory, "example.txt");

        // Check the CSV representation
        assertEquals("example.txt "+TestingDirectory, trigger.getToCSV());
    }

 
}
