package progettose;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Progettose extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        
        FXMLDocumentController controllerOne = fxmlLoader.getController();
        
        Scene scene = new Scene(root);
        stage.setTitle("MyIFTTT");
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
              controllerOne.endThread();
          }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
