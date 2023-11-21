package progettose;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class FXMLDocumentTwoController implements Initializable {

    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField exFileTextField;
    @FXML
    private Button exFileButton;
    @FXML
    private TextField dimensionFileTextField;
    @FXML
    private Button dimensionFileButton;
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
    private ComboBox<?> actionComboBox;
    @FXML
    private Button saveButton;

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
        for (int i = 1; i <= 24; i++) {
            hourList.add(i);
        }

        ObservableList<Integer> minuteList = FXCollections.observableArrayList();
        minuteComboBox.setItems(minuteList);
        for (int i = 0; i <= 60; i++) {
            minuteList.add(i);
        }

    }

    @FXML
    private void onSave(ActionEvent event) {
    }

}
