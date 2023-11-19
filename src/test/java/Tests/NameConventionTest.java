package Tests;
import static org.junit.Assert.*;
import org.junit.Test;

import comp3607project.Folder;

import java.io.IOException;
 
public class NameConventionTest {
    @Test
    public void testStudentSubmissionName()  throws IOException{
        // Define the naming convention pattern ie. 8160123456_LaraCroft_A3
        final String studentSubName = "[816000000-816099999]_[A-Z][A-Z]_[A][1-5]";
        String zipFilePath = "C:\\Users\\vanes\\OneDrive - The University of the West Indies, St. Augustine\\Documents\\Year 3\\COMP 3607\\CWE1 Sample papers.zip";
        String destDirectory = "C:\\Users\\vanes\\OneDrive - The University of the West Indies, St. Augustine\\Documents\\Year 3\\COMP 3607\\Lab5";

        Folder folderTest = new Folder(zipFilePath, destDirectory);
        String[] studentFolders = folderTest.getUZFilePath().list();

        // Ensure the folder is not empty
        assertTrue(studentFolders != null && studentFolders.length > 0);

        // Check the name of each folder
        for (String name : studentFolders) 
            assertTrue(name.matches(studentSubName));
    }    
}
