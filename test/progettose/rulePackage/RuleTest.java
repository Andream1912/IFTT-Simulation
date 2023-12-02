package progettose.rulePackage;

import java.time.LocalTime;
import static org.junit.Assert.*;
import org.junit.Test;

import progettose.RuleStateActive;
import progettose.RuleStateInactive;
import progettose.actionPackage.Action;
import progettose.actionPackage.ShowMessageAction;
import progettose.actionPackage.ShowMessageActionCreator;
import progettose.triggerPackage.TimeTrigger;
import progettose.triggerPackage.TimeTriggerCreator;
import progettose.triggerPackage.Trigger;

public class RuleTest {

    @Test
    public void testRuleCreation() {
        Trigger trigger = null;
        Action action = null;
        Rule rule = new Rule("Rule1", action, trigger);

        //Testing creation with extandable code 
        assertEquals("Rule1", rule.getName());
        assertEquals(action, rule.getAction());
        assertEquals(trigger, rule.getTrigger());
    }

    @Test
    public void testSettersAndGetters() {
        Rule rule = new Rule("Rule2", null, null);

        //Testing getters with extendable code
        assertEquals("Rule2", rule.getName());
        assertEquals(null, rule.getAction());
        assertEquals(null, rule.getTrigger());

        // Testing setters with extendable code
        rule.setName("NewRule");
        assertEquals("NewRule", rule.getName());

        Action newAction = null;
        rule.setAction(newAction);
        assertEquals(newAction, rule.getAction());

        Trigger newTrigger = null;
        rule.setTrigger(newTrigger);
        assertEquals(newTrigger, rule.getTrigger());
    }
    
    @Test
    public void ruleStateActiveCheckTrigger() {
        Rule rule1 = new Rule("TestRule", new ShowMessageActionCreator("Ciao").createAction(), new TimeTriggerCreator(LocalTime.now()).createTrigger());
        assertTrue(rule1.getState() instanceof RuleStateActive);
        assertTrue(rule1.evaluateTrigger());
        
        Rule rule2 = new Rule("TestRule", new ShowMessageActionCreator("Ciao").createAction(), new TimeTriggerCreator(LocalTime.now().minusSeconds(2)).createTrigger());
        assertTrue(rule2.getState() instanceof RuleStateActive);
        assertFalse(rule2.evaluateTrigger());
        
        Rule rule3 = new Rule("TestRule", new ShowMessageActionCreator("Ciao").createAction(), new TimeTriggerCreator(LocalTime.now().plusMinutes(5)).createTrigger());
        assertTrue(rule3.getState() instanceof RuleStateActive);
        assertFalse(rule3.evaluateTrigger());
        
        
    }

    @Test
    public void ruleStateInactiveCheckTrigger() {
        Rule rule1 = new Rule("TestRule", new ShowMessageActionCreator("Ciao").createAction(), new TimeTriggerCreator(LocalTime.now()).createTrigger());
        rule1.setState(false);
        assertTrue(rule1.getState() instanceof RuleStateInactive);
        assertFalse(rule1.evaluateTrigger()); 
        
        Rule rule2 = new Rule("TestRule", new ShowMessageActionCreator("Ciao").createAction(), new TimeTriggerCreator(LocalTime.now().minusSeconds(2)).createTrigger());
        rule2.setState(false);
        assertTrue(rule2.getState() instanceof RuleStateInactive);
        assertFalse(rule2.evaluateTrigger()); 
        
        Rule rule3 = new Rule("TestRule", new ShowMessageActionCreator("Ciao").createAction(), new TimeTriggerCreator(LocalTime.now().plusMinutes(5)).createTrigger());
        rule3.setState(false);
        assertTrue(rule3.getState() instanceof RuleStateInactive);
        assertFalse(rule3.evaluateTrigger()); 
    }
}
