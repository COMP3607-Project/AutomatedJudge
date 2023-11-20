package comp3607project;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
 
public class ContainsJavaFiles {
    @Test
    public void testFileFormat() throws IOException {
        // Unzipped student folder
        String destDirectory = "src/resources/asg2";
        File[] studentFolders = new File(destDirectory).listFiles();

        // Ensure the folder is not empty
        assertTrue(studentFolders != null && studentFolders.length > 0);

        // Check each file in student's folder
        for (File studentFiles : studentFolders) {
            assertTrue(studentFiles != null);

            assertTrue(studentFiles.isFile());
            assertTrue(studentFiles.getName().endsWith(".java"));
        }
    }
}
