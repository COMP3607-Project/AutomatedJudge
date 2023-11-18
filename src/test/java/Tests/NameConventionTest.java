package Tests;
import static org.junit.Assert.*;
import org.junit.Test;

import comp3607project.Folder;

import java.io.File;
import java.io.IOException;
 
public class NameConventionTest {
    @Test
    public void testSubmissionNameFollowConvention(String studentSubName)  throws IOException{
        // Define the naming convention pattern ie. "Assignment3_Java_Programs"
        final String classSubName = "Assignment[1-5]_Java_Programs";

        String zipFilePath = "C:\\Users\\vanes\\OneDrive - The University of the West Indies, St. Augustine\\Documents\\Year 3\\COMP 3607\\CWE1 Sample papers.zip";
        String destDirectory = "C:\\Users\\vanes\\OneDrive - The University of the West Indies, St. Augustine\\Documents\\Year 3\\COMP 3607\\Lab5";

        Folder folderTest = new Folder(zipFilePath, destDirectory);

        // String[] contents = file.list();
        // for(String name:contents){ 
        //     assertTrue(name.matches(studentSubName));//, "File name '" + name + "' does not follow the naming convention.");
        // }
    }
}
