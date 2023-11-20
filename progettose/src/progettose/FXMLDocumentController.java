/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package progettose;

import progettose.triggerPackage.Trigger;
import progettose.triggerPackage.TimeTriggerCreator;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author andrea
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button toggleButton;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
        
        System.out.println("prova");
        LocalTime triggerTime = LocalTime.of(12, 30);
        TimeTriggerCreator timeTriggerCreator = new TimeTriggerCreator(triggerTime);
        Trigger trigger = timeTriggerCreator.createTrigger();
        trigger.evaluate();
    }    
    
}
