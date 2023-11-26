package progettose.actionPackage;

import java.nio.file.Path;

public class PlayAudioActionCreator extends ActionCreator {

    // The path to the audio file for the PlayAudioAction
    private Path path;

    // Constructor to initialize PlayAudioActionCreator with the specified audio file path
    public PlayAudioActionCreator(Path path) {
        this.path = path;
    }

    // Creates and returns a new instance of PlayAudioAction with the configured audio file path
    @Override
    public Action createAction() {
        return new PlayAudioAction(this.path);
    }

}
