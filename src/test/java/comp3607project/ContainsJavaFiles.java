package comp3607project;

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
        File[] studentFolders = folderTest.getUZFilePath().listFiles();

        // Ensure the folder is not empty
        assertTrue(studentFolders != null && studentFolders.length > 0);

        // Check each student folder
        for (File studentFolder : studentFolders) {
            assertTrue(studentFolder.isDirectory());

            File[] studentFiles = studentFolder.listFiles();
            assertTrue(studentFiles != null && studentFiles.length > 0);

            // Check each file within the student folder
            for (File f1 : studentFiles) {
                assertTrue(f1.isFile());
                assertTrue(f1.getName().endsWith(".java"));
            }
        }
    }
}
