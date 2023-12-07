package progettose.actionPackage;

import java.nio.file.Path;

public class DeleteFileActionCreator extends ActionCreator{
    
    //Path for DeleteFileAction
    private final Path filePath;
    
    //Constructor for DeleteFileActionCreator
    public DeleteFileActionCreator(Path filePath){
        this.filePath = filePath;
    }
    
    //Returns a new instance of DeleteFileAction with the specified paths for the file and destination directory
    @Override
    public Action createAction(){
        return new DeleteFileAction (this.filePath);
    }
}
