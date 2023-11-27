package progettose.rulePackage;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import progettose.Rule;
import progettose.RuleManager;
import progettose.actionPackage.Action;
import progettose.triggerPackage.Trigger;

public class RuleManagerTest {

    private RuleManager ruleManager;
    ActionMock actionMock = new ActionMock();
    TriggerMock triggerMock = new TriggerMock();

    @Before
    public void setUp() {
        ruleManager = RuleManager.getInstance();
        //Cleare possible values in the instance
        ruleManager.getRules().clear();
        //initialize mocks
        actionMock = new ActionMock();
        triggerMock = new TriggerMock();
    }

    @Test
    public void testAddRule() {
        Rule rule = new Rule("TestRule", actionMock, triggerMock);
        ruleManager.addRule(rule);
        ObservableList<Rule> rules = ruleManager.getRules();

        //testing add rule
        assertEquals(1, rules.size());
        assertEquals("TestRule", rules.get(0).getName());
    }

    @Test
    public void testRemoveRule() {
        Rule rule = new Rule("TestRule", actionMock, triggerMock);
        ruleManager.addRule(rule);
        ObservableList<Rule> rules = ruleManager.getRules();

        //testing remove rule
        assertEquals(1, rules.size());
        ruleManager.removeRule(rule);
        assertEquals(0, rules.size());
    }

    @Test
    public void testSetAndGetRules() {
        ObservableList<Rule> rules = FXCollections.observableArrayList();
        rules.add(new Rule("Rule1", actionMock, triggerMock));
        rules.add(new Rule("Rule2", actionMock, triggerMock));

        //testing setter
        ruleManager.setRules(rules);
        //testing getter
        assertEquals(rules, ruleManager.getRules());
    }

    @Test
    public void testPeriodicCheckAndFireRule() {
        Rule rule = new Rule("TestRule", actionMock, triggerMock);

        ruleManager.addRule(rule);

        // Periodic task control
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // test action is done
        assertTrue(actionMock.isExecuted());
    }

    @After
    public void tearDown() {
        // Close of resources
        RuleManager.shutdownScheduler();
    }

    // Mock class Action
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

    // Mock class Trigger
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
}