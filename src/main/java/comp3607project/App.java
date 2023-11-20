package comp3607project;

import java.io.File;
import com.itextpdf.io.exceptions.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        String zipFilePath = "C:\\Users\\vanes\\OneDrive - The University of the West Indies, St. Augustine\\Documents\\Year 3\\COMP 3607\\CWE1 Sample papers.zip";
        File dest = new File(zipFilePath);

        try{
            // Store folder contents in parent directory
            Folder classFolder = new Folder(zipFilePath, dest.getParentFile().getAbsolutePath());

            // Use iterator pattern to process each student submission folder
            Iterator itr = classFolder.createIterator();
            
            while (itr.hasNext()) { // Unzip student folder and store in Maven project
                Folder test = new Folder(((File) itr.next()).getAbsolutePath(), "/workspace/AutomatedJudge/src/main");
                //runTests();
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
