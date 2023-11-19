package Tests;
import static org.junit.Assert.*;
import org.junit.Test;

import comp3607project.Folder;

import java.io.File;
import java.io.IOException;
 
public class ContainsJavaFiles {
    @Test
    public void testSubmissionNameFollowConvention() throws IOException {
        // Define the naming convention pattern ie. "Assignment3_Java_Programs"
        final String classSubName = "Assignment[1-5]_Java_Programs";

        String zipFilePath = "C:\\Users\\vanes\\OneDrive - The University of the West Indies, St. Augustine\\Documents\\Year 3\\COMP 3607\\CWE1 Sample papers.zip";
        String destDirectory = "C:\\Users\\vanes\\OneDrive - The University of the West Indies, St. Augustine\\Documents\\Year 3\\COMP 3607\\Lab5";

        Folder folderTest = new Folder(zipFilePath, destDirectory);

        // Ensure the folder exists
        assertTrue(folderTest.getFolder().exists());

        // Ensure the folder is not empty
        File[] studentFolders = folderTest.getFolder().listFiles(File::isDirectory);
        assertNotNull(studentFolders);
        assertTrue(studentFolders.length > 0);

        for (File studentFolder : studentFolders) {
            // Check each student folder
            assertTrue(studentFolder.isDirectory());
            File[] studentFiles = studentFolder.listFiles();
            assertNotNull(studentFiles);
            assertTrue(studentFiles.length > 0);

            for (File file : studentFiles) {
                // Check each file within the student folder
                assertTrue(file.isFile());
                assertTrue(file.getName().endsWith(".java"));
                assertTrue(file.length() > 0);
            }
        }
    }
}
