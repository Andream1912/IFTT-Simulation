package progettose;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import progettose.actionPackage.Action;
import progettose.actionPackage.ActionCreator;
import progettose.actionPackage.PlayAudioActionCreator;
import progettose.actionPackage.ShowMessageActionCreator;
import progettose.triggerPackage.TimeTriggerCreator;
import progettose.triggerPackage.Trigger;
import progettose.triggerPackage.TriggerCreator;

public class RuleManagerProxy implements RuleManager {

    private ConcreteRuleManager concrRM;
    private static RuleManagerProxy uniqueInstance;
    private final String directoryProject = System.getProperty("user.dir") + "/rules.csv";
    private int i = 0;

    public RuleManagerProxy(ConcreteRuleManager concrRM) {
        // Constructor that initializes the ConcreteRuleManager and loads rules from a file.
        this.concrRM = concrRM;
        try {
            File file = new File(directoryProject);
            if (!file.exists()) {
                file.createNewFile();
            }
            this.loadFromFile();

        } catch (IOException ex) {
            Logger.getLogger(RuleManagerProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addRule(Rule r) {
        // Adds a rule to the ConcreteRuleManager and stores rules to a file.
        this.concrRM.addRule(r);
        try {
            this.storeToFile();
        } catch (IOException ex) {
            Logger.getLogger(RuleManagerProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeRule(Rule r) {
        // Removes a rule from the ConcreteRuleManager and stores rules to a file.
        this.concrRM.removeRule(r);
        try {
            this.storeToFile();
        } catch (IOException ex) {
            Logger.getLogger(RuleManagerProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<Rule> getRules() {
        // Returns the list of rules from the ConcreteRuleManager.
        return this.concrRM.getRules();
    }

    private void storeToFile() throws IOException {

        // Writes rules from the ConcreteRuleManager to a CSV file.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directoryProject, false))) {
            for (Rule r : this.concrRM.getRules()) {
                writer.write(r.getName() + ";" + r.getTrigger().getType() + ";" + r.getTrigger().getToCSV() + ";" + r.getAction().getType() + ";" + r.getAction().getToCSV() + ";true");
                writer.newLine();
            }
            writer.close();
        }
    }

    private Trigger checkTrigger(String s, String column[]) {
        // Creates a Trigger based on the provided trigger type and data.
        switch (s) {
            case "Time":
                TriggerCreator timeTC = new TimeTriggerCreator(LocalTime.parse(column[i++] + ":00"));
                return timeTC.createTrigger();
            default:
                System.out.println("Not valid Trigger");
                return null;
        }
    }

    private Action checkAction(String s, String column[]) {
        // Creates an Action based on the provided action type and data.

        switch (s) {
            case "Play Audio":
                ActionCreator playAudioAC = new PlayAudioActionCreator(Paths.get(column[i++]));
                return playAudioAC.createAction();
            case "Show Message":
                ActionCreator showMessageAC = new ShowMessageActionCreator(column[i++]);
                return showMessageAC.createAction();
            default:
                System.out.println("Not valid Action");
                return null;
        }
    }

    private void loadFromFile() throws IOException {

        // Reads rules from a CSV file and adds them to the ConcreteRuleManager.
        try (BufferedReader reader = new BufferedReader(new FileReader(directoryProject))) {
            String s;
            String[] column;
            while ((s = reader.readLine()) != null) {

                i = 0;
                column = s.split(";");
                if (i < column.length) {
                    String ruleName = column[i++];
                    String nameTrigger = column[i++];
                    Trigger trigger = checkTrigger(nameTrigger, column);
                    String nameAction = column[i++];
                    Action action = checkAction(nameAction, column);
                    this.concrRM.addRule(new Rule(ruleName, action, trigger));
                } else {
                    System.out.println("Error in reading the line");
                }
            }
            reader.close();
        }
    }

}
