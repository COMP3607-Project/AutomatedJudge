package comp3607project;

import java.io.File;
import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        int failed;
        FileCompilationChecker checker = new FileCompilationChecker();
        String zipFilePath = "C:\\Users\\vanes\\OneDrive - The University of the West Indies, St. Augustine\\Documents\\Year 3\\COMP 3607\\CWE1 Sample papers.zip";
        String destDirectory = "C:\\Users\\vanes\\OneDrive - The University of the West Indies, St. Augustine\\Documents\\Year 3\\COMP 3607\\Lab5";

        try{
            Folder classFolder = new Folder(zipFilePath, destDirectory);

            // Use iterator pattern to process each student submission folder
            Iterator itr = classFolder.createIterator();
            
            while (itr.hasNext()) {
                // Open each student's submission folder and copy  
                // java files to test files folder to evaluate correctness
                Folder test = new Folder(itr.next(), testFilesFolder);

                // Determine whether java files compile
                File[] studentFiles = test.getContents();
                for (File f1: studentFiles) 
                    checker.checkCompilation(f1.getAbsolutePath());

                //failed = checker.getFailedCount();
                //System.out.println("Total failed compilations: " + failed);
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
