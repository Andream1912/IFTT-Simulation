package progettose.triggerPackage;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExecuteProgramTriggerTest {

    //ExecuteProgramtrigger testing
    //Attributes for Trigger creation
    private final List<String> commandList = new ArrayList<>();
    private final int userValue = 0;

    //Test for initialization of ExecuteProgramTrigger
    @Test
    public void testExecuteProgramTriggerInitialization() {
        ExecuteProgramTrigger execProg = new ExecuteProgramTrigger(commandList, userValue);
        assertNotNull(execProg);
        assertEquals(commandList, execProg.getCommandList());
        assertEquals(userValue, execProg.getUserValue());
    }

    //Test for initialization with ExecuteProgramTriggerCreator
    @Test
    public void testExecuteProgramTriggerCreator() {
        ExecuteProgramTriggerCreator execProgTC = new ExecuteProgramTriggerCreator(commandList, userValue);
        Trigger execProg = execProgTC.createTrigger();
        assertNotNull(execProg);
        assertTrue(execProg instanceof ExecuteProgramTrigger);
        assertEquals(commandList, ((ExecuteProgramTrigger) execProg).getCommandList());
        assertEquals(userValue, ((ExecuteProgramTrigger) execProg).getUserValue());
    }

    //Test for evaluate function
    @Test
    public void testExecuteProgramTriggerEvaluate() {
        commandList.add("Path/to/file.exe");
        commandList.add("Command1");
        commandList.add("Command2");
        ExecuteProgramTrigger execProg = new ExecuteProgramTrigger(commandList, userValue);
        assertFalse(execProg.evaluate());
    }

}
