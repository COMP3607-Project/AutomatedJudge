package comp3607project;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.regex.*;

import java.io.File;
import java.io.IOException;
 
public class NameConventionTest {
    @Test
    public void testStudentSubmissionName()  throws IOException{
        // Define the naming convention pattern ie. 8160123456_LaraCroft_A3
        final String studentSubName = "^[816000000-816099999_]+_[A-Za-z_]+_[A0-9_]+$";//"[816000000-816099999]_[A-Za-z]_[A][1-5]";

        // Compile the pattern into a regex Pattern object
        Pattern regexPattern = Pattern.compile(studentSubName);

        // Unzipped class folder
        String destDirectory = "src/resources/SubTest";
        String[] studentFolders = new File(destDirectory).list();

        // Check the name of each folder
        for (String name : studentFolders)  {
            assertTrue(name, regexPattern.matcher(name).matches());
        }
    }
}
