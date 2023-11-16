import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
 
/**
 * This utility extracts files and directories of a 
 * standard zip file to a destination directory.
 * @author www.codejava.net
 */

public class UnzipUtility {
    private static final int BUFFER_SIZE = 4096; // Most file systems are configured to use block sizes of 4096
    File destDir;

    public void unzip(String zipFilePath, String destDirectory) throws IOException {
        destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry(); // Get first entry in zipped 

        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();

            if (!entry.isDirectory()) { // if the entry is a file, extract it
                extractFile(zipIn, filePath);
            } else { // if the entry is a folder, make new directory within parent directory
                File dir = new File(filePath);
                dir.mkdirs();
            }

            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }

    // Extracts a zip file entry 
    public void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        FileOutputStream fout = new FileOutputStream(filePath);
        BufferedOutputStream bout = new BufferedOutputStream(fout);
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int totalBytes = 0; 

        while ((totalBytes = zipIn.read(bytesIn)) != -1) {
            bout.write(bytesIn, 0, totalBytes);
        }
        bout.flush();
        bout.close();
    }
 
    public File getDestDir() {
        return destDir;
    }
}
