package progettose;

import progettose.rulePackage.FireOnceRule;
import progettose.rulePackage.Rule;
import progettose.rulePackage.SleepingTimeRule;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import progettose.actionPackage.Action;
import progettose.actionPackage.ActionCreator;
import progettose.actionPackage.PlayAudioActionCreator;
import progettose.actionPackage.ShowMessageActionCreator;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import progettose.actionPackage.CopyFileActionCreator;
import progettose.actionPackage.DeleteFileActionCreator;
import progettose.actionPackage.ExecuteProgramActionCreator;
import progettose.actionPackage.FileAppenderActionCreator;
import progettose.actionPackage.MoveFileActionCreator;
import progettose.triggerPackage.CompositeTrigger;
import progettose.triggerPackage.DateTriggerCreator;
import progettose.triggerPackage.DayOfMonthTriggerCreator;
import progettose.triggerPackage.DayOfWeekTriggerCreator;
import progettose.triggerPackage.ExecuteProgramTriggerCreator;
import progettose.triggerPackage.FileSizeCheckerTriggerCreator;
import progettose.triggerPackage.FileCheckTriggerCreator;
import progettose.triggerPackage.TimeTriggerCreator;
import progettose.triggerPackage.Trigger;
import progettose.triggerPackage.TriggerCreator;

public class FXMLDocumentTwoController implements Initializable {

    // Injected JavaFX elements
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField exFileTextField;
    @FXML
    private Button exFileButton;
    @FXML
    private Button execProgramButton;
    @FXML
    private TextField execProgramTextField;
    @FXML
    private ComboBox<Integer> dayOfMonthComboBox;
    @FXML
    private ComboBox<String> dayOfWeekComboBox;
    @FXML
    private ComboBox<String> hourComboBox;
    @FXML
    private ComboBox<String> minuteComboBox;
    @FXML
    private ComboBox<String> triggerComboBox;
    @FXML
    private ComboBox<String> actionComboBox;
    @FXML
    private Button saveButton;
    @FXML
    private TextField fileDimensionTextField;
    @FXML
    private Button fileDimensionButton;
    @FXML
    private Label dateLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label dayOfWeekLabel;
    @FXML
    private Label dayOfMonthLabel;
    @FXML
    private Label exFileLabel;
    @FXML
    private Label fileDimensionLabel;
    @FXML
    private Label execProgramLabel;
    @FXML
    private Button playAudioButton;
    @FXML
    private TextArea showMessageTextArea;
    @FXML
    private TextArea appendToFileTextArea;
    @FXML
    private Button appendToFileButton;
    @FXML
    private Button sourceDirectoryButton;
    @FXML
    private Button destinationDirectoryButton;
    @FXML
    private TextField moveCopyTextField;
    @FXML
    private TextField execArgumentsTextField;
    @FXML
    private Button execProgButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField deleteTextField;
    @FXML
    private Label playAudioLabel;
    @FXML
    private Label moveCopyLabel;
    @FXML
    private Label destinationDirectoryLabel;
    @FXML
    private Label showMessageLabel;
    @FXML
    private Label appendToFileLabel2;
    @FXML
    private Label sourceDirectoryLabel;
    @FXML
    private Label deleteLabel1;
    @FXML
    private Label deleteLabel2;
    @FXML
    private Label execProgLabel;
    @FXML
    private Label execArgumentsLabel;
    @FXML
    private Label appendToFileLabel1;
    @FXML
    private TextField ruleNameTextField;
    @FXML
    private Label fileAudioNameLabel;
    @FXML
    private Label selectedDestinationDirectoryLabel;
    @FXML
    private Label selectedSourceDirectoryLabel;
    @FXML
    private Label labelDimensionFile;
    @FXML
    private ComboBox<String> typeDimensionComboBox;
    @FXML
    private Label deleteFileLabel;
    @FXML
    private Label exFileDirectoryLabel;
    @FXML
    private CheckBox fireOnceCheckBox;
    @FXML
    private CheckBox fireSleepingTimeCheckBox;
    @FXML
    private Spinner<Integer> minuteSleepingTimeSpinner;
    @FXML
    private Spinner<Integer> hourSleepingTimeSpinner;
    @FXML
    private Spinner<Integer> daySleepingTimeSpinner;
    @FXML
    private Label daySleepingTimeLabel;
    @FXML
    private Label hourSleepingTimeLabel;
    @FXML
    private Label minuteSleepingTimeLabel;
    @FXML
    private Label fileToAppendLabel;
    @FXML
    private Label execProgramActionLabel;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField insertExitValueTextField;
    @FXML
    private Label insertExitValueLabel;
    @FXML
    private Label exitValueProgramLabel;
    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private Pane pane1;
    @FXML
    private DatePicker datePicker1;
    @FXML
    private TextField exFileTextField1;
    @FXML
    private Button exFileButton1;
    @FXML
    private TextField fileDimensionTextField1;
    @FXML
    private Button fileDimensionButton1;
    @FXML
    private Button execProgramButton1;
    @FXML
    private TextField execProgramTextField1;
    @FXML
    private ComboBox<Integer> dayOfMonthComboBox1;
    @FXML
    private ComboBox<String> dayOfWeekComboBox1;
    @FXML
    private ComboBox<String> hourComboBox1;
    @FXML
    private ComboBox<String> minuteComboBox1;
    @FXML
    private Label dateLabel1;
    @FXML
    private Label timeLabel1;
    @FXML
    private Label dayOfWeekLabel1;
    @FXML
    private Label dayOfMonthLabel1;
    @FXML
    private Label exFileLabel1;
    @FXML
    private Label fileDimensionLabel1;
    @FXML
    private Label execProgramLabel1;
    @FXML
    private ComboBox<String> triggerComboBox1;
    @FXML
    private Label labelDimensionFile1;
    @FXML
    private ComboBox<String> typeDimensionComboBox1;
    @FXML
    private Label exFileDirectoryLabel1;
    @FXML
    private DatePicker datePicker2;
    @FXML
    private TextField exFileTextField2;
    @FXML
    private Button exFileButton2;
    @FXML
    private TextField fileDimensionTextField2;
    @FXML
    private Button fileDimensionButton2;
    @FXML
    private Button execProgramButton2;
    @FXML
    private TextField execProgramTextField2;
    @FXML
    private ComboBox<Integer> dayOfMonthComboBox2;
    @FXML
    private ComboBox<String> dayOfWeekComboBox2;
    @FXML
    private ComboBox<String> hourComboBox2;
    @FXML
    private ComboBox<String> minuteComboBox2;
    @FXML
    private Label dateLabel2;
    @FXML
    private Label timeLabel2;
    @FXML
    private Label dayOfWeekLabel2;
    @FXML
    private Label dayOfMonthLabel2;
    @FXML
    private Label exFileLabel2;
    @FXML
    private Label fileDimensionLabel2;
    @FXML
    private Label execProgramLabel2;
    @FXML
    private ComboBox<String> triggerComboBox2;
    @FXML
    private Label secondTriggerLabel;
    @FXML
    private Label labelDimensionFile2;
    @FXML
    private ComboBox<String> typeDimensionComboBox2;
    @FXML
    private Label exFileDirectoryLabel2;
    @FXML
    private ComboBox<String> notOperandComboBox1;
    @FXML
    private Button createCompositeTriggerButton;
    @FXML
    private Button doneTriggerButton;
    @FXML
    private ComboBox<String> notOperandComboBox2;
    @FXML
    private ComboBox<String> logicalOperandComboBox;
    @FXML
    private Label logicalOperandLabel;
    @FXML
    private TextField triggerTextField;
    @FXML
    private Label compositeTriggerLabel1;
    @FXML
    private Label compositeTriggerLabel2;
    @FXML
    private TextField actionsTextField;
    @FXML
    private ComboBox<?> actionComboBox1;
    @FXML
    private TextArea showMessageTextArea1;
    @FXML
    private TextArea appendToFileTextArea1;
    @FXML
    private Button sourceDirectoryButton1;
    @FXML
    private TextField moveCopyTextField1;
    @FXML
    private TextField execArgumentsTextField1;
    @FXML
    private Button deleteButton1;
    @FXML
    private TextField deleteTextField1;
    @FXML
    private Label playAudioLabel1;
    @FXML
    private Label appendToFileLabel11;
    @FXML
    private Label moveCopyLabel1;
    @FXML
    private Label destinationDirectoryLabel1;
    @FXML
    private Label showMessageLabel1;
    @FXML
    private Label appendToFileLabel21;
    @FXML
    private Label sourceDirectoryLabel1;
    @FXML
    private Label deleteLabel11;
    @FXML
    private Label deleteLabel21;
    @FXML
    private Label execProgLabel1;
    @FXML
    private Label execArgumentsLabel1;
    @FXML
    private Button appendToFileButton1;
    @FXML
    private Button playAudioButton1;
    @FXML
    private Button destinationDirectoryButton1;
    @FXML
    private Button execProgButton1;
    @FXML
    private Label fileAudioNameLabel1;
    @FXML
    private Label selectedDestinationDirectoryLabel1;
    @FXML
    private Label selectedSourceDirectoryLabel1;
    @FXML
    private Label deleteFileLabel1;
    @FXML
    private Label fileToAppendLabel1;
    @FXML
    private Label execProgramActionLabel1;
    @FXML
    private Label compositeTriggerLabel;
    @FXML
    private Button backFromTriggerButton;
    
