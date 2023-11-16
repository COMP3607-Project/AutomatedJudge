package comp3607project;

import java.io.File;
import java.io.IOException;
 
public class Folder{
    private File unzipFilePath;
    private UnzipUtility uz = new UnzipUtility();
    private File[] contents;

    public Folder(String zipFilePath, String destDirectory) throws IOException{ 
        uz.unzip(zipFilePath, destDirectory);
        setFilePath();
    }

    public void setFilePath() {
        unzipFilePath = uz.getDestDir();
    }

    public File getFilePath() {
        return unzipFilePath;
    }

    //use iterator design pattern to get all contents of a folder
    //use unzipFilePath to get directories and store using their file paths
}

