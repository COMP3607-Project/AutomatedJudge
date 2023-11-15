package comp3607project;
 
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
 
public class Folders implements Component{
    protected File dirPath;
    protected ZipFile zipFile;

    public Folders(String directory) throws IOException{ 
        setDirPath(directory); //create File object for directory
        unzip(directory);
    }

    //method to unzip folder
    public void unzip(String filePath) throws IOException { 
        //steps to process zipped folder
        open(filePath);
        processContents();
        close();
    }

   @Override
    public void open(String filePath) throws IOException {
        zipFile = new ZipFile(filePath);
    }
    
    //iterator vs template pattern
    public void processContents() throws IOException{
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            processFile(entry);
        }
    }

    public void processFile(ZipEntry entry) throws IOException {
        //child classes will override
    }

    public void close() throws IOException {
        zipFile.close();
    }

    public File getDirPath() {
        return dirPath;
    }

    public void setDirPath(String directory) {
        dirPath = new File(directory);
    }
}
