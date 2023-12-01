package progettose.triggerPackage;

import java.nio.file.Path;

public class FileSizeCheckerTriggerCreator extends TriggerCreator {

    private Path filePath;
    private long value;
    private String typeDimension;

    public FileSizeCheckerTriggerCreator(Path file, long value, String typeDimension) {
        this.filePath = file;
        this.value = value;
        this.typeDimension = typeDimension;
    }

    @Override
    public Trigger createTrigger() {
        return new FileSizeCheckerTrigger(this.filePath, this.value, this.typeDimension);
    }

}
