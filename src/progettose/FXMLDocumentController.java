package progettose;

import progettose.counterPackage.CounterList;
import progettose.rulePackage.Rule;
import progettose.rulePackage.RuleState;
import progettose.rulePackage.RuleManagerProxy;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import progettose.actionPackage.Action;
import progettose.triggerPackage.Trigger;

public class FXMLDocumentController implements Initializable {

    // JavaFX elements injected from FXML
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
    @FXML
    private VBox vBoxSCAM;
    private TableView<Map.Entry<String, Integer>> tW;
    private RuleManagerProxy rmp;
    private Label counterLabel;
    @FXML
    private Separator separatorCounter;
    @FXML
    private Button arrowButton;

    private CounterList counter = new CounterList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Creating RuleManagerProxy instance
        tW = new TableView<>();
        tW.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        counterLabel = new Label("Counters");
        counterLabel.setFont(new Font(22));
        rmp = RuleManagerProxy.getInstance();

        // Initializing TableView and its columns
        initializeTableColumns();

        // Binding TableView to the ObservableList
        tableView.setItems(rmp.getRules());

        // Disabling buttons when no row is selected
        removeRuleButton.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());
        toggleStateButton.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());

        // Periodic check for rules
        rmp.periodicCheck(tableView);

        // Creating and configuring the key and value columns for tW table
        configureTWColumns();

        // Creating ContextMenu with "Delete" and "Add" options for the tW table
        createTWContextMenu();
    }

    private void initializeTableColumns() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        triggerColumn.setCellValueFactory(new PropertyValueFactory<>("trigger"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("ruleTypeDescription"));

        // Making columns non-resizable
        nameColumn.resizableProperty().setValue(Boolean.FALSE);
        actionColumn.resizableProperty().setValue(Boolean.FALSE);
        triggerColumn.resizableProperty().setValue(Boolean.FALSE);
        statusColumn.resizableProperty().setValue(Boolean.FALSE);
        typeColumn.resizableProperty().setValue(Boolean.FALSE);
    }

    private void configureTWColumns() {
        TableColumn<Map.Entry<String, Integer>, String> keyColumn = new TableColumn<>("Name");
        TableColumn<Map.Entry<String, Integer>, Integer> valueColumn = new TableColumn<>("Value");

        keyColumn.resizableProperty().setValue(Boolean.FALSE);
        valueColumn.resizableProperty().setValue(Boolean.FALSE);

        keyColumn.setCellValueFactory(entry -> new SimpleStringProperty(entry.getValue().getKey()));
        valueColumn.setCellValueFactory(entry -> new SimpleIntegerProperty(entry.getValue().getValue()).asObject());

        keyColumn.setMinWidth(125);
        keyColumn.setMaxWidth(136);
        valueColumn.setMaxWidth(60);

        tW.getColumns().setAll(keyColumn, valueColumn);
    }

    private void createTWContextMenu() {
        ContextMenu contextMenuTW = new ContextMenu();
        MenuItem deleteMenuItemTW = new MenuItem("Delete");
        MenuItem addMenuItemTW = new MenuItem("Add");

        // Event handler for deleting an item from the tW table
        deleteMenuItemTW.setOnAction(event -> handleDeleteMenuItemTW());

        // Event handler for adding an item to the tW table
        addMenuItemTW.setOnAction(event -> handleAddMenuItemTW());

        contextMenuTW.getItems().addAll(addMenuItemTW, deleteMenuItemTW);
        tW.setContextMenu(contextMenuTW);
    }

    private void handleDeleteMenuItemTW() {
        Map.Entry<String, Integer> selectedItem = tW.getSelectionModel().getSelectedItem();
        tW.getItems().remove(selectedItem);
        counter.removeValue(selectedItem.getKey());
    }

    private void handleAddMenuItemTW() {
        TextInputDialog keyDialog = new TextInputDialog();
        keyDialog.setTitle("Add Item");
        keyDialog.setHeaderText("Enter the name of your counter:");

        Optional<String> keyResult = keyDialog.showAndWait();

        keyResult.ifPresent(name -> {
            if (!name.matches(".*\\d.*")) {
                handleValidKey(name);
            } else {
                handleInvalidKey();
            }
        });
    }

    private void handleValidKey(String name) {
        TextInputDialog valueDialog = new TextInputDialog();
        valueDialog.setTitle("Add Item");
        valueDialog.setHeaderText("Enter the value for the counter " + name + ":");

        Optional<String> valueResult = valueDialog.showAndWait();

        valueResult.ifPresent(v -> {
            try {
                int itemValue = Integer.parseInt(v);
                counter.addValue(name, itemValue);

                // Update the TableView
                ObservableList<Map.Entry<String, Integer>> items = FXCollections.observableArrayList(counter.getHashMap().entrySet());
                tW.setItems(items);
            } catch (NumberFormatException e) {
                handleInvalidValue();
            }
        });
    }

    private void handleInvalidKey() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Invalid name. Please enter only strings.");
        alert.showAndWait();
    }

    private void handleInvalidValue() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Invalid value. Please enter a valid number.");
        alert.showAndWait();
    }

    @FXML
    private void onAddRule(ActionEvent event) throws IOException {
        // Handling the 'Add Rule' button click
        // It opens a new window where the user can create a new rule
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
        // Display warning message for removing the rule
        Alert messageBox = new Alert(Alert.AlertType.NONE);
        ButtonType confButton = new ButtonType("Yes");
        ButtonType cancelButton = new ButtonType("No");
        messageBox.getButtonTypes().setAll(confButton, cancelButton);
        messageBox.setTitle("Warning");
        messageBox.setContentText("Do you want to permanently remove the rule?");
        // Display the Alert and wait for the user to decide whether to remove the rule or not
        messageBox.showAndWait().ifPresent(buttonType -> {
            if (buttonType == confButton) {
                rmp.removeRule(tableView.getSelectionModel().selectedItemProperty().getValue());
                tableView.getSelectionModel().clearSelection();
            }
        });
    }

    @FXML
    private void onToggleState(ActionEvent event) {
        // Handling the 'Toggle State' button click
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

// Method to add a rule to the ObservableList
    public void addRuleToObsList(Rule r) {
        rmp.addRule(r);
    }

// Method to end the scheduler thread
    public void endThread() {
        RuleManagerProxy.shutdownScheduler();
    }

// Method to check if a rule name already exists
    public boolean checkNameRule(String s) {
        for (Rule r : rmp.getRules()) {
            if (r.getName().equals(s)) {
                return false;
            }
        }
        return true;
    }

    @FXML
    private void unselectRow(MouseEvent event) {
        // Handling the event to clear the selection when clicking outside the table row
        tableView.getSelectionModel().clearSelection();
    }

    @FXML
    private void onSelectRule(MouseEvent event) {
        // Handling the event when a rule is selected
        Rule r = tableView.getSelectionModel().selectedItemProperty().getValue();
        if (r != null) {
            if (r.isActive()) {
                toggleStateButton.textProperty().setValue("Deactivate Rule");
            } else {
                toggleStateButton.textProperty().setValue("Activate Rule");
            }
        }
    }

    @FXML
    private void onTry(ActionEvent event) {
        // Handling the 'Try' button click
        Stage stage = (Stage) anchorPane.getScene().getWindow();

        // Check if the tW table is visible
        boolean isTableViewVisible = anchorPane.getChildren().contains(tW);
        separatorCounter.visibleProperty().setValue(!isTableViewVisible);

        if (isTableViewVisible) {
            // If tW table is visible, adjust the stage width and remove the tW table and counterLabel
            adjustStageForVisibleTW(stage);
        } else {
            // If tW table is not visible, adjust the stage width and add the tW table and counterLabel
            adjustStageForInvisibleTW(stage);
            updateTWTableWithData();
        }
    }

    private void adjustStageForVisibleTW(Stage stage) {
        stage.setWidth(868.0);
        anchorPane.getChildren().removeAll(tW, counterLabel);
        arrowButton.setText("->");
        AnchorPane.setLeftAnchor(vBoxSCAM, 0.0);
        AnchorPane.setRightAnchor(vBoxSCAM, 0.0);
    }

    private void adjustStageForInvisibleTW(Stage stage) {
        stage.setWidth(1222.0);
        anchorPane.getChildren().addAll(tW, counterLabel);
        arrowButton.setText("<-");
        AnchorPane.setTopAnchor(tW, 250.5);
        AnchorPane.setLeftAnchor(tW, 946.0);
        AnchorPane.setRightAnchor(tW, 70.0);
        AnchorPane.setBottomAnchor(tW, 330.5);
        AnchorPane.setLeftAnchor(vBoxSCAM, 10.0);
        AnchorPane.setRightAnchor(vBoxSCAM, 300.0);
        AnchorPane.setTopAnchor(counterLabel, 200.5);
        AnchorPane.setLeftAnchor(counterLabel, 1000.0);
        AnchorPane.setRightAnchor(counterLabel, 70.0);
    }

    private void updateTWTableWithData() {
        ObservableList<Map.Entry<String, Integer>> items = FXCollections.observableArrayList(counter.getHashMap().entrySet());
        tW.setItems(items);
    }
}
