package progettose.actionPackage;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;


public class FileAppenderActionTest {
    
    //Testing for FileAppenderAction
    
    //Attribute for FileAppenderAction testing
    private File tempFile;

    //Creating temporary file for testing
    @Before
    public void setUp() throws IOException {
        tempFile = File.createTempFile("tempFile", ".txt");
        new JFXPanel();
    }

    //Deleting temporary file after testing
    @After
    public void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    //Testing for execute method of FileAppenderAction
    @Test
    public void execute_shouldAppendMessageToFile() {
        try {
            FileAppenderAction fileAppenderAction = new FileAppenderAction(tempFile.toPath(), "Hello, World!");

            //Wait for the execute method to end
            CompletableFuture<Void> future = new CompletableFuture<>();

            Platform.runLater(() -> {
                fileAppenderAction.execute();
                future.complete(null);
            });

            //Waits 5 seconds for execute to end then timeout
            future.get(5, TimeUnit.SECONDS);

            //Verify if message was added without problems
            BufferedReader reader = new BufferedReader(new FileReader(tempFile));
            String line = reader.readLine();
            System.out.println(line);
            reader.close();

            assertEquals("Hello, World!", line);
        } catch (Exception e) {
            fail("Error during test : " + e.getMessage());
        }
    }
}
