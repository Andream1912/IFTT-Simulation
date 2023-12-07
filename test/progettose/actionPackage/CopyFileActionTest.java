package progettose.actionPackage;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;

public class CopyFileActionTest {
    
    //CopyFileAction testing
    
    //Creating action and attributes for testing 
    private Path filePath = Paths.get("Starting/file/directory");
    private Path copyPath = Paths.get("Destination/file/directory");
    private CopyFileAction action = new CopyFileAction(filePath, copyPath);
    
    //Test for the initialization of CopyFileAction
    @Test
    public void testCopyFileActionInitialization(){
        CopyFileAction copyFile = new CopyFileAction(filePath, copyPath);
        assertNotNull(copyFile);
        assertEquals(filePath, copyFile.getFilePath());
        assertEquals(copyPath, copyFile.getCopyPath());
    }
    
    //Test for CopyFileActionCreator 
    @Test
    public void testCopyFileActionCreator(){
        CopyFileActionCreator copyFileAC = new CopyFileActionCreator(filePath, copyPath);
        Action copyFile = copyFileAC.createAction();
        assertNotNull(copyFile);
        assertTrue(copyFile instanceof CopyFileAction);
        assertEquals(filePath, ((CopyFileAction)copyFile).getFilePath());
        assertEquals(copyPath, ((CopyFileAction)copyFile).getCopyPath());
    }
    
    //Test for getters
    @Test
    public void testGetFilePath(){
        assertEquals(filePath, action.getFilePath());
    }
    
    @Test
    public void testGetCopyPath(){
        assertEquals(copyPath, action.getCopyPath());
    }
    
    //Test CopyFileAction with null or empty paths
    @Test
    public void testCopyFileActionNullOrEmptyPath(){
        CopyFileAction copyFile = new CopyFileAction(null, null);
        assertNotNull(copyFile);
        assertTrue(copyFile instanceof CopyFileAction);
    }
    
}
