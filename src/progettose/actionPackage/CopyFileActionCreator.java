package progettose.actionPackage;

import java.nio.file.Path;

public class CopyFileActionCreator extends ActionCreator{
    
    //Paths for CopyFileAction
    private Path filePath;
    private Path copyPath;
    
    //Costructor of the CopyFileActionCreator
    public CopyFileActionCreator(Path filePath, Path copyPath){
        this.filePath = filePath;
        this.copyPath = copyPath;
    }
    
    //Returns a new instance of CopyFileAction with the specified paths for the file and destination directory
    @Override
    public Action createAction(){
        return new CopyFileAction(this.filePath, this.copyPath);
    }
    
}
