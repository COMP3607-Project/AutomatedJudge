package comp3607project;

import java.io.File;
 
public class Folders implements Component{
    private File dirPath;
    //private boolean empty=true;

    public Folders(String directory){ 
        setDirPath(directory); //create File object for directory
        open();
    }

    public void open() {
        unzip();
        //public boolean isEmpty() {};?
    }

    public void unzip() {
        //method to unzip folder
    }

    public File getDirPath() {
        return dirPath;
    }

    public void setDirPath(String directory) {
        dirPath = new File(directory);
    }
}
