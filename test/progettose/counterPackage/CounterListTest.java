package progettose.counterPackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class CounterListTest {
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

    @Test
    public void testSaveAndLoadFromFile() {
        // Creates an instance of Counter for testing
        CounterList counter = new CounterList();

        // Performs some operations
        counter.increment("A");
        counter.addValue("B", 5);
        counter.setValue("C", 10);

        // Creates a new instance of Counter to simulate a fresh application start
        CounterList newCounter = new CounterList();

        // Checks that the data has been correctly loaded, and the operations have taken place
        assertEquals(1, newCounter.getCount("A"));
        assertEquals(5, newCounter.getCount("B"));
        assertEquals(10, newCounter.getCount("C"));
    }

    @Test
    public void testIncrement() {
        CounterList counter = new CounterList();

        // Performs some increment operations
        counter.increment("Key1");
        counter.increment("Key1");
        counter.increment("Key2");

        // Creates a new instance of Counter to simulate a fresh application start
        CounterList newCounter = new CounterList();

        // Checks that the data has been correctly loaded, and the increments have occurred
        assertEquals(2, newCounter.getCount("Key1"));
        assertEquals(1, newCounter.getCount("Key2"));
        assertEquals(0, newCounter.getCount("Key3")); // Key not present, should be 0
    }

    @Test
    public void testAddValueAndSetValue() {
        CounterList counter = new CounterList(); // Performs some add and set operations
        counter.addValue("A", 5);
        counter.setValue("B", 10);
        counter.setValue("C", 15);

        // Creates a new instance of Counter to simulate a fresh application start
        CounterList newCounter = new CounterList();

        // Checks that the data has been correctly loaded, and the values added/set are correct
        assertEquals(5, newCounter.getCount("A"));
        assertEquals(10, newCounter.getCount("B"));
        assertEquals(15, newCounter.getCount("C"));
    }

    @AfterClass
    public static void restoreOriginalFile() throws IOException {
        // Restores the original file at the end of all tests
        Files.copy(testFileBackup.toPath(), testFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        // Deletes the backup file
        Files.delete(testFileBackup.toPath());
    }
}