    private Path selectedFileForDimension;
    private Path selectedFileForDimension1;
    private Path selectedFileForDimension2;
    private FXMLDocumentController controllerOne;
    private Path selectedFilePath;
    private Path selectedSourcePath;
    private Path selectedDestinationPath;
    private Path selectedDeleteSourcePath;
    private Path selectedExFile;
    private Path selectedExFile1;
    private Path selectedExFile2;
    private Path selectedProgramTriggerFile;
    private Path selectedProgramTriggerFile1;
    private Path selectedProgramTriggerFile2;
    private Path selectedAppendFile;
    private ObservableList<String> triggerList;
    private ObservableList<String> actionList;
    private HashMap<String, Trigger> compositeTrigger;
    @FXML
    private ComboBox<?> actionComboBox2;
    @FXML
    private TextArea showMessageTextArea2;
    @FXML
    private TextArea appendToFileTextArea2;
    @FXML
    private Button sourceDirectoryButton2;
    @FXML
    private TextField moveCopyTextField2;
    @FXML
    private TextField execArgumentsTextField2;
    @FXML
    private Button deleteButton2;
    @FXML
    private TextField deleteTextField2;
    @FXML
    private Label playAudioLabel2;
    @FXML
    private Label appendToFileLabel12;
    @FXML
    private Label moveCopyLabel2;
    @FXML
    private Label destinationDirectoryLabel2;
    @FXML
    private Label showMessageLabel2;
    @FXML
    private Label appendToFileLabel22;
    @FXML
    private Label sourceDirectoryLabel2;
    @FXML
    private Label deleteLabel12;
    @FXML
    private Label deleteLabel22;
    @FXML
    private Label execProgLabel2;
    @FXML
    private Label execArgumentsLabel2;
    @FXML
    private Button appendToFileButton2;
    @FXML
    private Button playAudioButton2;
    @FXML
    private Button destinationDirectoryButton2;
    @FXML
    private Button execProgButton2;
    @FXML
    private Label fileAudioNameLabel2;
    @FXML
    private Label selectedDestinationDirectoryLabel2;
    @FXML
    private Label selectedSourceDirectoryLabel2;
    @FXML
    private Label deleteFileLabel2;
    @FXML
    private Label fileToAppendLabel2;
    @FXML
    private Label execProgramActionLabel2;
    @FXML
    private ComboBox<?> actionComboBox3;
    @FXML
    private TextArea showMessageTextArea3;
    @FXML
    private TextArea appendToFileTextArea3;
    @FXML
    private Button sourceDirectoryButton3;
    @FXML
    private TextField moveCopyTextField3;
    @FXML
    private TextField execArgumentsTextField3;
    @FXML
    private Button deleteButton3;
    @FXML
    private TextField deleteTextField3;
    @FXML
    private Label playAudioLabel3;
    @FXML
    private Label appendToFileLabel13;
    @FXML
    private Label moveCopyLabel3;
    @FXML
    private Label destinationDirectoryLabel3;
    @FXML
    private Label showMessageLabel3;
    @FXML
    private Label appendToFileLabel23;
    @FXML
    private Label sourceDirectoryLabel3;
    @FXML
    private Label deleteLabel13;
    @FXML
    private Label deleteLabel23;
    @FXML
    private Label execProgLabel3;
    @FXML
    private Label execArgumentsLabel3;
    @FXML
    private Button appendToFileButton3;
    @FXML
    private Button playAudioButton3;
    @FXML
    private Button destinationDirectoryButton3;
    @FXML
    private Button execProgButton3;
    @FXML
    private Label fileAudioNameLabel3;
    @FXML
    private Label selectedDestinationDirectoryLabel3;
    @FXML
    private Label selectedSourceDirectoryLabel3;
    @FXML
    private Label deleteFileLabel3;
    @FXML
    private Label fileToAppendLabel3;
    @FXML
    private Label execProgramActionLabel3;
    @FXML
    private ComboBox<?> actionComboBox4;
    @FXML
    private TextArea showMessageTextArea4;
    @FXML
    private TextArea appendToFileTextArea4;
    @FXML
    private Button sourceDirectoryButton4;
    @FXML
    private TextField moveCopyTextField4;
    @FXML
    private TextField execArgumentsTextField4;
    @FXML
    private Button deleteButton4;
    @FXML
    private TextField deleteTextField4;
    @FXML
    private Label playAudioLabel4;
    @FXML
    private Label appendToFileLabel14;
    @FXML
    private Label moveCopyLabel4;
    @FXML
    private Label destinationDirectoryLabel4;
    @FXML
    private Label showMessageLabel4;
    @FXML
    private Label appendToFileLabel24;
    @FXML
    private Label sourceDirectoryLabel4;
    @FXML
    private Label deleteLabel14;
    @FXML
    private Label deleteLabel24;
    @FXML
    private Label execProgLabel4;
    @FXML
    private Label execArgumentsLabel4;
    @FXML
    private Button appendToFileButton4;
    @FXML
    private Button playAudioButton4;
    @FXML
    private Button destinationDirectoryButton4;
    @FXML
    private Button execProgButton4;
    @FXML
    private Label fileAudioNameLabel4;
    @FXML
    private Label selectedDestinationDirectoryLabel4;
    @FXML
    private Label selectedSourceDirectoryLabel4;
    @FXML
    private Label deleteFileLabel4;
    @FXML
    private Label fileToAppendLabel4;
    @FXML
    private Label execProgramActionLabel4;
    @FXML
    private ComboBox<?> actionComboBox5;
    @FXML
    private TextArea showMessageTextArea5;
    @FXML
    private TextArea appendToFileTextArea5;
    @FXML
    private Button sourceDirectoryButton5;
    @FXML
    private TextField moveCopyTextField5;
    @FXML
    private TextField execArgumentsTextField5;
    @FXML
    private Button deleteButton5;
    @FXML
    private TextField deleteTextField5;
    @FXML
    private Label playAudioLabel5;
    @FXML
    private Label appendToFileLabel15;
    @FXML
    private Label moveCopyLabel5;
    @FXML
    private Label destinationDirectoryLabel5;
    @FXML
    private Label showMessageLabel5;
    @FXML
    private Label appendToFileLabel25;
    @FXML
    private Label sourceDirectoryLabel5;
    @FXML
    private Label deleteLabel15;
    @FXML
    private Label deleteLabel25;
    @FXML
    private Label execProgLabel5;
    @FXML
    private Label execArgumentsLabel5;
    @FXML
    private Button appendToFileButton5;
    @FXML
    private Button playAudioButton5;
    @FXML
    private Button destinationDirectoryButton5;
    @FXML
    private Button execProgButton5;
    @FXML
    private Label fileAudioNameLabel5;
    @FXML
    private Label selectedDestinationDirectoryLabel5;
    @FXML
    private Label selectedSourceDirectoryLabel5;
    @FXML
    private Label deleteFileLabel5;
    @FXML
    private Label fileToAppendLabel5;
    @FXML
    private Label execProgramActionLabel5;
    @FXML
    private Button createSequenceActionButton;
    @FXML
    private Pane pane2;
    @FXML
    private Button backFromActionButton;
    @FXML
    private Button doneActionButton;
    @FXML
    private TextField insertExitValueTextField1;
    @FXML
    private Label insertExitValueLabel1;
    @FXML
    private Label insertExitValueLabel2;
    @FXML
    private TextField insertExitValueTextField2;
    @FXML
    private Label exitValueProgramLabel1;
    @FXML
    private Label exitValueProgramLabel2;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize ComboBox options
        // ... (initialize triggerList, actionList, dayOfWeekList, dayOfMonthList, hourList, minuteList)
        triggerList = FXCollections.observableArrayList();
        triggerComboBox.setItems(triggerList);
        triggerList.addAll("Time", "Day of Week", "Day of Month",
                "Date",
                "File Existance Verification", "File Dimension Verification",
                "Program Exit Status Verification");

