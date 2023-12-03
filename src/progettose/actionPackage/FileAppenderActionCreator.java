package progettose.actionPackage;

import java.nio.file.Path;

public class FileAppenderActionCreator extends ActionCreator {

    private final Path filePath;
    private final String message;

    public FileAppenderActionCreator(Path filePath, String message) {
        this.filePath = filePath;
        this.message = message;
    }

    public Path getFilePath() {
        return filePath;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public Action createAction() {
        return new FileAppenderAction(this.filePath, this.message);

    }
    }
