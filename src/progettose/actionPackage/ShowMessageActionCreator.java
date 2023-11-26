package progettose.actionPackage;

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