        actionList = FXCollections.observableArrayList();
        actionComboBox.setItems(actionList);
        actionList.addAll("Show Message", "Play Audio",
                "Append String to Textfile", "Move File",
                "Copy File", "Delete File",
                "Execute Program");

        ObservableList<String> dayOfWeekList = FXCollections.observableArrayList();
        dayOfWeekComboBox.setItems(dayOfWeekList);
        dayOfWeekList.addAll("Monday", "Tuesday",
                "Wednesday", "Thursday",
                "Friday", "Saturday", "Sunday");

        ObservableList<Integer> dayOfMonthList = FXCollections.observableArrayList();
        dayOfMonthComboBox.setItems(dayOfMonthList);
        for (int i = 1; i <= 31; i++) {
            dayOfMonthList.add(i);
        }

        ObservableList<String> hourList = FXCollections.observableArrayList();
        hourComboBox.setItems(hourList);
        for (int i = 0; i <= 23; i++) {
            hourList.add(String.format("%02d", i));
        }

        ObservableList<String> minuteList = FXCollections.observableArrayList();
        minuteComboBox.setItems(minuteList);
        for (int i = 0; i <= 59; i++) {
            minuteList.add(String.format("%02d", i));
        }
        ObservableList<String> typeDimensionList = FXCollections.observableArrayList("Byte", "KB", "MB", "GB");
        typeDimensionComboBox.setValue("Byte");
        typeDimensionComboBox.setItems(typeDimensionList);

        // Bind disable properties for the saveButton
        // ... (binding saveButton disable property based on various conditions)
        saveButton.disableProperty().bind(((hourComboBox.valueProperty().isNull().or(minuteComboBox.valueProperty().isNull()))
                .and(dayOfWeekComboBox.valueProperty().isNull())
                .and(dayOfMonthComboBox.valueProperty().isNull())
                .and(datePicker.valueProperty().isNull())
                .and((exFileTextField.textProperty().isEmpty()).or(exFileDirectoryLabel.visibleProperty().not()))
                .and((fileDimensionTextField.textProperty().isEmpty()).or(labelDimensionFile.visibleProperty().not()))
                .and((execProgramTextField.textProperty().isEmpty()).or(exitValueProgramLabel.visibleProperty().not()).or(insertExitValueTextField.textProperty().isEmpty()))
                .and(compositeTriggerLabel.visibleProperty().not())
                .or(triggerComboBox.valueProperty().isNull()))
                .or(
                        (showMessageTextArea.textProperty().isEmpty())
                                .and(fileAudioNameLabel.visibleProperty().not())
                                .and((appendToFileTextArea.textProperty().isEmpty()).or(fileToAppendLabel.visibleProperty().not()))
                                .and((moveCopyTextField.textProperty().isEmpty()).or(selectedSourceDirectoryLabel.visibleProperty().not()).or(selectedDestinationDirectoryLabel.visibleProperty().not()))
                                .and((deleteTextField.textProperty().isEmpty()).or(deleteFileLabel.visibleProperty().not()))
                                .and((execArgumentsTextField.textProperty().isEmpty()).or(execProgramActionLabel.visibleProperty().not()))
                                .and((insertExitValueTextField.textProperty().isEmpty()))
                                .or(actionComboBox.valueProperty().isNull()))
                .or(ruleNameTextField.textProperty().isEmpty())
                //.or(fireSleepingTimeCheckBox.selectedProperty().not()
                        // .and(fireOnceCheckBox.selectedProperty().not()))
        );

        fileAudioNameLabel.visibleProperty().setValue(Boolean.FALSE);
        exFileDirectoryLabel.visibleProperty().setValue(Boolean.FALSE);
        labelDimensionFile.visibleProperty().setValue(Boolean.FALSE);
        labelDimensionFile1.visibleProperty().setValue(Boolean.FALSE);
        exFileDirectoryLabel1.visibleProperty().setValue(Boolean.FALSE);
        insertExitValueLabel.visibleProperty().setValue(Boolean.FALSE);

        fireSleepingTimeCheckBox.disableProperty().bind(fireOnceCheckBox.selectedProperty());
        fireOnceCheckBox.disableProperty().bind(fireSleepingTimeCheckBox.selectedProperty());

