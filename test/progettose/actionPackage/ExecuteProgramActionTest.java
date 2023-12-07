package progettose.actionPackage;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExecuteProgramActionTest {

    //ExecuteProgramAction testing
    //Creating action and attributes for testing
    private final List<String> commandList = new ArrayList<>();
    private final ExecuteProgramAction action = new ExecuteProgramAction(commandList);

    //Test for initialization of ExecuteProgramAction
    @Test
    public void testExecuteProgramActionInitialization() {
        ExecuteProgramAction execProg = new ExecuteProgramAction(commandList);
        assertNotNull(execProg);
        assertEquals(commandList, execProg.getCommandList());
    }

    //Test for ExecuteProgramActionCreator
    @Test
    public void testExecuteProgramActionCreator() {
        ExecuteProgramActionCreator execProgAC = new ExecuteProgramActionCreator(commandList);
        Action execProg = execProgAC.createAction();
        assertNotNull(execProg);
        assertTrue(execProg instanceof ExecuteProgramAction);
        assertEquals(commandList, ((ExecuteProgramAction) execProg).getCommandList());
    }

    //Test for getter
    @Test
    public void testGetCommandList() {
        assertEquals(commandList, action.getCommandList());
    }

    //Test for null list of arguments
    @Test
    public void testExecuteProgramActionNullOrEmptyList() {
        ExecuteProgramAction execProg = new ExecuteProgramAction(null);
        assertNotNull(execProg);
        assertTrue(execProg instanceof ExecuteProgramAction);
    }
}
