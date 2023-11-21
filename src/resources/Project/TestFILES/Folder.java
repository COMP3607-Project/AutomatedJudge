import java.io.File;
import java.io.IOException;

public class Folder implements Container{
    private File unzipFilePath;
    private UnzipUtility uz = new UnzipUtility();
    private File[] foldercontents;

    public Folder(String zipFilePath, String destDirectory) throws IOException{ 
        uz.unzip(zipFilePath, destDirectory);
        setFilePath();
    }

    public void setFilePath() {
        unzipFilePath = uz.getDestDir();
    }

    public File getUZFilePath() {
        return unzipFilePath;
    }

    public void loadContents() {
        foldercontents = getUZFilePath().listFiles(); // Stores a collection of the file objects within directory
    }

    @Override
    public Iterator createIterator() {
        return new FolderIterator();
    }

    // Nested class
    private class FolderIterator implements Iterator {
        private int i=0;

        @Override
        public boolean hasNext()
        {
            if (i < foldercontents.length)
                return true;
            
            return false;
        }

        @Override
        public Object next()
        {
            if (this.hasNext())
                return foldercontents[i++];
            
            return null;
        }  
    }
}