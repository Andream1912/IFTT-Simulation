package progettose.triggerPackage;

import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;

public class FileCheckTest {

    // Change this variable for test dynamically
    private final String TestingDirectory = System.getProperty("user.dir");
    private FileCheckTrigger trigger;

    @Before
    public void setUp() {
        trigger = new FileCheckTrigger(TestingDirectory, "existingFile.txt");
    }

    @Test
    public void testEvaluateFileExists() {
        // Create the file for testing
        File file = new File(TestingDirectory, "existingFile.txt");
        trigger.evaluate();
        assertFalse(trigger.returnEvaluation());
        try {
            file.createNewFile();

        } catch (IOException e) {
            System.out.println("Problem test Evaluate File exists");
        }

        // Evaluate the trigger
        trigger.evaluate();
        assertTrue(trigger.returnEvaluation());
    }

    @After
    public void removeTempFile() throws IOException {
        Files.delete(Paths.get(TestingDirectory, "existingFile.txt"));
    }
}
