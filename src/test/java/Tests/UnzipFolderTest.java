package Tests;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
       
public class UnzipFolderTest {

    @Test
    public void testUnzipFile() throws IOException {
        String zipFilePath = "path/file.zip"; //ie. Assignment1_Java_Programs.zip/8160123456_LaraCroft_A3
        String destDirectory = "path/to/destination/folder";

        // Method to unzip folder in UnzipFolder class
        //unzipFolder.unzip(zipFilePath, destDirectory);

        // Verify that the unzipped folder exists
        File unzippedFolder = new File(destDirectory);

        assertTrue(unzippedFolder.exists());
        assertTrue(unzippedFolder.isDirectory());

        // Ensure folder is not empty ie.contains individual student submissions
        File[] folderContents = unzippedFolder.listFiles();
        assertTrue(folderContents != null && folderContents.length > 0);
    }
}