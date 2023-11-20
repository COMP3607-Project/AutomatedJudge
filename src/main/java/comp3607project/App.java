package comp3607project;

import java.io.File;
import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        String zipFilePath = "C:\\Users\\vanes\\OneDrive - The University of the West Indies, St. Augustine\\Documents\\Year 3\\COMP 3607\\CWE1 Sample papers.zip";
        String destDirectory = "C:\\Users\\vanes\\OneDrive - The University of the West Indies, St. Augustine\\Documents\\Year 3\\COMP 3607\\Lab5";
        
        try{
            Folder classFolder = new Folder(zipFilePath, destDirectory);

            // Use iterator pattern to process each student submission folder
            Iterator itr = classFolder.createIterator();
            
            while (itr.hasNext()) {
                //open each student's submission folder
                //and copy java files to test files folder 

                //ie. Folder test = new Folder(itr.next, testFilesFolder);
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
