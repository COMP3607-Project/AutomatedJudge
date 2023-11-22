package comp3607project;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.junit.runner.Result;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import com.itextpdf.io.exceptions.IOException;
import java.util.ArrayList;


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

                Feedback.setFileName(((File) itr.next()).getName());

                ReportContent content = new ReportContent();

                Folder test = new Folder(((File) itr.next()).getAbsolutePath(), "src/resources/StudentFiles");
                //Result result = JUnitCore.runClasses(TestSuite.class);
                JUnitCore junit = new JUnitCore();
                junit.addListener(new TextListener(System.out));
                junit.run(ClassTestSuite.class);

                // Update student files in folder
                //Path from = ((File) itr.next()).toPath(); //convert from File to Path
                //Path to = Paths.get("src/resources/StudentFiles"); //convert from String to Path
                //Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);

                FeedbackReport report = new FeedbackReport(content);
                report.generateDocument();
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
/*public class App {
     public static void main(String[] args) {


        Feedback.setFileName("816033413_JasonBalroop_A1.zip");

        ReportContent content = new ReportContent();

        ReportContent.addFeedback(new Feedback("Flight", "Test 1", 10, "Nice!"));
        ReportContent.addFeedback(new Feedback("Flight", "Test 2", 10, "Good job!"));
        ReportContent.addFeedback(new Feedback("Passenger", "Test 3", 10, "Well done!"));
        ReportContent.addFeedback(new Feedback("Passenger", "Test 4", 10, "Excellent!"));
        ReportContent.addFeedback(new Feedback("Luggage", "Test 5", 10, "Great!"));
        ReportContent.addFeedback(new Feedback("Luggage", "Test 6", 10, "Awesome!"));
        
        FeedbackReport report = new FeedbackReport(content);
        report.generateDocument();

     }
}*/