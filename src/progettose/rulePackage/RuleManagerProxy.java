package progettose.rulePackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import progettose.actionPackage.Action;
import progettose.actionPackage.ActionCreator;
import progettose.actionPackage.AddCounterToCounterActionCreator;
import progettose.actionPackage.AddValueToCounterActionCreator;
import progettose.actionPackage.CopyFileActionCreator;
import progettose.actionPackage.DeleteFileActionCreator;
import progettose.actionPackage.ExecuteProgramActionCreator;
import progettose.actionPackage.FileAppenderActionCreator;
import progettose.actionPackage.MoveFileActionCreator;
import progettose.actionPackage.SetCounterActionCreator;
import progettose.actionPackage.ShowMessageActionCreator;
import progettose.counterPackage.CounterList;
import progettose.triggerPackage.CheckCounterToCounterTriggerCreator;
import progettose.triggerPackage.CheckValueToCounterTriggerCreator;
import progettose.actionPackage.PlayAudioActionCreator;
import progettose.triggerPackage.CompositeTrigger;
import progettose.triggerPackage.DateTriggerCreator;
import progettose.triggerPackage.DayOfMonthTriggerCreator;
import progettose.triggerPackage.DayOfWeekTriggerCreator;
import progettose.triggerPackage.ExecuteProgramTriggerCreator;
import progettose.triggerPackage.FileCheckTriggerCreator;
import progettose.triggerPackage.FileSizeCheckerTriggerCreator;
import progettose.triggerPackage.TimeTriggerCreator;
import progettose.triggerPackage.Trigger;
import progettose.triggerPackage.TriggerCreator;
// Manages rules using a proxy pattern and includes functionalities to read/write rules to a file, check triggers periodically, and execute rule actions.

public class RuleManagerProxy implements RuleManager {

    // Concrete RuleManager instance.
    private ConcreteRuleManager concrRM;
    private static RuleManagerProxy uniqueInstance; // Singleton instance.
    private final String directoryProject = System.getProperty("user.dir") + "/rules.csv"; // File path for storing rules.
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private int i = 0;

