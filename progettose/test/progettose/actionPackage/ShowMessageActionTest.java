/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettose.actionPackage;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author smuel
 */
public class ShowMessageActionTest {
    
    //ShowMessageAction testing
    
    private ShowMessageAction action;

    @Test
    public void testGetMessage() {
        String message = "Test message";
        action = new ShowMessageAction(message);

        assertEquals(message, action.getMessage());
    }

    @Test
    public void testSetMessage() {
        String initialMessage = "Initial message";
        action = new ShowMessageAction(initialMessage);

        String newMessage = "New message";
        action.setMessage(newMessage);

        assertEquals(newMessage, action.getMessage());
    }

    //execute() tested via UI application
    
}
