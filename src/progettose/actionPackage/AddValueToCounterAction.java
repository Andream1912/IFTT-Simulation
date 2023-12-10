package progettose.actionPackage;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import progettose.counterPackage.CounterList;

public class AddValueToCounterAction implements Action{
    
    //Attributes for AddValueToCounterAction
    private final String counter;
    private final int valueToAdd;
    private final String type;
    
    //Constructor for AddValueToCounterAction
    public AddValueToCounterAction(String counter, int valueToAdd){
        this.counter = counter;
        this.valueToAdd = valueToAdd;
        this.type = "Add Value to Counter";
    }
    
    //Getters for counter and valueToAdd
    public String getCounter(){
        return this.counter;
    }
    
    public int getValueToAdd(){
        return this.valueToAdd;
    }
    
    //toString for AddValueToCounterAction
    @Override 
    public String toString(){
        return "Adding value '" + this.valueToAdd + "' to counter '" + this.counter + "'";
    }
    
    @Override
    public void execute(){
        try{
            CounterList countList = new CounterList();
            int currentValue = countList.getCounter(this.counter);
            countList.setValue(this.counter, currentValue + this.valueToAdd);
            Alert addValueCountAlert = new Alert(Alert.AlertType.NONE);
            addValueCountAlert.setTitle("Counter");
            addValueCountAlert.setContentText("'" + this.valueToAdd + "' has been added to counter '" + this.counter + "'");
            ButtonType confButton = new ButtonType("Ok");
            addValueCountAlert.getButtonTypes().setAll(confButton);
            addValueCountAlert.showAndWait();
        } catch (Exception e){
            System.out.println("Error while adding '" + this.valueToAdd + "' to counter '" + this.counter + "' " + e.getStackTrace());
        }
    }
    
    @Override
    public String getType(){
        return this.type;
    }
    
    @Override
    public String getToCSV(){
        return this.counter + ";" + this.valueToAdd;
    }
    
}