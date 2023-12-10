package progettose.actionPackage;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import progettose.counterPackage.CounterList;

public class AddCounterToCounterAction implements Action{
    
    //Attributes for AddCounterToCounterAction
    private final String counter;
    private final String counterToAdd;
    private final String type;
    
    //Constructor for AddCounterToCounterAction
    public AddCounterToCounterAction(String counter, String counterToAdd){
        this.counter = counter;
        this.counterToAdd = counterToAdd;
        this.type = "Add Value of Counter to Counter";
    }
    
    //toString method for AddCounterToCounterAction
    @Override
    public String toString(){
        return "Adding value of '" + this.counterToAdd + "' to counter '" + this.counter + "'";
    }
    
    @Override 
    public void execute(){
        try{
            CounterList countList = new CounterList();
            int currentValue = countList.getCounter(this.counter);
            int valueToAdd = countList.getCounter(this.counterToAdd);
            countList.setValue(this.counter, currentValue + valueToAdd);
            Alert addCountToCountAlert = new Alert(Alert.AlertType.NONE);
            addCountToCountAlert.setTitle("Counter");
            addCountToCountAlert.setContentText("'" + this.counterToAdd + "' value has been added to counter '" + this.counter + "'");
            ButtonType confButton = new ButtonType("Ok");
            addCountToCountAlert.getButtonTypes().setAll(confButton);
            addCountToCountAlert.showAndWait();
        } catch (Exception e){
            System.out.println("Error while adding counter '" + this.counterToAdd + "' to counter '" 
                    + this.counter + "'" + e.getStackTrace());
        }
    }
    
    @Override
    public String getType(){
        return this.type;
    }
    
    @Override
    public String getToCSV(){
        return this.counter + ";" + this.counterToAdd;
    }
}
