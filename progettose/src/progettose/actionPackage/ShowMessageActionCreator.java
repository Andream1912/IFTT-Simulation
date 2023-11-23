/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose.actionPackage;

/**
 *
 * @author smuel
 */
public class ShowMessageActionCreator extends ActionCreator{
    
    private String message;
    
    public ShowMessageActionCreator(String message){
        
        this.message=message;
    }
    
    @Override
    public Action createAction(){
        
        return new ShowMessageAction(this.message);
        
    }
    
}
