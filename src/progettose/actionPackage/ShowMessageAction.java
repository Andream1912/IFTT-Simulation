package progettose.actionPackage;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ShowMessageAction implements Action{
    
    //Defining String attribute for message action
    private String message;
    
    //Constructor
    public ShowMessageAction(String message){
        
        this.message=message;
        
    }
    
    //Method for getting message
    public String getMessage(){
        
        return this.message;
        
    }
    
    //Method for setting message
    public void setMessage(String temp){
        
        this.message=temp;
        
    }
    
    @Override
    public void execute(){
        
         //Definition of alert for showing user message
        Alert messageBox = new Alert(AlertType.NONE);
        
        ButtonType confButton = new ButtonType("Close");
        
        messageBox.getButtonTypes().setAll(confButton);
        
        messageBox.setTitle("Message");
        
        messageBox.setContentText(this.message);
        
        //Box will remain until user presses OK button
        messageBox.showAndWait();
    }
    
}
