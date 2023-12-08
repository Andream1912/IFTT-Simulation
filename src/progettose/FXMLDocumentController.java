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
import javafx.collections.ListChangeListener;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import progettose.actionPackage.Action;
import progettose.triggerPackage.Trigger;

public class FXMLDocumentController implements Initializable {

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
    private ContextMenu contextMenu;
    @FXML
    private MenuItem removeContextItem;
    @FXML
    private MenuItem deactivateContextItem;
    @FXML
    private Separator separatorCounter;
    @FXML
    
    private Button arrowButton;
    private RuleManagerProxy rmp;
    private TableView<Map.Entry<String, Integer>> counterTableView;
    private Label counterLabel;
    private CounterList counter = new CounterList();
    private Button addCounter;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Creating RuleManagerProxy instance
        counterTableView = new TableView<>();
        counterTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        counterLabel = new Label("Counters");
        counterLabel.setFont(new Font(22));
        rmp = RuleManagerProxy.getInstance();

        // Initializing TableView and its columns

        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        actionColumn.setCellValueFactory(new PropertyValueFactory("action"));
        triggerColumn.setCellValueFactory(new PropertyValueFactory("trigger"));
        typeColumn.setCellValueFactory(new PropertyValueFactory("ruleTypeDescription"));

        initializeTableColumns();

        // Binding TableView to the ObservableList
        tableView.setItems(rmp.getRules());

        // Disabling buttons when no row is selected
        removeRuleButton.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());
        toggleStateButton.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());

        // Periodic check for rules
        rmp.periodicCheck(tableView);

        // Creating and configuring the key and value columns for counterTableView table
        configureTWColumns();

        // Creating ContextMenu with "Delete" and "Add" options for the counterTableView table
        createTWContextMenu();
        addCounter = new Button("Add Counter");
        addCounter.setOnAction(this::addCounterButton);
    }

    private void initializeTableColumns() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        triggerColumn.setCellValueFactory(new PropertyValueFactory<>("trigger"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("ruleTypeDescription"));

        // Making columns non-resizable
        nameColumn.resizableProperty().setValue(Boolean.FALSE);
        actionColumn.resizableProperty().setValue(Boolean.FALSE);
        triggerColumn.resizableProperty().setValue(Boolean.FALSE);
        statusColumn.resizableProperty().setValue(Boolean.FALSE);
        typeColumn.resizableProperty().setValue(Boolean.FALSE);
        
        rmp.periodicCheck(tableView);      
        
        statusColumn.setCellValueFactory(new PropertyValueFactory<Rule,RuleState>("state"));
        
        statusColumn.setCellFactory(new Callback<TableColumn<Rule, RuleState>, TableCell<Rule, RuleState>>() {
        public TableCell call(TableColumn param) {
            return new TableCell<Rule, RuleState>() {

                @Override
                public void updateItem(RuleState item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!isEmpty()) {
                        this.setTextFill(Color.RED);
                        if(item.toString().equals("Active")) 
                            this.setTextFill(Color.GREEN);
                        setText(item.toString());
                    }
                }
            };
        }
        });
    }

    private void configureTWColumns() {
        // Creating key and value columns for the counterTableView table
        TableColumn<Map.Entry<String, Integer>, String> keyColumn = new TableColumn<>("Name");
        TableColumn<Map.Entry<String, Integer>, Integer> valueColumn = new TableColumn<>("Value");

        // Making columns non-resizable
        keyColumn.resizableProperty().setValue(Boolean.FALSE);
        valueColumn.resizableProperty().setValue(Boolean.FALSE);      

        // Enabling table editing
        counterTableView.setEditable(true);

        // Configuring the "Name" column cell to be editable
        keyColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        keyColumn.setOnEditCommit(event -> handleKeyColumnEditCommit(event));

        // Configuring the "Value" column cell to be editable
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
        valueColumn.setOnEditCommit(event -> handleValueColumnEditCommit(event));
        keyColumn.setCellValueFactory(entry -> new SimpleStringProperty(entry.getValue().getKey()));
        valueColumn.setCellValueFactory(entry -> new SimpleIntegerProperty(entry.getValue().getValue()).asObject());

       

        // Setting minimum and maximum width for columns
        keyColumn.setMinWidth(151);
        keyColumn.setMaxWidth(152);
        valueColumn.setMaxWidth(60);

        // Setting the columns for the counterTableView table
        counterTableView.getColumns().setAll(keyColumn, valueColumn);
    }

// Handles the event of editing the "Name" column.
    private void handleKeyColumnEditCommit(TableColumn.CellEditEvent<Map.Entry<String, Integer>, String> event) {
        // Retrieves the Map.Entry associated with the edited cell.
        Map.Entry<String, Integer> entry = event.getRowValue();
        // Obtains the new value entered for the "Name" column.
        String newKey = event.getNewValue();
        // Verifies that the newKey is a string (not an integer).
        // Verifies that the newKey contains only characters.
        if (newKey.matches("[a-zA-Z]+")) {
            // Calls the updateKeyName method in the CounterList class to update the key.
            counter.updateKeyName(entry.getKey(), newKey);
            // Updates the TableView with the modified data.
            updateTWTableWithData();
        } else {
            // Handle the case where newKey contains non-alphabetic characters.
            // You may display an error message or take appropriate action.
            handleInvalidInput("Invalid name. Please enter only strings");
            event.getTableView().refresh();
        }
    }

