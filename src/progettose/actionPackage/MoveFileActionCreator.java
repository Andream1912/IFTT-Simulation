package progettose.actionPackage;

import java.nio.file.Path;

public class MoveFileActionCreator extends ActionCreator{
    
    //Paths for MoveFileAction
    private final Path filePath;
    private Path movePath;
    
    //Constructor for MoveFileActionCreator
    public MoveFileActionCreator(Path filePath, Path movePath){
        this.filePath = filePath;
        this.movePath = movePath;
    }
    
    //Returns a new instance of MoveFileAction with the specified paths for the file and destination directory
    @Override
    public Action createAction(){
        return new MoveFileAction(this.filePath, this.movePath);
    }
}
