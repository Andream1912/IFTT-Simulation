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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import progettose.actionPackage.Action;
import progettose.actionPackage.ActionCreator;
import progettose.actionPackage.CopyFileActionCreator;
import progettose.actionPackage.DeleteFileActionCreator;
import progettose.actionPackage.ExecuteProgramActionCreator;
import progettose.actionPackage.FileAppenderActionCreator;
import progettose.actionPackage.MoveFileActionCreator;
import progettose.actionPackage.PlayAudioActionCreator;
import progettose.actionPackage.ShowMessageActionCreator;
import progettose.triggerPackage.DateTriggerCreator;
import progettose.triggerPackage.DayOfMonthTriggerCreator;
import progettose.triggerPackage.DayOfWeekTriggerCreator;
import progettose.triggerPackage.ExecuteProgramTriggerCreator;
import progettose.triggerPackage.FileCheckTriggerCreator;
import progettose.triggerPackage.FileSizeCheckerTriggerCreator;
import progettose.triggerPackage.TimeTriggerCreator;
import progettose.triggerPackage.Trigger;
import progettose.triggerPackage.TriggerCreator;

public class RuleManagerProxy implements RuleManager {

    private ConcreteRuleManager concrRM;
    private static RuleManagerProxy uniqueInstance;
    private final String directoryProject = System.getProperty("user.dir") + "/rules.csv";
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private int i = 0;

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

    public static RuleManagerProxy getInstance() {
        //Singleton pattern to Rulemanager
        if (uniqueInstance == null) {
            uniqueInstance = new RuleManagerProxy();
        }
        return uniqueInstance;
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

    @Override
    public void activateRule(Rule r) {
        this.concrRM.activateRule(r);
        try {
            this.storeToFile();
        } catch (IOException ex) {
            Logger.getLogger(RuleManagerProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deactivateRule(Rule r) {
        this.concrRM.deactivateRule(r);
        try {
            this.storeToFile();
        } catch (IOException ex) {
            Logger.getLogger(RuleManagerProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void storeToFile() throws IOException {

        // Writes rules from the ConcreteRuleManager to a CSV file.
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(directoryProject, false))) {
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

    private Trigger checkTrigger(String s, String column[]) {
        // Creates a Trigger based on the provided trigger type and data.
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
            default:
                System.out.println("Not valid Action");
                return null;
        }
    }

    private void loadFromFile() throws IOException {
        // Reads rules from a CSV file and adds them to the ConcreteRuleManager.
        try ( BufferedReader reader = new BufferedReader(new FileReader(directoryProject))) {
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
                    } else {
                        r = new SleepingTimeRule(ruleName, action, trigger, Integer.parseInt(column[i++]), Integer.parseInt(column[i++]), Integer.parseInt(column[i++]));
                        ((SleepingTimeRule) r).setLastTimeFired(LocalDateTime.parse(column[i++]));
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

    public void periodicCheck(TableView tb) {
        //Scheduler check the rule firing
        scheduler.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {
                for (Rule r : this.concrRM.getRules()) {
                    if (r.evaluateTrigger()) {
                        this.fireRule(r);
                    }
                }
                tb.refresh();
            });
        }, 0, 3, TimeUnit.SECONDS);
    }

    public void fireRule(Rule r) {
        r.getAction().execute();
        try {
            this.storeToFile();
        } catch (IOException ex) {
            Logger.getLogger(RuleManagerProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void shutdownScheduler() {
        //Use it everytime a scheduler is created
        scheduler.shutdown();
    }
}
