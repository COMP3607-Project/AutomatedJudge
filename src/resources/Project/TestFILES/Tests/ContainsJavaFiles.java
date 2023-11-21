package Tests;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
 
public class ContainsJavaFiles {
    @Test
    public void testFileFormat() throws IOException {
        String zipFilePath = "C:\\Users\\vanes\\OneDrive - The University of the West Indies, St. Augustine\\Documents\\Year 3\\COMP 3607\\CWE1 Sample papers.zip";
        String destDirectory = "C:\\Users\\vanes\\OneDrive - The University of the West Indies, St. Augustine\\Documents\\Year 3\\COMP 3607\\Lab5";
        Folder folderTest = new Folder(zipFilePath, destDirectory);

        // Ensure the folder is not empty
        File[] studentFolders = folderTest.getFolder().listFiles(File::isDirectory);
        assertTrue(studentFolders != null && studentFolders.length > 0);

        // Check each student folder
        for (File studentFolder : studentFolders) {
            assertTrue(studentFolder.isDirectory());

            File[] studentFiles = studentFolder.listFiles();
            assertTrue(studentFiles != null && studentFiles.length > 0);

            for (File file : studentFiles) {
                // Check each file within the student folder
                assertTrue(file.isFile());
                assertTrue(file.getName().endsWith(".java"));
                assertTrue(file.length() > 0);
            }
        }
    }
}
