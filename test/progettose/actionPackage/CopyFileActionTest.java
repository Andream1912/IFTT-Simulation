package progettose.actionPackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class CopyFileActionTest {

    //CopyFileAction testing
    //Creating file and panel for testing
    private File tempFile;
    private final Path copyPath = Paths.get("test/Path");

    @Before
    public void setUp() throws IOException {
        tempFile = File.createTempFile("test", ".txt");
    }

    //Deleting file after testing
    @After
    public void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }
    
    //Test for the initialization of CopyFileAction
    @Test
    public void testCopyFileActionInitialization() {
        CopyFileAction copyFile = new CopyFileAction(tempFile.toPath(), copyPath);
        assertNotNull(copyFile);
        assertEquals(tempFile.toPath(), copyFile.getFilePath());
        assertEquals(copyPath, copyFile.getCopyPath());
    }

    //Test for CopyFileActionCreator 
    @Test
    public void testCopyFileActionCreator() {
        CopyFileActionCreator copyFileAC = new CopyFileActionCreator(tempFile.toPath(), copyPath);
        Action copyFile = copyFileAC.createAction();
        assertNotNull(copyFile);
        assertTrue(copyFile instanceof CopyFileAction);
        assertEquals(tempFile.toPath(), ((CopyFileAction) copyFile).getFilePath());
        assertEquals(copyPath, ((CopyFileAction) copyFile).getCopyPath());
    }

    //Test for getters
    @Test
    public void testGetFilePath() {
        CopyFileAction copyFile = new CopyFileAction(tempFile.toPath(), copyPath);
        assertEquals(tempFile.toPath(), copyFile.getFilePath());
    }

    @Test
    public void testGetCopyPath() {
        CopyFileAction copyFile = new CopyFileAction(tempFile.toPath(), copyPath);
        assertEquals(copyPath, copyFile.getCopyPath());
    }

    //Test CopyFileAction with null or empty paths
    @Test
    public void testCopyFileActionNullOrEmptyPath() {
        CopyFileAction copyFile = new CopyFileAction(null, null);
        assertNotNull(copyFile);
        assertTrue(copyFile instanceof CopyFileAction);
    }
}
