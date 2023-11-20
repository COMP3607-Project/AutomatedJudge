package comp3607project;

import java.io.File;

import org.junit.runner.Result;
import org.junit.runner.JUnitCore;
import com.itextpdf.io.exceptions.IOException;

import junit.framework.TestSuite;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        String zipFilePath = "src/resources/Assignment1_Java_Programs.zip";
        String destDirectory = "src/resources/ClassFiles";

        try{
            Folder classFolder = new Folder(zipFilePath, destDirectory);
            
            // Use iterator pattern to process each student submission folder
            Iterator itr = classFolder.createIterator();
            
            while (itr.hasNext()) { // Unzip student folder and store in Maven project
                Folder test = new Folder(((File) itr.next()).getAbsolutePath(), "src/resources/StudentFiles");
                Result result = JUnitCore.runClasses(TestSuite.class);
            }
        }
        catch(Exception e){
            System.out.println("Invalid directories submitted.");
        }
        finally{
            System.out.println("End of Student submission processing.");
        }
    }
}


// public class App {
//     public static void main(String[] args) {

//         ReportContent content = new ReportContent();

//         ReportContent.addFeedback(new Feedback("Flight", "Test 1", 10, "Nice!"));
//         ReportContent.addFeedback(new Feedback("Flight", "Test 2", 10, "Good job!"));
//         ReportContent.addFeedback(new Feedback("Passenger", "Test 3", 10, "Well done!"));
//         ReportContent.addFeedback(new Feedback("Passenger", "Test 4", 10, "Excellent!"));
//         ReportContent.addFeedback(new Feedback("Luggage", "Test 5", 10, "Great!"));
//         ReportContent.addFeedback(new Feedback("Luggage", "Test 6", 10, "Awesome!"));
        
//         FeedbackReport report = new FeedbackReport(content);
//         report.generateDocument();

//     }
// }
