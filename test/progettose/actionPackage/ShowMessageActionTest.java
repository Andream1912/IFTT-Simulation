package progettose.actionPackage;

import org.junit.Test;
import static org.junit.Assert.*;

public class ShowMessageActionTest {
    
    //ShowMessageAction testing
    
    private ShowMessageAction action;

    //Test for getter
    @Test
    public void testGetMessage() {
        String message = "Test message";
        action = new ShowMessageAction(message);
        action.execute();
        assertEquals(message, action.getMessage());
    }

    //Test for the initialization of ShowMessageAction
    @Test
    public void testShowMessageActionInitialization(){
        String message = "test";
        ShowMessageAction showMessageAction = new ShowMessageAction(message);
        assertNotNull(showMessageAction);
        assertEquals(message, showMessageAction.getMessage());
        
    }
    
    //Test for the initialization of ShowMessageAction with ShowMessageAtionCreator
    @Test
    public void testShowMessageActionCreator(){
        String message = "test";
        ShowMessageActionCreator showMessageActionCreator = new ShowMessageActionCreator(message);
        Action showMessageAction = showMessageActionCreator.createAction();
        assertNotNull(showMessageAction);
        assertTrue(showMessageAction instanceof ShowMessageAction);
        assertEquals(message, ((ShowMessageAction)showMessageAction).getMessage());
    }
}
