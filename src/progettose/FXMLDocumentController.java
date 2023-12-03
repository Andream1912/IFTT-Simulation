package progettose;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import progettose.actionPackage.Action;
import progettose.triggerPackage.Trigger;

public class FXMLDocumentController implements Initializable {

    // Injected JavaFX elements
    @FXML
    private Button addRuleButton;
    @FXML
    private Button removeRuleButton;
    @FXML
    private Button toggleStateButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Rule> tableView;
    @FXML
    private TableColumn<Rule, String> nameColumn;
    @FXML
    private TableColumn<Rule, Action> actionColumn;
    @FXML
    private TableColumn<Rule, Trigger> triggerColumn;
    @FXML
    private TableColumn<Rule, RuleState> statusColumn;
    @FXML
    private TableColumn<Rule, String> typeColumn;
    private RuleManagerProxy rmp;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Creating RuleManager object
        rmp = RuleManagerProxy.getInstance();

        // Initializing TableView and its columns
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        actionColumn.setCellValueFactory(new PropertyValueFactory("action"));
        triggerColumn.setCellValueFactory(new PropertyValueFactory("trigger"));
        statusColumn.setCellValueFactory(new PropertyValueFactory("state"));
        typeColumn.setCellValueFactory(new PropertyValueFactory("ruleTypeDescription"));

        // Making columns non-resizable
        nameColumn.resizableProperty().setValue(Boolean.FALSE);
        actionColumn.resizableProperty().setValue(Boolean.FALSE);
        triggerColumn.resizableProperty().setValue(Boolean.FALSE);
        statusColumn.resizableProperty().setValue(Boolean.FALSE);

        // Binding TableView to the ObservableList        
        tableView.setItems(rmp.getRules());

        // Disabling removeRuleButton and toggleStateButton when no row is selected
        removeRuleButton.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());
        toggleStateButton.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());

        rmp.periodicCheck(tableView);      

    }

    @FXML
    private void onAddRule(ActionEvent event) throws IOException {
        // Handling the 'Add Rule' button click
        // It will open a new Window where the user can create a new rule
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocumentTwo.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        FXMLDocumentTwoController controllerTwo = fxmlLoader.getController();
        controllerTwo.setControllerOne(this);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Rule Creator");
        stage.setScene(new Scene(root1));
        stage.show();

    }

    @FXML
    private void onRemoveRule(ActionEvent event) {
        // Handling the 'Remove Rule' button click

        //Display warning message for removing rule
        Alert messageBox = new Alert(Alert.AlertType.NONE);
        ButtonType confButton = new ButtonType("Yes");
        ButtonType cancelButton = new ButtonType("No");
        messageBox.getButtonTypes().setAll(confButton, cancelButton);
        messageBox.setTitle("Warning");
        messageBox.setContentText("Do you want to permanently remove the rule?");
        // Display the Alert and wait for user to decide if removing the rule or not
        messageBox.showAndWait().ifPresent(buttonType -> {
            if (buttonType == confButton) {
                rmp.removeRule(tableView.getSelectionModel().selectedItemProperty().getValue());
                tableView.getSelectionModel().clearSelection();
            }
        });

    }

    @FXML
    private void onToggleState(ActionEvent event) {
        Rule r = tableView.getSelectionModel().selectedItemProperty().getValue();
        if (r.isActive()) {
            rmp.deactivateRule(r);
            toggleStateButton.textProperty().setValue("Activate Rule");

        } else {
            rmp.activateRule(r);
            toggleStateButton.textProperty().setValue("Deactivate Rule");
        }
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }

    public void addRuleToObsList(Rule r) {
        rmp.addRule(r);
    }

    public void endThread() {
        RuleManagerProxy.shutdownScheduler();
    }

    @FXML
    private void unselectRow(MouseEvent event) {
        // Handling the event to clear the selection when clicking outside the table row
        tableView.getSelectionModel().clearSelection();
    }

    @FXML
    private void onSelectRule(MouseEvent event) {
        Rule r = tableView.getSelectionModel().selectedItemProperty().getValue();
        if (r != null) {
            if (r.isActive()) {
                toggleStateButton.textProperty().setValue("Deactivate Rule");
            } else {
                toggleStateButton.textProperty().setValue("Activate Rule");
            }
        }

    }

}
