package progettose.rulePackage;

import static org.junit.Assert.*;
import org.junit.Test;
import progettose.Rule;
import progettose.actionPackage.Action;
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
}
