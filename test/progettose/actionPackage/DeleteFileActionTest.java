package progettose.actionPackage;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeleteFileActionTest {
    
    //DeleteFileAction testing
    
    //Creating action and attributes for testing
    private Path filePath = Paths.get("Delete/file/directory");
    private DeleteFileAction action = new DeleteFileAction(filePath);
    
    //Test for the initialization of DeleteFileAction
    @Test
    public void testDeleteFileActionInitialization(){
        DeleteFileAction deleteFile = new DeleteFileAction(filePath);
        assertNotNull(deleteFile);
        assertEquals(filePath, deleteFile.getFilePath());
    }
    
    //Test for DeleteFileActionCreator
    @Test
    public void testDeleteFileActionCreator(){
        DeleteFileActionCreator deleteFileAC = new DeleteFileActionCreator(filePath);
        Action deleteFile = deleteFileAC.createAction();
        assertNotNull(deleteFile);
        assertTrue(deleteFile instanceof DeleteFileAction);
        assertEquals(filePath, ((DeleteFileAction) deleteFile).getFilePath());
    }
    
    //Test for getter
    @Test
    public void testGetFilePath(){
        assertEquals(filePath, action.getFilePath());
    }
    
    //Test DeleteFileAction with null or empty paths
    @Test
    public void testDeleteFileActionNullOrEmptyPath(){
        DeleteFileAction deleteFile = new DeleteFileAction(null);
        assertNotNull(deleteFile);
        assertTrue(deleteFile instanceof DeleteFileAction);
    }
    
}
