package progettose;

import java.io.File;
import java.net.URL;
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
    private ComboBox<Integer> hourComboBox;
    @FXML
    private ComboBox<Integer> minuteComboBox;
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
    /**
     * ************** Global Variable ***********
     */
    private String selectedFilePath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> triggerList = FXCollections.observableArrayList();
        triggerComboBox.setItems(triggerList);
        triggerList.addAll("Time", "Day of Week",
                "Day of Month", "Date",
                "File Existance Verification", "File Dimension Verification",
                "Program Exit Status Verification");

        ObservableList<String> actionList = FXCollections.observableArrayList();
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

        ObservableList<Integer> hourList = FXCollections.observableArrayList();
        hourComboBox.setItems(hourList);
        for (int i = 0; i <= 23; i++) {
            hourList.add(i);
        }

        ObservableList<Integer> minuteList = FXCollections.observableArrayList();
        minuteComboBox.setItems(minuteList);
        for (int i = 0; i <= 59; i++) {
            minuteList.add(i);
        }

        //saveButton.disableProperty().bind(actionComboBox.valueProperty().isNull());
        saveButton.disableProperty().bind(((hourComboBox.valueProperty().isNull().or(minuteComboBox.valueProperty().isNull()))
                .and(dayOfWeekComboBox.valueProperty().isNull())
                .and(dayOfMonthComboBox.valueProperty().isNull())
                .and(datePicker.valueProperty().isNull())
                //.and((exFileTextField.textProperty().isEmpty()).or())
                //.and((fileDimensionTextField.textProperty().isEmpty()).or())
                //.and((execProgramTextField.textProperty().isEmpty()).or())
                .or(triggerComboBox.valueProperty().isNull())));

        saveButton.disableProperty().bind(
                (showMessageTextArea.textProperty().isEmpty())
                        //.and()
                        //.and((appendToFileTextArea.textProperty().isEmpty()).or())
                        //.and((moveCopyTextField.textProperty().isEmpty()).or().or())
                        //.and((deleteTextField.textProperty().isEmpty()).or())
                        //.and((execArgumentsTextField.textProperty().isEmpty()).or())
                        .or(actionComboBox.valueProperty().isNull()));

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
    }

    @FXML
    private void onSave(ActionEvent event) {
        switch (triggerComboBox.getValue()) {
            case "Time":
                TriggerCreator timeTC = new TimeTriggerCreator(LocalTime.of(hourComboBox.getValue(), minuteComboBox.getValue()));
                Trigger timeT = timeTC.createTrigger();
                break;
            default:
                System.out.println("Trigger non valido");
        }
        switch (actionComboBox.getValue()) {
            case "PlayAudioButton": //* Da cambiare con il rispettivo nome
                ActionCreator playAudioAC = new PlayAudioActionCreator(selectedFilePath);
                Action playAudioA = playAudioAC.createAction();
                break;
            
            //When showMessageAction is selected, onSave the action is created with text from textArea
            case "ShowMessageAction": 
                ActionCreator showMessageAC = new ShowMessageActionCreator(showMessageTextArea.getText());
                Action showMessage = showMessageAC.createAction();
                break;

        }
    }

    @FXML
    private void onPlayAudioButton(ActionEvent event) {
        Stage primaryStage = (Stage) playAudioButton.getScene().getWindow();

        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Audio File");

        // Set the filter for audio files
        FileChooser.ExtensionFilter audioFilter = new FileChooser.ExtensionFilter("Audio Files", "*.mav");
        fileChooser.getExtensionFilters().add(audioFilter);

        // Show the FileChooser and get the selected file
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            selectedFilePath = selectedFile.getAbsolutePath();
        }
    }

    @FXML
    private void onChangeTrigger(ActionEvent event) {
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
    }

}
