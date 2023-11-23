package progettose.actionPackage;

public class PlayAudioActionCreator extends ActionCreator {

    private String path;

    public PlayAudioActionCreator(String path) {
        this.path = path;
    }

    @Override
    public Action createAction() {
        return new PlayAudioAction(this.path);
    }

}
