package progettose;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.DirectoryChooser;
import progettose.actionPackage.CopyFileActionCreator;
import progettose.actionPackage.DeleteFileActionCreator;
import progettose.actionPackage.FileAppenderActionCreator;
import progettose.actionPackage.MoveFileActionCreator;
import progettose.triggerPackage.DateTriggerCreator;
import progettose.triggerPackage.DayOfMonthTriggerCreator;
import progettose.triggerPackage.DayOfWeekTriggerCreator;
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
    
    private Path selectedFileForDimension;
    private FXMLDocumentController controllerOne;
    private Path selectedFilePath;
    private Path selectedSourcePath;
    private Path selectedDestinationPath;
    private Path selectedDeleteSourcePath;
    private Path selectedExFile;
    private Path selectedAppendFile;


    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // Initialize ComboBox options
        // ... (initialize triggerList, actionList, dayOfWeekList, dayOfMonthList, hourList, minuteList)
        ObservableList<String> triggerList = FXCollections.observableArrayList();
        triggerComboBox.setItems(triggerList);
        triggerList.addAll("Time", "Day of Week", "Day of Month",
                "Date",
                "File Existance Verification", "File Dimension Verification" /*,
                "Program Exit Status Verification"*/);

        ObservableList<String> actionList = FXCollections.observableArrayList();
        actionComboBox.setItems(actionList);
        actionList.addAll("Show Message", "Play Audio",
                "Append String to Textfile"/*, "Move File"/*,
                 */, "Copy File", "Delete File"/*,
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
                //.and((execProgramTextField.textProperty().isEmpty()).or())
                .or(triggerComboBox.valueProperty().isNull()))
                .or(
                        (showMessageTextArea.textProperty().isEmpty())
                                .and(fileAudioNameLabel.visibleProperty().not())
                                .and((appendToFileTextArea.textProperty().isEmpty()).or(fileToAppendLabel.visibleProperty().not()))
                                .and((moveCopyTextField.textProperty().isEmpty()).or(selectedSourceDirectoryLabel.visibleProperty().not()).or(selectedDestinationDirectoryLabel.visibleProperty().not()))
                                .and((deleteTextField.textProperty().isEmpty()).or(deleteFileLabel.visibleProperty().not()))
                                //.and((execArgumentsTextField.textProperty().isEmpty()).or())
                                .or(actionComboBox.valueProperty().isNull()))
                .or(ruleNameTextField.textProperty().isEmpty())
        );

        fileAudioNameLabel.visibleProperty().setValue(Boolean.FALSE);
        exFileDirectoryLabel.visibleProperty().setValue(Boolean.FALSE);
        labelDimensionFile.visibleProperty().setValue(Boolean.FALSE);
        
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

                // Disabilita le date precedenti a oggi
                setDisable(date.isBefore(LocalDate.now()));
            }
        });
    }

    private Trigger checkTrigger(String s) {
        switch (s) {
            case "Time":
                TriggerCreator timeTC = new TimeTriggerCreator(LocalTime.of(Integer.parseInt(hourComboBox.getValue()), Integer.parseInt(minuteComboBox.getValue()), 0));
                return timeTC.createTrigger();
            case "Day of Week":
                TriggerCreator dayOfWeekTC = new DayOfWeekTriggerCreator(dayOfWeekComboBox.getValue());
                return dayOfWeekTC.createTrigger();
            case "Day of Month":
                TriggerCreator dayOfMonthTC = new DayOfMonthTriggerCreator(dayOfMonthComboBox.getValue());
                return dayOfMonthTC.createTrigger();
            case "Date":
                TriggerCreator dateTC = new DateTriggerCreator(datePicker.getValue());
                return dateTC.createTrigger();
            case "File Dimension Verification":
                TriggerCreator fileDimensionTC = new FileSizeCheckerTriggerCreator(selectedFileForDimension, Long.parseLong(fileDimensionTextField.textProperty().getValue()), typeDimensionComboBox.getValue());
                return fileDimensionTC.createTrigger();
            case "File Existance Verification":
                TriggerCreator fileExTC = new FileCheckTriggerCreator(selectedExFile.toString(), exFileTextField.getText());
                return fileExTC.createTrigger();
            default:
                System.out.println("Not valid Trigger");
                return null;
        }
    }

    private Action checkAction(String s) {
        switch (s) {
            case "Play Audio": //* Da cambiare con il rispettivo nome
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
        Trigger trigger = checkTrigger(triggerComboBox.getValue());
        Action action = checkAction(actionComboBox.getValue());
        Rule r;
        //When copyFileAction is selected, onSave the action is created from the different input fields
        // Create a Rule using the created Trigger and Action
        if(fireSleepingTimeCheckBox.selectedProperty().getValue())
            r = new SleepingTimeRule(ruleNameTextField.textProperty().getValue(), action, trigger, daySleepingTimeSpinner.valueProperty().getValue(), hourSleepingTimeSpinner.valueProperty().getValue(), minuteSleepingTimeSpinner.valueProperty().getValue());
        else
            r = new FireOnceRule(ruleNameTextField.textProperty().getValue(), action, trigger);
        //else
            //rule = new Rule(ruleNameTextField.textProperty().getValue(), action, trigger);

        // Add the created rule to the ObservableList in the first controller
        controllerOne.addRuleToObsList(r);

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
        fileToAppendLabel.visibleProperty().setValue(Boolean.FALSE);

    }

    public void setControllerOne(FXMLDocumentController controllerOne) {
        //Sets the reference to the first controller
        this.controllerOne = controllerOne;
    }

    @FXML
    private void onFileDimensionButton(ActionEvent event) {
        Stage primaryStage = (Stage) playAudioButton.getScene().getWindow();

        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a File");
        // Show the FileChooser and get the selected file
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            selectedFileForDimension = selectedFile.toPath();
            labelDimensionFile.textProperty().setValue(selectedFileForDimension.getFileName().toString());
            labelDimensionFile.setVisible(true);

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
            selectedExFile = checkFile.toPath();
            exFileDirectoryLabel.textProperty().setValue(selectedExFile.getFileName().toString());
            exFileDirectoryLabel.visibleProperty().setValue(Boolean.TRUE);
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

        // Add an additional filter for all files
        FileChooser.ExtensionFilter allFilter = new FileChooser.ExtensionFilter("All files", "*.*");
        fileChooser.getExtensionFilters().add(allFilter);

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
        if (!(event.getCharacter()).matches("^\\d*")){
            event.consume();
        }
    }
}
