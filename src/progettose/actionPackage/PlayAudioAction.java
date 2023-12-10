package progettose.actionPackage;

import java.io.File;
import java.nio.file.Path;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayAudioAction implements Action {

    // The path to the audio file
    private final Path path;
    private final String type;

    public PlayAudioAction(Path path) {
        this.path = path;
        this.type = "Play Audio";
    }

    public Path getPath() {
        return this.path;
    }

    @Override
    public String getType() {
        return this.type;
    }

    //Executes the action, playing the audio file
    @Override
    public void execute() {
            File file = new File(this.path.toString());
            if (file.exists()) {

                // Create a media object from the audio file
                Media media = new Media(file.toURI().toString());
                // Create a media player for the specified media
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                // Configure the media player to loop when the end of media is reached
                mediaPlayer.setOnEndOfMedia(() -> {
                    mediaPlayer.seek(mediaPlayer.getStartTime());
                });

                // Display an alert to stop the music
                Alert alert = new Alert(AlertType.NONE);
                alert.setTitle("Stop Music");
                alert.setHeaderText("Do you want to stop the music?");
                alert.setContentText("Press OK to stop the music.");

                // Customize the buttons with a custom button text
                ButtonType buttonTypeStop = new ButtonType("Stop Now", ButtonData.OK_DONE);
                alert.getButtonTypes().setAll(buttonTypeStop);

                // Play the audio file and show the alert
                mediaPlayer.play();
                alert.showAndWait();
                mediaPlayer.stop();

            } else {
                // Handle the case where the audio file is not found
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Audio Action ");
                alert.setHeaderText("File not found!");
                alert.showAndWait();
            }
    }

    @Override
    public String toString() {
        return "Play audio:\n" + path.getFileName();
    }

    @Override
    public String getToCSV() {
        return this.getPath().toString();
    }
}
