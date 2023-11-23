package progettose.rulePackage;

import java.util.concurrent.TimeUnit;
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

     @Test
    public void testFireRuleWhenTriggerIsFired() {
        ActionMock actionMock = new ActionMock();
        TriggerMock triggerMock = new TriggerMock();
        Rule rule = new Rule("TestRule", actionMock, triggerMock);

        triggerMock.setEvaluateResult(true);

        // Attesa per 5 secondi per consentire al task periodico di eseguire il controllo
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(actionMock.isExecuted());
        Rule.shutdownScheduler();
    }

    @Test
    public void testFireRuleWhenTriggerIsNotFired() {
        ActionMock actionMock = new ActionMock();
        TriggerMock triggerMock = new TriggerMock();
        Rule rule = new Rule("TestRule", actionMock, triggerMock);

        triggerMock.setEvaluateResult(false);

        // Attesa per 5 secondi per consentire al task periodico di eseguire il controllo
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertFalse(actionMock.isExecuted());
        Rule.shutdownScheduler();
    }


     // Mock class per Action
    private static class ActionMock implements Action {
        private boolean executed = true;

        @Override
        public void execute() {
            executed = true;
        }

        public boolean isExecuted() {
            return executed;
        }
    }

    // Mock class per Trigger
    private static class TriggerMock implements Trigger {
        private boolean evaluateResult = true;

        @Override
        public boolean evaluate() {
            return evaluateResult;
        }

        public void setEvaluateResult(boolean result) {
            evaluateResult = result;
        }
    }
    // Add tests for every needs
}