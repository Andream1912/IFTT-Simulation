package progettose.actionPackage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class DeleteFileActionTest {

    //DeleteFileAction testing
    //Creating action and attributes for testing
    private File tempFile;

    //Creating temporary file for testing 
    @Before
    public void setUp() throws IOException {
        tempFile = File.createTempFile("test", ".txt");
    }

    //Deleting temporary file after testing
    @After
    public void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    //Test for the initialization of DeleteFileAction
    @Test
    public void testDeleteFileActionInitialization() {
        DeleteFileAction deleteFile = new DeleteFileAction(tempFile.toPath());
        assertNotNull(deleteFile);
        assertEquals(tempFile.toPath(), deleteFile.getFilePath());
    }

    //Test for DeleteFileActionCreator
    @Test
    public void testDeleteFileActionCreator() {
        DeleteFileActionCreator deleteFileAC = new DeleteFileActionCreator(tempFile.toPath());
        Action deleteFile = deleteFileAC.createAction();
        assertNotNull(deleteFile);
        assertTrue(deleteFile instanceof DeleteFileAction);
        assertEquals(tempFile.toPath(), ((DeleteFileAction) deleteFile).getFilePath());
    }

    //Test for getter
    @Test
    public void testGetFilePath() {
        DeleteFileAction deleteFile = new DeleteFileAction(tempFile.toPath());
        assertEquals(tempFile.toPath(), deleteFile.getFilePath());
    }

    //Test DeleteFileAction with null or empty paths
    @Test
    public void testDeleteFileActionNullOrEmptyPath() {
        DeleteFileAction deleteFile = new DeleteFileAction(null);
        assertNotNull(deleteFile);
        assertTrue(deleteFile instanceof DeleteFileAction);
    }

    //Test for the execute method of DeleteFileAction
    @Test
    public void testDeleteFileActionExecute() {
        try {
            DeleteFileAction deleteFile = new DeleteFileAction(tempFile.toPath());

            //Wait for the execution of the action
            CompletableFuture<Void> future = new CompletableFuture<>();

            Platform.runLater(() -> {
                deleteFile.execute();
                future.complete(null);
            });

            //Waits 5 seconds for the execute to complete
            future.get(5, TimeUnit.SECONDS);
            
            //Check if file has been deleted
            assertTrue(!tempFile.exists());
            
        } catch(Exception e){
            System.out.println("Error while testing testDeleteFileActionExecute " + e.getStackTrace());
        }

    }

}