    // Private constructor to enforce the singleton pattern.
    private RuleManagerProxy() {
        // Constructor that initializes the ConcreteRuleManager and loads rules from a file.
        this.concrRM = new ConcreteRuleManager();
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

    // Singleton pattern to get an instance of RuleManagerProxy.
    public static RuleManagerProxy getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new RuleManagerProxy();
        }
        return uniqueInstance;
    }

    // Adds a rule to the ConcreteRuleManager and stores rules to a file.
    @Override
    public void addRule(Rule r) {
        this.concrRM.addRule(r);
        try {
            this.storeToFile();
        } catch (IOException ex) {
            Logger.getLogger(RuleManagerProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Removes a rule from the ConcreteRuleManager and stores rules to a file.
    @Override
    public void removeRule(Rule r) {
        this.concrRM.removeRule(r);
        try {
            this.storeToFile();
        } catch (IOException ex) {
            Logger.getLogger(RuleManagerProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Returns the list of rules from the ConcreteRuleManager.
    @Override
    public ObservableList<Rule> getRules() {
        return this.concrRM.getRules();
    }

    // Activates a rule and stores rules to a file.
    @Override
    public void activateRule(Rule r) {
        this.concrRM.activateRule(r);
        try {
            this.storeToFile();
        } catch (IOException ex) {
            Logger.getLogger(RuleManagerProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Deactivates a rule and stores rules to a file.
    @Override
    public void deactivateRule(Rule r) {
        this.concrRM.deactivateRule(r);
        try {
            this.storeToFile();
        } catch (IOException ex) {
            Logger.getLogger(RuleManagerProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Writes rules from the ConcreteRuleManager to a CSV file.
    private void storeToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directoryProject, false))) {
            for (Rule r : this.getRules()) {
                writer.write(r.getClass().getSimpleName() + ";" + r.getName() + ";" + r.getTrigger().getType() + ";" + r.getTrigger().getToCSV() + ";" + r.getAction().getType() + ";" + r.getAction().getToCSV());
                if (r instanceof SleepingTimeRule) {
                    writer.write(";" + ((SleepingTimeRule) r).getNumDays() + ";" + ((SleepingTimeRule) r).getNumHours() + ";" + ((SleepingTimeRule) r).getNumMinutes() + ";" + ((SleepingTimeRule) r).getLastTimeFired());
                }
                writer.write(";" + r.isActive());
                writer.newLine();
            }
            writer.close();
        }
    }

    // Creates a Trigger based on the provided trigger type and data.
    private Trigger checkTrigger(String s, String column[]) {
        switch (s) {
            case "Time":
                TriggerCreator timeTC = new TimeTriggerCreator(LocalTime.parse(column[i++]));
                return timeTC.createTrigger();
            case "Day of Week":
                TriggerCreator dayOfWeekTC = new DayOfWeekTriggerCreator(column[i++]);
                return dayOfWeekTC.createTrigger();
            case "Day of Month":
                TriggerCreator dayOfMonthTC = new DayOfMonthTriggerCreator(Integer.parseInt(column[i++]));
                return dayOfMonthTC.createTrigger();
            case "Date":
                TriggerCreator dateTC = new DateTriggerCreator(LocalDate.parse(column[i++]));
                return dateTC.createTrigger();
            case "File Existance Verification":
                TriggerCreator fileCheckTC = new FileCheckTriggerCreator(column[i++], column[i++]);
                return fileCheckTC.createTrigger();
            case "File Dimension Verification":
                TriggerCreator fileDimensionTC = new FileSizeCheckerTriggerCreator(Paths.get(column[i++]), Long.parseLong(column[i++]), column[i++]);
                return fileDimensionTC.createTrigger();
            case "Program Exit Status Verification":
                int n = Integer.parseInt(column[i++]);
                List<String> execProgList = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    execProgList.add(column[i++]);
                }
                TriggerCreator executeProgramAC = new ExecuteProgramTriggerCreator(execProgList, Integer.parseInt(column[i++]));
                return executeProgramAC.createTrigger();
            case "Compare Counter to Value":
                TriggerCreator checkValueCountTC = new CheckValueToCounterTriggerCreator(column[i++], column[i++], Integer.parseInt(column[i++]));
                return checkValueCountTC.createTrigger();
            case "Compare Counter to Counter":
                TriggerCreator checkCountToCountTC = new CheckCounterToCounterTriggerCreator(column[i++], column[i++], column[i++]);
                return checkCountToCountTC.createTrigger();
            default:
                if (s.startsWith("Composite")) {
                    Trigger t1 = checkTrigger(column[i++], column);
                    String op = column[i++];
                    if (op.equals("NOT")) {
                        return new CompositeTrigger(s.substring(12), t1, null, op);
                    }
                    Trigger t2 = checkTrigger(column[i++], column);
                    return new CompositeTrigger(s.substring(12), t1, t2, op);

                } else {
                    System.out.println("Not a valid Trigger");
                    return null;
                }
        }
    }

    // Creates an Action based on the provided action type and data.
    private Action checkAction(String s, String column[]) {
        switch (s) {
            case "Play Audio":
                ActionCreator playAudioAC = new PlayAudioActionCreator(Paths.get(column[i++]));
                return playAudioAC.createAction();
            case "Show Message":
                ActionCreator showMessageAC = new ShowMessageActionCreator(column[i++]);
                return showMessageAC.createAction();
            case "Copy File":
                ActionCreator copyFileAC = new CopyFileActionCreator(Paths.get(column[i++]), Paths.get(column[i++]));
                return copyFileAC.createAction();
            case "Move File":
                ActionCreator moveFileAC = new MoveFileActionCreator(Paths.get(column[i++]), Paths.get(column[i++]));
                return moveFileAC.createAction();
            case "Delete File":
                ActionCreator deleteFileAC = new DeleteFileActionCreator(Paths.get(column[i++]));
                return deleteFileAC.createAction();
            case "Execute Program":
                int n = Integer.parseInt(column[i++]);
                List<String> execProgList = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    execProgList.add(column[i++]);
                }
                ActionCreator executeProgramAC = new ExecuteProgramActionCreator(execProgList);
                return executeProgramAC.createAction();
            case "Append String to Textfile":
                ActionCreator appendFileAC = new FileAppenderActionCreator(Paths.get(column[i++]), column[i++]);
                return appendFileAC.createAction();
            case "Set Value of Counter":
                ActionCreator setCountAC = new SetCounterActionCreator(column[i++], Integer.parseInt(column[i++]));
                return setCountAC.createAction();
            case "Add Value to Counter":
                ActionCreator addValueCountAC = new AddValueToCounterActionCreator(column[i++], Integer.parseInt(column[i++]));
                return addValueCountAC.createAction();
            case "Add Value of Counter to Counter":
                ActionCreator addCountToCountAC = new AddCounterToCounterActionCreator(column[i++], column[i++]);
                return addCountToCountAC.createAction();
            default:
                System.out.println("Not a valid Action");
                return null;
        }
    }

    // Reads rules from a CSV file and adds them to the ConcreteRuleManager.
    private void loadFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(directoryProject))) {
            String s;
            Rule r;
            String[] column;
            while ((s = reader.readLine()) != null) {
                i = 0;
                column = s.split(";");
                if (i < column.length) {
                    String ruleType = column[i++];
                    String ruleName = column[i++];
                    String nameTrigger = column[i++];
                    Trigger trigger = checkTrigger(nameTrigger, column);
                    String nameAction = column[i++];
                    Action action = checkAction(nameAction, column);
                    if (ruleType.equals("FireOnceRule")) {
                        r = new FireOnceRule(ruleName, action, trigger);
                    } else if (ruleType.equals("SleepingTimeRule")) {
                        r = new SleepingTimeRule(ruleName, action, trigger, Integer.parseInt(column[i++]), Integer.parseInt(column[i++]), Integer.parseInt(column[i++]));
                        ((SleepingTimeRule) r).setLastTimeFired(LocalDateTime.parse(column[i++]));
                    } else {
                        r = new Rule(ruleName, action, trigger);
                    }
                    r.setState(Boolean.parseBoolean(column[i++]));
                    this.concrRM.addRule(r);

                } else {
                    System.out.println("Error in reading the line");
                }
            }
            reader.close();
        }
    }

    public void periodicCheck(TableView tb, TableView cTB, CounterList counterTB) {
        //Scheduler check the rule firing
        scheduler.scheduleAtFixedRate(() -> {
            for (Rule r : this.concrRM.getRules()) {
                if (r.evaluateTrigger()) {
                    Platform.runLater(() -> {
                        this.fireRule(r);
                        counterTB.loadFromFile();
                        cTB.refresh();
                    });
                }

            }
            tb.refresh();
        }, 0, 3, TimeUnit.SECONDS);
    }

    // Executes the action of a rule and stores rules to a file.
    public void fireRule(Rule r) {
        r.getAction().execute();
        try {
            this.storeToFile();
        } catch (IOException ex) {
            Logger.getLogger(RuleManagerProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Shuts down the scheduler.
    public static void shutdownScheduler() {
        scheduler.shutdown();
    }
}
