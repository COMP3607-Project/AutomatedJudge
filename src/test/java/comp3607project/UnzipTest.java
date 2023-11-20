package comp3607project;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
       
public class UnzipTest {
    @Test
    public void testUnzipFile() throws IOException {
        // Paths must have '\\' instead of '\'
        String zipFilePath = "src/resources/CWE1 Sample papers.zip"; 
        String destDirectory = "src/resources/Project";
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
        assertTrue(folderContents.length > 0);
    }
}