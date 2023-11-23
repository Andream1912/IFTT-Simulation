package progettose.actionPackage;

import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayAudioAction implements Action {

    public String path;

    //the path will be the path of the file choosed
    public PlayAudioAction(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void execute() {

        try {
            File file = new File(this.path);
            if (file.exists()) {
                Media media = new Media(file.toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setOnEndOfMedia(() -> {
                    mediaPlayer.seek(mediaPlayer.getStartTime());
                });

                Alert alert = new Alert(AlertType.NONE);
                alert.setTitle("Stop Music");
                alert.setHeaderText("Do you want to stop the music?");
                alert.setContentText("Press OK to stop the music.");

                // Customize the buttons with a custom button text
                ButtonType buttonTypeStop = new ButtonType("Stop Now", ButtonData.OK_DONE);
                alert.getButtonTypes().setAll(buttonTypeStop);
                mediaPlayer.play();
                alert.showAndWait();
                mediaPlayer.stop();

            } else {
                System.out.println("File not found: " + this.path);
            }
        } catch (Exception e) {
            System.out.println("Error during audio playback: " + e.getMessage());
        }
    }
}
