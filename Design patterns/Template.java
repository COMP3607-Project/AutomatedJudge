import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

// Template Design Pattern
abstract class ZipFileProcessor {
    private ZipFile zipFile;

    public void process(String filePath) throws IOException {
        openFile(filePath);
        processFiles();
        closeFile();
    }

    private void openFile(String filePath) throws IOException {
        // Common code for opening the zip file
        zipFile = new ZipFile(filePath);
    }

    private void processFiles() throws IOException {
        // Common code for processing files
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            processFile(entry);
        }
    }

    protected abstract void processFile(ZipEntry entry) throws IOException;

    private void closeFile() throws IOException {
        // Common code for closing the zip file
        zipFile.close();
    }
}