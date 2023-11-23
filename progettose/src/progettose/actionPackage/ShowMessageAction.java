/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose.actionPackage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author smuel
 */
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
        
        messageBox.setContentText(this.message);
        
        //Box will remain until user presses OK button
        messageBox.showAndWait();
    }
    
}