// Handle the event of editing the "Value" column
    private void handleValueColumnEditCommit(TableColumn.CellEditEvent<Map.Entry<String, Integer>, Integer> event) {
        Map.Entry<String, Integer> entry = event.getRowValue();
        Integer newValue = event.getNewValue();

        // Check if newValue is an instance of Integer
        if (newValue instanceof Integer) {
            // Update the counter value
            counter.setValue(entry.getKey(), newValue);
            updateTWTableWithData();
        } else {
            // Display an error alert and revert to the previous value
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Value");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid number for the 'Value' column.");
            alert.showAndWait();
            counter.setValue(entry.getKey(), entry.getValue());
            counterTableView.refresh();
        }
    }

    private void createTWContextMenu() {
        ContextMenu contextMenuTW = new ContextMenu();
        MenuItem deleteMenuItemTW = new MenuItem("Delete");
        MenuItem addMenuItemTW = new MenuItem("Add");

        // Event handler for deleting an item from the counterTableView table
        deleteMenuItemTW.setOnAction(event -> handleDeleteMenuItemTW());

        // Event handler for adding an item to the counterTableView table
        addMenuItemTW.setOnAction(event -> handleAddMenuItemTW());

        contextMenuTW.getItems().addAll(addMenuItemTW, deleteMenuItemTW);
        counterTableView.setContextMenu(contextMenuTW);
        deleteMenuItemTW.disableProperty().bind(counterTableView.getSelectionModel().selectedItemProperty().isNull());
        
    }

    private void handleDeleteMenuItemTW() {
        Map.Entry<String, Integer> selectedItem = counterTableView.getSelectionModel().getSelectedItem();
        counterTableView.getItems().remove(selectedItem);
        counter.removeCounter(selectedItem.getKey());
    }

    private void handleAddMenuItemTW() {
        TextInputDialog keyDialog = new TextInputDialog();
        keyDialog.setTitle("Add Item");
        keyDialog.setHeaderText("Enter the name of your counter:");

        Optional<String> keyResult = keyDialog.showAndWait();

        keyResult.ifPresent(name -> {
            if (!name.matches(".*\\d.*")) {
                if (counter.checkKey(name)) {
                    handleInvalidInput("The name already exists!");
                } else {
                    handleValidKey(name);
                }
            } else {
                handleInvalidInput("Invalid name. Please enter only strings.");
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
                counter.addCounter(name, itemValue);

                // Update the TableView
                ObservableList<Map.Entry<String, Integer>> items = FXCollections.observableArrayList(counter.getHashMap().entrySet());
                counterTableView.setItems(items);
            } catch (NumberFormatException e) {
                handleInvalidValue();
            }
        });
    }

    private void handleInvalidInput(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(s);
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
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Rule Creator");
        stage.setScene(new Scene(root1));
        stage.show();
        tableView.refresh();
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
        tableView.refresh();
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
    private void onShowCounter(ActionEvent event) {
        // Handling the 'Try' button click
        Stage stage = (Stage) anchorPane.getScene().getWindow();

        // Check if the counterTableView table is visible
        boolean isTableViewVisible = anchorPane.getChildren().contains(counterTableView);
        separatorCounter.visibleProperty().setValue(!isTableViewVisible);

        if (isTableViewVisible) {
            // If counterTableView table is visible, adjust the stage width and remove the counterTableView table and counterLabel
            adjustStageForVisibleTW(stage);
        } else {
            // If counterTableView table is not visible, adjust the stage width and add the counterTableView table and counterLabel
            adjustStageForInvisibleTW(stage);
            updateTWTableWithData();
        }
    }

    private void adjustStageForVisibleTW(Stage stage) {
        stage.setWidth(856.0);
        anchorPane.getChildren().removeAll(counterTableView, counterLabel, addCounter);
        arrowButton.setText("▶");
    }

    private void adjustStageForInvisibleTW(Stage stage) {
        stage.setWidth(1172.0);
        anchorPane.getChildren().addAll(counterTableView, counterLabel, addCounter);
        arrowButton.setText("◀");
        AnchorPane.setTopAnchor(counterTableView, 250.5);
        AnchorPane.setLeftAnchor(counterTableView, 905.0);
        AnchorPane.setRightAnchor(counterTableView, 48.0);
        AnchorPane.setBottomAnchor(counterTableView, 330.5);
        AnchorPane.setTopAnchor(addCounter, 390.0);
        AnchorPane.setLeftAnchor(addCounter, 905.0);
        AnchorPane.setRightAnchor(addCounter, 48.0);
        AnchorPane.setBottomAnchor(addCounter, 280.0);
        AnchorPane.setTopAnchor(counterLabel, 200.5);
        AnchorPane.setLeftAnchor(counterLabel, 969.0);
        AnchorPane.setRightAnchor(counterLabel, 48.0);
    }

    private void updateTWTableWithData() {
        ObservableList<Map.Entry<String, Integer>> items = FXCollections.observableArrayList(counter.getHashMap().entrySet());
        counterTableView.setItems(items);
    }
    
    @FXML
    private void addCounterButton(ActionEvent event) {
        handleAddMenuItemTW();
    }
}