        minuteSleepingTimeSpinner.visibleProperty().bind(fireSleepingTimeCheckBox.selectedProperty());
        hourSleepingTimeSpinner.visibleProperty().bind(fireSleepingTimeCheckBox.selectedProperty());
        daySleepingTimeSpinner.visibleProperty().bind(fireSleepingTimeCheckBox.selectedProperty());
        minuteSleepingTimeLabel.visibleProperty().bind(fireSleepingTimeCheckBox.selectedProperty());
        hourSleepingTimeLabel.visibleProperty().bind(fireSleepingTimeCheckBox.selectedProperty());
        daySleepingTimeLabel.visibleProperty().bind(fireSleepingTimeCheckBox.selectedProperty());

        // Set initial visibility properties for UI elements based on trigger and action selection
        // ... (set visibility properties based on triggerComboBox and actionComboBox values)
        hourComboBox.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("Time"));
        minuteComboBox.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("Time"));
        dayOfWeekComboBox.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("Day of Week"));
        dayOfMonthComboBox.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("Day of Month"));
        datePicker.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("Date"));
        exFileTextField.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("File Existance Verification"));
        exFileButton.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("File Existance Verification"));
        fileDimensionTextField.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("File Dimension Verification"));
        fileDimensionButton.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("File Dimension Verification"));
        execProgramTextField.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("Program Exit Status Verification"));
        execProgramButton.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("Program Exit Status Verification"));
        timeLabel.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("Time"));
        dayOfWeekLabel.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("Day of Week"));
        dayOfMonthLabel.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("Day of Month"));
        dateLabel.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("Date"));
        exFileLabel.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("File Existance Verification"));
        fileDimensionLabel.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("File Dimension Verification"));
        execProgramLabel.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("Program Exit Status Verification"));
        typeDimensionComboBox.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("File Dimension Verification"));
        insertExitValueLabel.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("Program Exit Status Verification"));
        insertExitValueTextField.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("Program Exit Status Verification"));

        showMessageTextArea.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Show Message"));
        playAudioButton.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Play Audio"));
        appendToFileButton.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Append String to Textfile"));
        appendToFileTextArea.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Append String to Textfile"));
        moveCopyTextField.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Move File").or(actionComboBox.valueProperty().isEqualTo("Copy File")));
        sourceDirectoryButton.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Move File").or(actionComboBox.valueProperty().isEqualTo("Copy File")));
        destinationDirectoryButton.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Move File").or(actionComboBox.valueProperty().isEqualTo("Copy File")));
        deleteTextField.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Delete File"));
        deleteButton.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Delete File"));
        execProgButton.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Execute Program"));
        execArgumentsTextField.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Execute Program"));
        showMessageLabel.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Show Message"));
        playAudioLabel.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Play Audio"));
        appendToFileLabel1.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Append String to Textfile"));
        appendToFileLabel2.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Append String to Textfile"));
        sourceDirectoryLabel.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Move File").or(actionComboBox.valueProperty().isEqualTo("Copy File")));
        destinationDirectoryLabel.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Move File").or(actionComboBox.valueProperty().isEqualTo("Copy File")));
        moveCopyLabel.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Move File").or(actionComboBox.valueProperty().isEqualTo("Copy File")));
        deleteLabel1.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Delete File"));
        deleteLabel2.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Delete File"));
        execProgLabel.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Execute Program"));
        execArgumentsLabel.visibleProperty().bind(actionComboBox.valueProperty().isEqualTo("Execute Program"));

        //Limit the number of characters in showMessageTextArea to 1000
        showMessageTextArea.setTextFormatter(new TextFormatter<String>(change
                -> change.getControlNewText().length() <= 1000 ? change : null));

        //Placeholder text for showMessageTextArea
        showMessageTextArea.setPromptText("Max 1000 characters...");

        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                // Disable dates that have already passed
                setDisable(date.isBefore(LocalDate.now()));
            }
        });
        
        datePicker1.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                // Disabilita le date precedenti a oggi
                setDisable(date.isBefore(LocalDate.now()));
            }
        });
        
        datePicker2.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                // Disabilita le date precedenti a oggi
                setDisable(date.isBefore(LocalDate.now()));
            }
        });
        
        triggerComboBox1.setItems(triggerList);
        dayOfWeekComboBox1.setItems(dayOfWeekList);
        dayOfMonthComboBox1.setItems(dayOfMonthList);
        hourComboBox1.setItems(hourList);
        minuteComboBox1.setItems(minuteList);
        typeDimensionComboBox1.setValue("Byte");
        typeDimensionComboBox1.setItems(typeDimensionList);
        
        triggerComboBox2.setItems(triggerList);
        dayOfWeekComboBox2.setItems(dayOfWeekList);
        dayOfMonthComboBox2.setItems(dayOfMonthList);
        hourComboBox2.setItems(hourList);
        minuteComboBox2.setItems(minuteList);
        typeDimensionComboBox2.setValue("Byte");
        typeDimensionComboBox2.setItems(typeDimensionList);
        
        
        ObservableList<String> notNothingList = FXCollections.observableArrayList();
        notOperandComboBox1.setItems(notNothingList);
        notOperandComboBox2.setItems(notNothingList);
        notNothingList.addAll("","NOT");
        notOperandComboBox1.valueProperty().setValue("");
        notOperandComboBox2.valueProperty().setValue("");
        
        ObservableList<String> andOrList = FXCollections.observableArrayList();
        logicalOperandComboBox.setItems(andOrList);
        andOrList.addAll("", "AND", "OR");
        
        logicalOperandComboBox.getSelectionModel().selectFirst();
        
        secondTriggerLabel.visibleProperty().bind(logicalOperandComboBox.valueProperty().isEqualTo("").not());
        notOperandComboBox2.visibleProperty().bind(logicalOperandComboBox.valueProperty().isEqualTo("").not());
        triggerComboBox2.visibleProperty().bind(logicalOperandComboBox.valueProperty().isEqualTo("").not());
        
        
        hourComboBox1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("Time"));
        minuteComboBox1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("Time"));
        dayOfWeekComboBox1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("Day of Week"));
        dayOfMonthComboBox1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("Day of Month"));
        datePicker1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("Date"));
        exFileTextField1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("File Existance Verification"));
        exFileButton1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("File Existance Verification"));
        fileDimensionTextField1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("File Dimension Verification"));
        fileDimensionButton1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("File Dimension Verification"));
        execProgramTextField1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("Program Exit Status Verification"));
        execProgramButton1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("Program Exit Status Verification"));
        timeLabel1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("Time"));
        dayOfWeekLabel1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("Day of Week"));
        dayOfMonthLabel1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("Day of Month"));
        dateLabel1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("Date"));
        exFileLabel1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("File Existance Verification"));
        fileDimensionLabel1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("File Dimension Verification"));
        execProgramLabel1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("Program Exit Status Verification"));
        typeDimensionComboBox1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("File Dimension Verification"));
        insertExitValueTextField1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("Program Exit Status Verification"));
        insertExitValueLabel1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("Program Exit Status Verification"));
        
        
        hourComboBox2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("Time"));
        minuteComboBox2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("Time"));
        dayOfWeekComboBox2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("Day of Week"));
        dayOfMonthComboBox2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("Day of Month"));
        datePicker2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("Date"));
        exFileTextField2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("File Existance Verification"));
        exFileButton2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("File Existance Verification"));
        fileDimensionTextField2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("File Dimension Verification"));
        fileDimensionButton2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("File Dimension Verification"));
        execProgramTextField2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("Program Exit Status Verification"));
        execProgramButton2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("Program Exit Status Verification"));
        timeLabel2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("Time"));
        dayOfWeekLabel2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("Day of Week"));
        dayOfMonthLabel2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("Day of Month"));
        dateLabel2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("Date"));
        exFileLabel2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("File Existance Verification"));
        fileDimensionLabel2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("File Dimension Verification"));
        execProgramLabel2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("Program Exit Status Verification"));
        typeDimensionComboBox2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("File Dimension Verification"));
        insertExitValueTextField2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("Program Exit Status Verification"));
        insertExitValueLabel2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("Program Exit Status Verification"));
                
        logicalOperandLabel.visibleProperty().bind(((hourComboBox1.valueProperty().isNull().or(minuteComboBox1.valueProperty().isNull()))
                .and(dayOfWeekComboBox1.valueProperty().isNull())
                .and(dayOfMonthComboBox1.valueProperty().isNull())
                .and(datePicker1.valueProperty().isNull())
                .and((exFileTextField1.textProperty().isEmpty()).or(exFileDirectoryLabel1.visibleProperty().not()))
                .and((fileDimensionTextField1.textProperty().isEmpty()).or(labelDimensionFile1.visibleProperty().not()))
                //.and((execProgramTextField1.textProperty().isEmpty()).or())
                .and(compositeTriggerLabel1.visibleProperty().not())
                .or(triggerComboBox1.valueProperty().isNull())).not());
        
        logicalOperandComboBox.visibleProperty().bind(((hourComboBox1.valueProperty().isNull().or(minuteComboBox1.valueProperty().isNull()))
                .and(dayOfWeekComboBox1.valueProperty().isNull())
                .and(dayOfMonthComboBox1.valueProperty().isNull())
                .and(datePicker1.valueProperty().isNull())
                .and((exFileTextField1.textProperty().isEmpty()).or(exFileDirectoryLabel1.visibleProperty().not()))
                .and((fileDimensionTextField1.textProperty().isEmpty()).or(labelDimensionFile1.visibleProperty().not()))
                //.and((execProgramTextField1.textProperty().isEmpty()).or())
                .and(compositeTriggerLabel1.visibleProperty().not())
                .or(triggerComboBox1.valueProperty().isNull())).not());
        
        doneTriggerButton.disableProperty().bind((((hourComboBox1.valueProperty().isNull().or(minuteComboBox1.valueProperty().isNull()))
                .and(dayOfWeekComboBox1.valueProperty().isNull())
                .and(dayOfMonthComboBox1.valueProperty().isNull())
                .and(datePicker1.valueProperty().isNull())
                .and((exFileTextField1.textProperty().isEmpty()).or(exFileDirectoryLabel1.visibleProperty().not()))
                .and((fileDimensionTextField1.textProperty().isEmpty()).or(labelDimensionFile1.visibleProperty().not()))
                //.and((execProgramTextField.textProperty().isEmpty()).or())
                .and(compositeTriggerLabel1.visibleProperty().not())
                .or(triggerComboBox1.valueProperty().isNull()))
                .or(notOperandComboBox1.valueProperty().isEqualTo("")
                    .and(logicalOperandComboBox.valueProperty().isEqualTo("")))
                
                .or(triggerTextField.textProperty().isEmpty()))
                .or(((hourComboBox2.valueProperty().isNull().or(minuteComboBox2.valueProperty().isNull()))
                .and(dayOfWeekComboBox2.valueProperty().isNull())
                .and(dayOfMonthComboBox2.valueProperty().isNull())
                .and(datePicker2.valueProperty().isNull())
                .and((exFileTextField2.textProperty().isEmpty()).or(exFileDirectoryLabel2.visibleProperty().not()))
                .and((fileDimensionTextField2.textProperty().isEmpty()).or(labelDimensionFile2.visibleProperty().not()))
                //.and((execProgramTextField.textProperty().isEmpty()).or())
                .and(compositeTriggerLabel2.visibleProperty().not())
                .or(triggerComboBox2.valueProperty().isNull()))
                .and(logicalOperandComboBox.valueProperty().isNotEqualTo(""))
                ));
        
        compositeTrigger = new HashMap();
    }

    private Trigger checkTrigger(String s, int n) {
        switch (s) {
            case "Time":
                TriggerCreator timeTC;
                if(n == 0)
                    timeTC = new TimeTriggerCreator(LocalTime.of(Integer.parseInt(hourComboBox.getValue()), Integer.parseInt(minuteComboBox.getValue()), 0));
                else if(n == 1)
                    timeTC = new TimeTriggerCreator(LocalTime.of(Integer.parseInt(hourComboBox1.getValue()), Integer.parseInt(minuteComboBox1.getValue()), 0));
                else
                    timeTC = new TimeTriggerCreator(LocalTime.of(Integer.parseInt(hourComboBox2.getValue()), Integer.parseInt(minuteComboBox2.getValue()), 0));
                return timeTC.createTrigger();
            case "Day of Week":
                TriggerCreator dayOfWeekTC;
                if(n == 0)
                    dayOfWeekTC = new DayOfWeekTriggerCreator(dayOfWeekComboBox.getValue());
                else if(n == 1)
                    dayOfWeekTC = new DayOfWeekTriggerCreator(dayOfWeekComboBox1.getValue());
                else
                    dayOfWeekTC = new DayOfWeekTriggerCreator(dayOfWeekComboBox2.getValue());
                return dayOfWeekTC.createTrigger();
            case "Day of Month":
                TriggerCreator dayOfMonthTC;
                if(n == 0)
                    dayOfMonthTC = new DayOfMonthTriggerCreator(dayOfMonthComboBox.getValue());
                else if(n == 1)
                    dayOfMonthTC = new DayOfMonthTriggerCreator(dayOfMonthComboBox1.getValue());
                else
                    dayOfMonthTC = new DayOfMonthTriggerCreator(dayOfMonthComboBox2.getValue());
                return dayOfMonthTC.createTrigger();
            case "Date":
                TriggerCreator dateTC;
                if(n == 0)
                    dateTC = new DateTriggerCreator(datePicker.getValue());
                else if(n == 1)
                    dateTC = new DateTriggerCreator(datePicker1.getValue());
                else 
                    dateTC = new DateTriggerCreator(datePicker2.getValue());
                return dateTC.createTrigger();
            case "File Dimension Verification":
                TriggerCreator fileDimensionTC;
                if(n == 0)
                    fileDimensionTC = new FileSizeCheckerTriggerCreator(selectedFileForDimension, Long.parseLong(fileDimensionTextField.textProperty().getValue()), typeDimensionComboBox.getValue());
                else if(n == 1)
                    fileDimensionTC = new FileSizeCheckerTriggerCreator(selectedFileForDimension1, Long.parseLong(fileDimensionTextField1.textProperty().getValue()), typeDimensionComboBox1.getValue());
                else
                    fileDimensionTC = new FileSizeCheckerTriggerCreator(selectedFileForDimension2, Long.parseLong(fileDimensionTextField2.textProperty().getValue()), typeDimensionComboBox2.getValue());
                return fileDimensionTC.createTrigger();
            case "File Existance Verification":
                TriggerCreator fileExTC;
                if(n == 0)
                    fileExTC = new FileCheckTriggerCreator(selectedExFile.toString(), exFileTextField.getText());
                else if(n == 1)
                    fileExTC = new FileCheckTriggerCreator(selectedExFile1.toString(), exFileTextField1.getText());
                else
                    fileExTC = new FileCheckTriggerCreator(selectedExFile2.toString(), exFileTextField2.getText());
                return fileExTC.createTrigger();
            case "Program Exit Status Verification":
                
                List<String> execProgList = new ArrayList<>();
                execProgList.add(selectedProgramTriggerFile.toString());
                execProgList.addAll(Arrays.asList(execProgramTextField.getText().split(" ")));
                TriggerCreator execProgTC = new ExecuteProgramTriggerCreator(execProgList, Integer.parseInt(insertExitValueTextField.getText()));
                return execProgTC.createTrigger();
            default:
                for(Trigger t : compositeTrigger.values()){
                    if(s.substring(12).equals(t.getType().substring(12)))
                           return t; 
                }
                System.out.println("Not valid Trigger");
                return null;
        }
    }

    private Action checkAction(String s) {
        switch (s) {
            case "Play Audio":
                ActionCreator playAudioAC = new PlayAudioActionCreator(selectedFilePath);
                return playAudioAC.createAction();

            //When showMessageAction is selected, onSave the action is created with text from textArea
            case "Show Message":
                ActionCreator showMessageAC = new ShowMessageActionCreator(showMessageTextArea.getText());
                return showMessageAC.createAction();
            case "Copy File":
                ActionCreator copyFileAC = new CopyFileActionCreator(Paths.get(selectedSourcePath.toString() + "/" + moveCopyTextField.getText()), selectedDestinationPath);
                return copyFileAC.createAction();
            case "Move File":
                ActionCreator moveFileAC = new MoveFileActionCreator(Paths.get(selectedSourcePath.toString() + "/" + moveCopyTextField.getText()), selectedDestinationPath);
                return moveFileAC.createAction();
            case "Delete File":
                ActionCreator deleteFileAC = new DeleteFileActionCreator(Paths.get(selectedDeleteSourcePath.toString() + "/" + deleteTextField.getText()));
                return deleteFileAC.createAction();
            case "Execute Program":
                List<String> execProgList = new ArrayList<>();
                execProgList.add(selectedFilePath.toString());
                execProgList.addAll(Arrays.asList(execArgumentsTextField.getText().split(" ")));
                ActionCreator execProgAC = new ExecuteProgramActionCreator(execProgList);
                return execProgAC.createAction();
            case "Append String to Textfile":
                ActionCreator appendFileAC = new FileAppenderActionCreator(selectedAppendFile, appendToFileTextArea.getText());
                return appendFileAC.createAction();
            default:
                System.out.println("Not valid Action");
                return null;
        }
    }

    @FXML
    private void onSave(ActionEvent event) {
        // Create Trigger and Action based on selected options
        // ... (create Trigger and Action based on triggerComboBox and actionComboBox values)
        Trigger trigger = checkTrigger(triggerComboBox.getValue(), 0);
        Action action = checkAction(actionComboBox.getValue());
        Rule r;
        //When copyFileAction is selected, onSave the action is created from the different input fields
        // Create a Rule using the created Trigger and Action
        if (controllerOne.checkNameRule(ruleNameTextField.textProperty().getValue())) {
            if (fireSleepingTimeCheckBox.selectedProperty().getValue()) {
                r = new SleepingTimeRule(ruleNameTextField.textProperty().getValue(), action, trigger, daySleepingTimeSpinner.valueProperty().getValue(), hourSleepingTimeSpinner.valueProperty().getValue(), minuteSleepingTimeSpinner.valueProperty().getValue());
            } else {
                r = new FireOnceRule(ruleNameTextField.textProperty().getValue(), action, trigger);
            }
            //else
            //rule = new Rule(ruleNameTextField.textProperty().getValue(), action, trigger);

            // Add the created rule to the ObservableList in the first controller
            controllerOne.addRuleToObsList(r);

            // Close the current stage (window)
            Stage currentStage = (Stage) saveButton.getScene().getWindow();

            currentStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("OK");
            alert.setContentText("The name exists already!");
            alert.showAndWait();
        }
    }

    @FXML
    private void onPlayAudioButton(ActionEvent event) {
        Stage primaryStage = (Stage) playAudioButton.getScene().getWindow();

        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Audio File");

        // Set the filter for audio files
        FileChooser.ExtensionFilter audioFilter = new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3");
        fileChooser.getExtensionFilters().add(audioFilter);

        // Show the FileChooser and get the selected file
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            selectedFilePath = selectedFile.toPath();
            fileAudioNameLabel.textProperty().setValue(selectedFile.getName());
            fileAudioNameLabel.visibleProperty().setValue(Boolean.TRUE);
        }
    }

    @FXML
    private void onSourceDirectoryButton(ActionEvent event) {
        Stage primaryStage = (Stage) sourceDirectoryButton.getScene().getWindow();

        //Create a DirectoryChooser
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select the source directory of the file to copy");

        //Get source directory if it's not null
        File checkFile = directoryChooser.showDialog(primaryStage);
        if (checkFile != null) {
            selectedSourcePath = checkFile.toPath();
            selectedSourceDirectoryLabel.textProperty().setValue(selectedSourcePath.getFileName().toString());
            selectedSourceDirectoryLabel.visibleProperty().setValue(Boolean.TRUE);
        }
    }

    @FXML
    private void onDestinationDirectoryButton(ActionEvent event) {
        Stage primaryStage = (Stage) destinationDirectoryButton.getScene().getWindow();

        //Create a DirectoryChooser
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select the destination directory of the file to copy");

        //Get source directory if it's not null
        File checkFile = directoryChooser.showDialog(primaryStage);
        if (checkFile != null) {
            selectedDestinationPath = checkFile.toPath();
            selectedDestinationDirectoryLabel.textProperty().setValue(selectedDestinationPath.getFileName().toString());
            selectedDestinationDirectoryLabel.visibleProperty().setValue(Boolean.TRUE);
        }
    }

    @FXML
    private void onChangeTrigger(ActionEvent event) {
        // Reset UI elements based on trigger selection
        // ... (reset UI elements based on triggerComboBox selection)
        hourComboBox.valueProperty().setValue(null);
        minuteComboBox.valueProperty().setValue(null);
        dayOfWeekComboBox.valueProperty().setValue(null);
        dayOfMonthComboBox.valueProperty().setValue(null);
        datePicker.valueProperty().setValue(null);
        exFileTextField.textProperty().setValue(null);
        exFileButton.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("File Existance Verification"));
        fileDimensionTextField.textProperty().setValue(null);
        fileDimensionButton.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("File Dimension Verification"));
        execProgramTextField.textProperty().setValue(null);
        execProgramButton.visibleProperty().bind(triggerComboBox.valueProperty().isEqualTo("Program Exit Status Verification"));
        exFileDirectoryLabel.visibleProperty().setValue(Boolean.FALSE);
        execProgramTextField.textProperty().setValue(null);
        labelDimensionFile.visibleProperty().setValue(Boolean.FALSE);
        fileDimensionTextField.textProperty().setValue(null);
        insertExitValueTextField.textProperty().setValue(null);
        exitValueProgramLabel.visibleProperty().setValue(Boolean.FALSE);
        if(triggerComboBox.valueProperty().getValue().startsWith("Composite")){
            compositeTriggerLabel.textProperty().setValue(compositeTrigger.get(triggerComboBox.valueProperty().getValue()).toString());
            compositeTriggerLabel.visibleProperty().setValue(Boolean.TRUE);
        } else
            compositeTriggerLabel.visibleProperty().setValue(Boolean.FALSE);
    }

    @FXML
    private void onDeleteButton(ActionEvent event) {
        Stage primaryStage = (Stage) deleteButton.getScene().getWindow();

        //Create a DirectoryChooser
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select the directory of the file to delete");

        //Get directory of file to delete if it's not null
        File checkFile = directoryChooser.showDialog(primaryStage);
        if (checkFile != null) {
            selectedDeleteSourcePath = checkFile.toPath();
            deleteFileLabel.textProperty().setValue(selectedDeleteSourcePath.getFileName().toString());
            deleteFileLabel.visibleProperty().setValue(Boolean.TRUE);
        }
    }

    @FXML
    private void onChangeAction(ActionEvent event) {
        // Reset UI elements based on action selection
        // ... (reset UI elements based on actionComboBox selection)
        fileAudioNameLabel.visibleProperty().setValue(Boolean.FALSE);
        execProgramActionLabel.visibleProperty().setValue(Boolean.FALSE);
        labelDimensionFile.visibleProperty().setValue(Boolean.FALSE);
        fileToAppendLabel.visibleProperty().setValue(Boolean.FALSE);
        deleteFileLabel.visibleProperty().setValue(Boolean.FALSE);
        selectedDestinationDirectoryLabel.visibleProperty().setValue(Boolean.FALSE);
        selectedSourceDirectoryLabel.visibleProperty().setValue(Boolean.FALSE);
    }

    public void setControllerOne(FXMLDocumentController controllerOne) {
        //Sets the reference to the first controller
        this.controllerOne = controllerOne;
    }

    @FXML
    private void onFileDimensionButton(ActionEvent event) {
        Stage primaryStage = (Stage) fileDimensionButton.getScene().getWindow();

        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a File");
        // Show the FileChooser and get the selected file
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            if (event.getSource() instanceof Button) {
                Button clickedButton = (Button) event.getSource();
                String buttonId = clickedButton.getId();
                if (buttonId.charAt(buttonId.length()-1) == '1'){
                    selectedFileForDimension1 = selectedFile.toPath();
                    labelDimensionFile1.textProperty().setValue(selectedFileForDimension1.getFileName().toString());
                    labelDimensionFile1.setVisible(true);
                } else if (buttonId.charAt(buttonId.length()-1) == '2'){
                    selectedFileForDimension2 = selectedFile.toPath();
                    labelDimensionFile2.textProperty().setValue(selectedFileForDimension2.getFileName().toString());
                    labelDimensionFile2.setVisible(true);
                } else {
                    selectedFileForDimension = selectedFile.toPath();
                    labelDimensionFile.textProperty().setValue(selectedFileForDimension.getFileName().toString());
                    labelDimensionFile.setVisible(true);
                }
            }
        }
    }

    @FXML
    private void onExFileButton(ActionEvent event) {
        Stage primaryStage = (Stage) destinationDirectoryButton.getScene().getWindow();

        //Create a DirectoryChooser
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select the directory of the file to check");

        //Get source directory if it's not null
        File checkFile = directoryChooser.showDialog(primaryStage);
        if (checkFile != null) {
            if (event.getSource() instanceof Button) {
                Button clickedButton = (Button) event.getSource();
                String buttonId = clickedButton.getId();
                if (buttonId.charAt(buttonId.length()-1) == '1'){
                    selectedExFile1 = checkFile.toPath();
                    exFileDirectoryLabel1.textProperty().setValue(selectedExFile1.getFileName().toString());
                    exFileDirectoryLabel1.visibleProperty().setValue(Boolean.TRUE);
                } else if (buttonId.charAt(buttonId.length()-1) == '2'){
                    selectedExFile2 = checkFile.toPath();
                    exFileDirectoryLabel2.textProperty().setValue(selectedExFile2.getFileName().toString());
                    exFileDirectoryLabel2.visibleProperty().setValue(Boolean.TRUE);
                } else {
                    selectedExFile = checkFile.toPath();
                    exFileDirectoryLabel.textProperty().setValue(selectedExFile.getFileName().toString());
                    exFileDirectoryLabel.visibleProperty().setValue(Boolean.TRUE);
                }
            }
        }
    }

    @FXML
    private void onExecProgButton(ActionEvent event) {
        Stage primaryStage = (Stage) execProgButton.getScene().getWindow();

        //Setting the FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select the program to execute:");

        //Setting the filter for exe files
        FileChooser.ExtensionFilter exeFilter = new FileChooser.ExtensionFilter("Executable Files", "*.exe", "*.bat", "*.cmd", "*", "*.jar");
        fileChooser.getExtensionFilters().add(exeFilter);
        //Showing FileChooser and getting file path
        File selectedProgram = fileChooser.showOpenDialog(primaryStage);
        if (selectedProgram != null) {
            selectedFilePath = selectedProgram.toPath();
            execProgramActionLabel.textProperty().setValue(selectedFilePath.getFileName().toString());
            execProgramActionLabel.visibleProperty().setValue(Boolean.TRUE);
        }
    }

    @FXML
    private void onAppendToFileButton(ActionEvent event) {
        Stage primaryStage = (Stage) appendToFileTextArea.getScene().getWindow();

        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a File");

        // Set the default filter for text files
        FileChooser.ExtensionFilter defaultFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(defaultFilter);

        // Show the FileChooser and get the selected file
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            selectedAppendFile = selectedFile.toPath();
            // Check if the file is readable, writable, and a text file
            if (Files.isReadable(selectedAppendFile) && Files.isWritable(selectedAppendFile)) {
                fileToAppendLabel.textProperty().setValue(selectedFile.getName());
                fileToAppendLabel.visibleProperty().setValue(Boolean.TRUE);
            } else {
                // Handle cases where the selected file is not a readable/writable text file
                System.out.println("");
            }
        }
    }

    @FXML
    private void onFileDimensionCheck(KeyEvent event) {
        if (!(event.getCharacter()).matches("^\\d*")) {
            event.consume();
        }
    }

    @FXML
    private void onCreateCompositeTrigger(ActionEvent event) {
        anchorPane1.visibleProperty().setValue(Boolean.FALSE);
        pane1.visibleProperty().setValue(Boolean.TRUE);
        triggerTextField.textProperty().setValue("");
        triggerComboBox1.valueProperty().setValue("");
        notOperandComboBox1.valueProperty().setValue("");
    }

    @FXML
    private void onDoneTrigger(ActionEvent event) {
        pane1.visibleProperty().setValue(Boolean.FALSE);
        anchorPane1.visibleProperty().setValue(Boolean.TRUE);
        Trigger composite;
        Trigger composite1;
        Trigger composite2 = null;
        String logicOp = logicalOperandComboBox.valueProperty().getValue();
        Trigger trigger1 = checkTrigger(triggerComboBox1.getValue(), 1);
        if(notOperandComboBox1.valueProperty().get().contains("NOT"))
            composite1 = new CompositeTrigger(triggerTextField.getText(),trigger1, null, "NOT");
        else 
            composite1 = trigger1;
        composite = composite1;
        if(logicOp.equals("AND") || logicOp.equals("OR")){
            Trigger trigger2 = checkTrigger(triggerComboBox2.getValue(), 2);
            if(notOperandComboBox2.valueProperty().get().contains("NOT"))
                composite2 = new CompositeTrigger(triggerTextField.getText(),trigger2, null, "NOT");
            else
                composite2 = trigger2;
            composite = new CompositeTrigger(triggerTextField.getText(),composite1, composite2, logicOp);
        }
        
        triggerList.add(composite.getType());
        compositeTrigger.put(composite.getType(), composite);
        
    }

    @FXML
    private void onChangeTrigger1(ActionEvent event) {
        // Reset UI elements based on trigger selection
        // ... (reset UI elements based on triggerComboBox selection)
        hourComboBox1.valueProperty().setValue(null);
        minuteComboBox1.valueProperty().setValue(null);
        dayOfWeekComboBox1.valueProperty().setValue(null);
        dayOfMonthComboBox1.valueProperty().setValue(null);
        datePicker1.valueProperty().setValue(null);
        exFileTextField1.textProperty().setValue(null);
        exFileButton1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("File Existance Verification"));
        fileDimensionTextField1.textProperty().setValue(null);
        fileDimensionButton1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("File Dimension Verification"));
        execProgramTextField1.textProperty().setValue(null);
        execProgramButton1.visibleProperty().bind(triggerComboBox1.valueProperty().isEqualTo("Program Exit Status Verification"));
        exFileDirectoryLabel1.visibleProperty().setValue(Boolean.FALSE);
        execProgramTextField1.textProperty().setValue(null);
        labelDimensionFile1.visibleProperty().setValue(Boolean.FALSE);
        fileDimensionTextField1.textProperty().setValue(null);
        logicalOperandComboBox.valueProperty().setValue("");
        if(triggerComboBox1.valueProperty().getValue().startsWith("Composite")){
            compositeTriggerLabel1.textProperty().setValue(compositeTrigger.get(triggerComboBox1.valueProperty().getValue()).toString());
            compositeTriggerLabel1.visibleProperty().setValue(Boolean.TRUE);
        } else
            compositeTriggerLabel1.visibleProperty().setValue(Boolean.FALSE);
    }

    @FXML
    private void onChangeTrigger2(ActionEvent event) {
        // Reset UI elements based on trigger selection
        // ... (reset UI elements based on triggerComboBox selection)
        hourComboBox2.valueProperty().setValue(null);
        minuteComboBox2.valueProperty().setValue(null);
        dayOfWeekComboBox2.valueProperty().setValue(null);
        dayOfMonthComboBox2.valueProperty().setValue(null);
        datePicker2.valueProperty().setValue(null);
        exFileTextField2.textProperty().setValue(null);
        exFileButton2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("File Existance Verification"));
        fileDimensionTextField2.textProperty().setValue(null);
        fileDimensionButton2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("File Dimension Verification"));
        execProgramTextField2.textProperty().setValue(null);
        execProgramButton2.visibleProperty().bind(triggerComboBox2.valueProperty().isEqualTo("Program Exit Status Verification"));
        exFileDirectoryLabel2.visibleProperty().setValue(Boolean.FALSE);
        execProgramTextField2.textProperty().setValue(null);
        labelDimensionFile2.visibleProperty().setValue(Boolean.FALSE);
        fileDimensionTextField2.textProperty().setValue(null);
        if(triggerComboBox2.valueProperty().getValue().startsWith("Composite")){
            compositeTriggerLabel2.textProperty().setValue(compositeTrigger.get(triggerComboBox2.valueProperty().getValue()).toString());
            compositeTriggerLabel2.visibleProperty().setValue(Boolean.TRUE);
        } else
            compositeTriggerLabel2.visibleProperty().setValue(Boolean.FALSE);
    }

    @FXML
    private void onChangeOperator(ActionEvent event) {
        triggerComboBox2.valueProperty().setValue("");
        notOperandComboBox2.valueProperty().setValue("");

    }

    @FXML
    private void onBackTrigger(ActionEvent event) {
        pane1.visibleProperty().setValue(Boolean.FALSE);
        anchorPane1.visibleProperty().setValue(Boolean.TRUE);
    }

    @FXML    
    private void onExecProgramButton(ActionEvent event) {
        Stage primaryStage = (Stage) execProgramButton.getScene().getWindow();

        //Setting the FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select the program to execute:");

        //Setting the filter for exe files
        FileChooser.ExtensionFilter exeFilter = new FileChooser.ExtensionFilter("Executable Files", "*.exe", "*.bat", "*.cmd", "*", "*.jar");
        fileChooser.getExtensionFilters().add(exeFilter);
        //Showing FileChooser and getting file path
        File selectedProgram = fileChooser.showOpenDialog(primaryStage);
        if (selectedProgram != null) {
            if (event.getSource() instanceof Button) {
                Button clickedButton = (Button) event.getSource();
                String buttonId = clickedButton.getId();
                if (buttonId.charAt(buttonId.length()-1) == '1'){
                    selectedProgramTriggerFile1 = selectedProgram.toPath();
                    exitValueProgramLabel1.textProperty().setValue(selectedProgramTriggerFile1.getFileName().toString());
                    exitValueProgramLabel1.visibleProperty().setValue(Boolean.TRUE);
                } else if (buttonId.charAt(buttonId.length()-1) == '2'){
                    selectedProgramTriggerFile2 = selectedProgram.toPath();
                    exitValueProgramLabel2.textProperty().setValue(selectedProgramTriggerFile2.getFileName().toString());
                    exitValueProgramLabel2.visibleProperty().setValue(Boolean.TRUE);
                } else {
                    selectedProgramTriggerFile = selectedProgram.toPath();
                    exitValueProgramLabel.textProperty().setValue(selectedProgramTriggerFile.getFileName().toString());
                    exitValueProgramLabel.visibleProperty().setValue(Boolean.TRUE);
                }
            }
        }
    }

    @FXML
    private void onCreateSequenceActionButton(ActionEvent event) {
        anchorPane1.visibleProperty().setValue(Boolean.FALSE);
        pane2.visibleProperty().setValue(Boolean.TRUE);
    }

    @FXML
    private void onDoneAction(ActionEvent event) {
    }
    
    @FXML
    private void onBackAction(ActionEvent event) {
        anchorPane1.visibleProperty().setValue(Boolean.TRUE);
        pane2.visibleProperty().setValue(Boolean.FALSE);
    }

    
}
