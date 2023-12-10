package progettose.actionPackage;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShowMessageActionTest {

    //ShowMessageAction testing
    //Attributes for ShowMessageAction testing
    private String message = "Test message";
    ShowMessageAction showMessage = new ShowMessageAction(message);

    //Test for getter
    @Test
    public void testGetMessage() {
        assertEquals(message, showMessage.getMessage());
    }

    //Test for the initialization of ShowMessageAction
    @Test
    public void testShowMessageActionInitialization() {
        assertNotNull(showMessage);
        assertEquals(message, showMessage.getMessage());

    }

    //Test for the initialization of ShowMessageAction with ShowMessageAtionCreator
    @Test
    public void testShowMessageActionCreator() {
        ShowMessageActionCreator showMessageActionCreator = new ShowMessageActionCreator(message);
        Action showMessageAction = showMessageActionCreator.createAction();
        assertNotNull(showMessageAction);
        assertTrue(showMessageAction instanceof ShowMessageAction);
        assertEquals(message, ((ShowMessageAction) showMessageAction).getMessage());
    }

    //Test for execute method of ShowMessageAction
    @Test
    public void testShowMessageActionExecute() {
        try {
            //Wait for the execution of the action
            CompletableFuture<Void> future = new CompletableFuture<>();

            Platform.runLater(() -> {
                showMessage.execute();
                future.complete(null);
            });

            //Waits 5 seconds for the execute to complete
            future.get(5, TimeUnit.SECONDS);
            
        } catch(Exception e){
            System.out.println("Error while testing testShowMessageActionExecute " + e.getStackTrace());
        }

    }
}
