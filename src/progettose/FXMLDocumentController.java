package progettose;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import progettose.actionPackage.Action;
import progettose.actionPackage.ShowMessageAction;
import progettose.triggerPackage.TimeTrigger;
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
    private SplitPane splitPane;

    private RuleManager rm;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Creating RuleManager object
        rm = RuleManager.getInstance();

        // Initializing TableView and its columns
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        actionColumn.setCellValueFactory(new PropertyValueFactory("action"));
        triggerColumn.setCellValueFactory(new PropertyValueFactory("trigger"));
        statusColumn.setCellValueFactory(new PropertyValueFactory("state"));
       
        
        
        // Making columns non-resizable
        nameColumn.resizableProperty().setValue(Boolean.FALSE);
        actionColumn.resizableProperty().setValue(Boolean.FALSE);
        triggerColumn.resizableProperty().setValue(Boolean.FALSE);
        statusColumn.resizableProperty().setValue(Boolean.FALSE);

        // Binding TableView to the ObservableList        
        tableView.setItems(rm.getRules());

        // Adding a sample rule to the TableView
        rm.addRule(new Rule("Promemoria 1", new ShowMessageAction("Portare giu il cane"), new TimeTrigger(LocalTime.of(12, 30))));
        rm.addRule(new Rule("Promemoria 2", new ShowMessageAction("Vai a fare la spesa"), new TimeTrigger(LocalTime.of(10, 15))));

        // Disabling removeRuleButton and toggleStateButton when no row is selected
        removeRuleButton.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());
        toggleStateButton.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());
        
        
        // Setting a position for the SplitPane divider
        splitPane.getDividers().get(0).positionProperty().addListener((observable, oldValue, newValue) -> {
            splitPane.setDividerPosition(0, 0.3);
        });
        rm.periodicCheck();
        
        
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
        ButtonType confButton = new ButtonType("Remove");
        ButtonType cancelButton = new ButtonType("Cancel");
        messageBox.getButtonTypes().setAll(cancelButton, confButton);
        messageBox.setTitle("Warning");
        messageBox.setContentText("Do you want to permanently remove the rule?");
        // Display the Alert and wait for user to decide if removing the rule or not
        messageBox.showAndWait().ifPresent(buttonType -> {
            if(buttonType == confButton){
                rm.removeRule(tableView.getSelectionModel().selectedItemProperty().getValue());
                tableView.getSelectionModel().clearSelection();
            }
        });
    
        
    }

    @FXML
    private void onToggleState(ActionEvent event) {
        Rule r = tableView.getSelectionModel().selectedItemProperty().getValue();
        if(r.isActive()){
            rm.deactivateRule(r);
            toggleStateButton.textProperty().setValue("Activate Rule");
            
        }else{
            rm.activateRule(r);
            toggleStateButton.textProperty().setValue("Deactivate Rule");
        }
        tableView.refresh();
    }
    
    public void addRuleToObsList(Rule r) {
        rm.addRule(r);
    }
    
    public void endThread(){
        RuleManager.shutdownScheduler();
    }

    @FXML
    private void unselectRow(MouseEvent event) {
        // Handling the event to clear the selection when clicking outside the table row
        tableView.getSelectionModel().clearSelection();
    }

    @FXML
    private void onSelectRule(MouseEvent event) {
        Rule r = tableView.getSelectionModel().selectedItemProperty().getValue();
        if(r.isActive())
            toggleStateButton.textProperty().setValue("Deactivate Rule");
        else
            toggleStateButton.textProperty().setValue("Activate Rule");

        
    }

}


