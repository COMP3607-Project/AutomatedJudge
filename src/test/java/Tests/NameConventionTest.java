package Tests;
import static org.junit.Assert.*;
import org.junit.Test;
import java.io.File;
 
public class NameConventionTest {

    // Define the naming convention pattern ie. 8160123456_LaraCroft_A3
    private static final String studentSubName = "[816000000-900000000]_[A-Z]_[A][1-5]";
    //private static final String classSubName = "Assignment[1-5]_Java_Programs";

    //Path for unzipped folder containing all student submissions
    File file = new File("D:/Testfiles/"); 

    @Test
    public void testSubmissionNameFollowConvention(String studentSubName) {
        String[] contents = file.list();
        for(String name:contents){ 
            assertTrue(name.matches(studentSubName), "File name '" + name + "' does not follow the naming convention.");
        }
    }
}
