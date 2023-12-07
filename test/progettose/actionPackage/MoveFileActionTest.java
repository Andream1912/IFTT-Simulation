package progettose.actionPackage;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;

public class MoveFileActionTest {
    
    //MoveFileAction Testing
    
    //Creating action and attributes for testing
    private Path filePath = Paths.get("Starting/file/directory");
    private Path movePath = Paths.get("Destination/file/directory");
    private MoveFileAction action = new MoveFileAction(filePath, movePath);
    
    //Test for the initialization of MoveFileAction
    @Test
    public void testMoveFileActionInitialization(){
        MoveFileAction moveFile = new MoveFileAction(filePath, movePath);
        assertNotNull(moveFile);
        assertEquals(filePath, moveFile.getFilePath());
        assertEquals(movePath, moveFile.getMovePath());
    }
    
    //Test for MoveFileActionCreator
    @Test
    public void testMoveFileActionFileCreator(){
        MoveFileActionCreator moveFileAC = new MoveFileActionCreator(filePath, movePath);
        Action moveFile = moveFileAC.createAction();
        assertNotNull(moveFile);
        assertTrue(moveFile instanceof MoveFileAction);
        assertEquals(filePath, ((MoveFileAction)moveFile).getFilePath());
        assertEquals(movePath, ((MoveFileAction)moveFile).getMovePath());
    }
    
    //Test for getters
    @Test
    public void testGetFilePath(){
        assertEquals(filePath, action.getFilePath());
    }
    
    @Test
    public void testGetMovePath(){
        assertEquals(movePath, action.getMovePath());
    }
    
    //Test MoveFileAction with null or empty paths
    @Test
    public void testMoveFileActionNullOrEmptyPath(){
        MoveFileAction moveFile = new MoveFileAction(null, null);
        assertNotNull(moveFile);
        assertTrue(moveFile instanceof MoveFileAction);
    }
}
