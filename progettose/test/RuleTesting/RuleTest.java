package RuleTesting;

import static org.junit.Assert.*;
import org.junit.Test;
import progettose.Action;
import progettose.Rule;
import progettose.triggerPackage.Trigger;

public class RuleTest {

    @Test
    public void testRuleCreation() {
        Trigger trigger = null;
        Action action = null;
        Rule rule = new Rule("Rule1", action, trigger);
        
        //Testing creation
        assertEquals("Rule1", rule.getName());
        assertEquals(action, rule.getAction());
        assertEquals(trigger, rule.getTrigger());
    }

    @Test
    public void testSettersAndGetters() {
        Rule rule = new Rule("Rule2", null, null);
        
        //Testing getters
        assertEquals("Rule2", rule.getName());
        assertEquals(null, rule.getAction());
        assertEquals(null, rule.getTrigger());

        // Testing setters
        rule.setName("NewRule");
        assertEquals("NewRule", rule.getName());
        
        Action newAction = null;
        rule.setAction(newAction);
        assertEquals(newAction, rule.getAction());

        Trigger newTrigger = null;
        rule.setTrigger(newTrigger);
        assertEquals(newTrigger, rule.getTrigger());
    }

    // Add tests for every needs
}