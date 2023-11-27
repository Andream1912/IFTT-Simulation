package progettose;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import progettose.actionPackage.Action;
import progettose.actionPackage.ActionCreator;
import progettose.actionPackage.PlayAudioActionCreator;
import progettose.actionPackage.ShowMessageActionCreator;
import javafx.scene.control.TextFormatter;
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
    private SplitPane newRuleSplitPane;
    @FXML
    private Label fileAudioNameLabel;
    
    private FXMLDocumentController controllerOne;
    private Path selectedFilePath;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize ComboBox options
        // ... (initialize triggerList, actionList, dayOfWeekList, dayOfMonthList, hourList, minuteList)
        ObservableList<String> triggerList = FXCollections.observableArrayList();
        triggerComboBox.setItems(triggerList);
        triggerList.addAll("Time"/*, "Day of Week",
                "Day of Month", "Date",
                "File Existance Verification", "File Dimension Verification",
                "Program Exit Status Verification"*/);

        ObservableList<String> actionList = FXCollections.observableArrayList();
        actionComboBox.setItems(actionList);
        actionList.addAll("Show Message", "Play Audio"/*,
                "Append String to Textfile", "Move File",
                "Copy File", "Delete File",
                "Execute Program"*/);

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

        // Bind disable properties for the saveButton
        // ... (binding saveButton disable property based on various conditions)
        saveButton.disableProperty().bind(((hourComboBox.valueProperty().isNull().or(minuteComboBox.valueProperty().isNull()))
                .and(dayOfWeekComboBox.valueProperty().isNull())
                .and(dayOfMonthComboBox.valueProperty().isNull())
                .and(datePicker.valueProperty().isNull())
                //.and((exFileTextField.textProperty().isEmpty()).or())
                //.and((fileDimensionTextField.textProperty().isEmpty()).or())
                //.and((execProgramTextField.textProperty().isEmpty()).or())
                .or(triggerComboBox.valueProperty().isNull()))
        
                .or
                    (
                (showMessageTextArea.textProperty().isEmpty())
                        .and(fileAudioNameLabel.visibleProperty().not())
                        //.and((appendToFileTextArea.textProperty().isEmpty()).or())
                        //.and((moveCopyTextField.textProperty().isEmpty()).or().or())
                        //.and((deleteTextField.textProperty().isEmpty()).or())
                        //.and((execArgumentsTextField.textProperty().isEmpty()).or())
                        .or(actionComboBox.valueProperty().isNull()))
                .or(ruleNameTextField.textProperty().isEmpty()));
        
        fileAudioNameLabel.visibleProperty().setValue(Boolean.FALSE);

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
        showMessageTextArea.setTextFormatter(new TextFormatter<String>(change -> 
            change.getControlNewText().length() <= 1000 ? change : null));
        
        //Placeholder text for showMessageTextArea
        showMessageTextArea.setPromptText("Max 1000 characters...");
        
    }

    @FXML
    private void onSave(ActionEvent event) {
        // Create Trigger and Action based on selected options
        // ... (create Trigger and Action based on triggerComboBox and actionComboBox values)
        Trigger trigger = null;
        Action action = null;
        switch (triggerComboBox.getValue()) {
            case "Time":
                TriggerCreator timeTC = new TimeTriggerCreator(LocalTime.of(Integer.parseInt(hourComboBox.getValue()), Integer.parseInt(minuteComboBox.getValue()),0));
                trigger = timeTC.createTrigger();
                break;
            default:
                System.out.println("Not valid Trigger");
        }
        switch (actionComboBox.getValue()) {
            case "Play Audio": //* Da cambiare con il rispettivo nome
                ActionCreator playAudioAC = new PlayAudioActionCreator(selectedFilePath);
                action = playAudioAC.createAction();
                break;
            
            //When showMessageAction is selected, onSave the action is created with text from textArea
            case "Show Message": 
                ActionCreator showMessageAC = new ShowMessageActionCreator(showMessageTextArea.getText());
                action = showMessageAC.createAction();
                break;
            default:
                System.out.println("Not valid Action");
        }
        
        // Create a Rule using the created Trigger and Action
        Rule rule = new Rule(ruleNameTextField.textProperty().getValue(), action, trigger);
        
        // Add the created rule to the ObservableList in the first controller
        controllerOne.addRuleToObsList(rule);
        
        // Close the current stage (window)
        Stage currentStage = (Stage) saveButton.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void onPlayAudioButton(ActionEvent event) {
        Stage primaryStage = (Stage) playAudioButton.getScene().getWindow();

        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Audio File");

        // Set the filter for audio files
        FileChooser.ExtensionFilter audioFilter = new FileChooser.ExtensionFilter("Audio Files", "*.wav");
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
    }

    @FXML
    private void onChangeAction(ActionEvent event) {
        // Reset UI elements based on action selection
        // ... (reset UI elements based on actionComboBox selection)
    }
    
    public void setControllerOne(FXMLDocumentController controllerOne){
        //Sets the reference to the first controller
        this.controllerOne = controllerOne;
    }
}
