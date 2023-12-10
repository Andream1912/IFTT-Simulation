package progettose.actionPackage;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import progettose.counterPackage.CounterList;

public class SetCounterAction implements Action{
    
    //Attributes for SetCounterAction
    private final String counter;
    private final int counterValue;
    private final String type;
    private final CounterList countList;
    
    //Constructor for SetCounterAction
    public SetCounterAction(String counter, int counterValue){
        this.counter = counter;
        this.counterValue = counterValue;
        this.countList = new CounterList();
        this.type = "Set Value of Counter";
    }
    
    
    //toString method for SetCounterAction
    @Override
    public String toString(){
        return "Setting counter '" + this.counter + "' to the specified value '" + this.counterValue + "'";
    }
    
    
    @Override
    public void execute(){
        try{
            countList.setValue(this.counter, counterValue);
            Alert setCountAlert = new Alert(Alert.AlertType.NONE);
            setCountAlert.setTitle("Counter");
            setCountAlert.setContentText("'" + this.counter + "' has been set to value '" + this.counterValue + "'");
            ButtonType confButton = new ButtonType("Ok");
            setCountAlert.getButtonTypes().setAll(confButton);
            setCountAlert.showAndWait();
        } catch (Exception e){
            System.out.println("Error while setting '" + this.counter + "' to the specified value '" + this.counterValue + "' " + e.getStackTrace());
        }
    }
    
    @Override
    public String getType(){
        return this.type;
    }
    
    @Override 
    public String getToCSV(){
        return this.counter + ";" + this.counterValue;
    }
}
