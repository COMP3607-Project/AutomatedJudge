package Tests;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import comp3607project.UnzipUtility;
       
public class UnzipTest {
    @Test
    public void testUnzipFile() throws IOException {
        // Paths must have '\\' instead of '\'
        String zipFilePath = "path/file.zip"; 
        String destDirectory = "path/to/destination/folder";
        UnzipUtility unzipper = new UnzipUtility();

        // Method to unzip folder in UnzipUtility class
        unzipper.unzip(zipFilePath, destDirectory);

        // Verify that the unzipped folder exists
        File unzippedFolder = new File(destDirectory);

        assertTrue(unzippedFolder.exists());
        assertTrue(unzippedFolder.isDirectory()); 

        // Ensures folder is not empty 
        File[] folderContents = unzippedFolder.listFiles();
        assertNotNull(folderContents);
        assertTrue(folderContents.length >0);
    }
}