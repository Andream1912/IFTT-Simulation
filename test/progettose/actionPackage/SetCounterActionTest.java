package progettose.actionPackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import progettose.counterPackage.CounterList;

public class SetCounterActionTest {

    //SetCounterAction testing
    //Attributes for SetCounterAction testing
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
    
    //Testing for initialization with SetCounterActionCreator
    @Test
    public void testSetCounterActionCreator(){
        SetCounterActionCreator setCountAC = new SetCounterActionCreator("counter", 1);
        Action setCount = setCountAC.createAction();
        assertNotNull(setCount);
        assertTrue(setCount instanceof SetCounterAction);
    }
    //Testing for execute method of SetCounterAction
    @Test
    public void testSetCounterActionExecute() {
        try {
            countList.addCounter("counter", 0);
            SetCounterAction setCount = new SetCounterAction("counter", 1);

            //Wait for execute method to end
            CompletableFuture<Void> future = new CompletableFuture<>();

            Platform.runLater(() -> {
                setCount.execute();
                future.complete(null);
            });

            //Waits 5 seconds for the execute to complete
            future.get(5, TimeUnit.SECONDS);

            //Check if cunter was set to value
            assertEquals(1, countList.getCounter("counter"));

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
