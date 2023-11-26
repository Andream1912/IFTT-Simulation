package progettose.actionPackage;

public class ShowMessageActionCreator extends ActionCreator{

    // The message to be displayed by the ShowMessageAction
    private String message;

    // Constructor to initialize ShowMessageActionCreator with a specific message.
    public ShowMessageActionCreator(String message) {
        this.message = message;
    }

    // Creates and returns a new instance of ShowMessageAction with the configured message.
    @Override
    public Action createAction() {
        return new ShowMessageAction(this.message);
    }
}
