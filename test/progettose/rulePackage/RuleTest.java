package progettose.rulePackage;

import java.time.LocalDateTime;
import java.time.LocalTime;
import static org.junit.Assert.*;
import org.junit.Test;
import progettose.actionPackage.Action;
import progettose.actionPackage.ShowMessageActionCreator;
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
        assertTrue(rule2.evaluateTrigger());

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

    @Test
    public void testFireOnceRule() {
        Action action = new ShowMessageActionCreator("Ciao").createAction();
        Trigger trigger = new TimeTriggerCreator(LocalTime.now()).createTrigger();
        FireOnceRule fireOnceRule = new FireOnceRule("TestFireOnceRule", action, trigger);

        assertEquals("Fire Only Once", fireOnceRule.getRuleTypeDescription());

        // Trigger should return true only once
        assertTrue(fireOnceRule.evaluateTrigger());
        assertFalse(fireOnceRule.evaluateTrigger());
    }

    @Test
    public void testSleepingTimeRule() {
        Action action = new ShowMessageActionCreator("Ciao").createAction();
        Trigger trigger = new TimeTriggerCreator(LocalTime.now()).createTrigger();
        int numDays = 1;
        int numHours = 2;
        int numMinutes = 30;

        SleepingTimeRule sleepingTimeRule = new SleepingTimeRule("TestSleepingTimeRule", action, trigger, numDays, numHours, numMinutes);

        assertEquals("Fire again after\n1d, 2h, 30m", sleepingTimeRule.getRuleTypeDescription());

        // Trigger should return true only if after sleeping time
        assertTrue(sleepingTimeRule.evaluateTrigger());

        // Trigger should return false because the sleeping time is not passed
        assertFalse(sleepingTimeRule.evaluateTrigger());
        // Manually set lastTimeFired to test if the evaluateTrigger() works after the sleeping time
        sleepingTimeRule.setLastTimeFired(LocalDateTime.now().minusDays(numDays).minusHours(numHours).minusMinutes(numMinutes + 1));
        assertTrue(sleepingTimeRule.evaluateTrigger());
        assertFalse(sleepingTimeRule.isAfterSleepingTime());

    }
}
