package comp3607project;

import java.io.File;
import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        //runner class to ensure that everything works
        Folder folderTest;
        String zipFilePath = "C:\\Users\\vanes\\OneDrive - The University of the West Indies, St. Augustine\\Documents\\Year 3\\COMP 3607\\CWE1 Sample papers.zip";
        String destDirectory = "C:\\Users\\vanes\\OneDrive - The University of the West Indies, St. Augustine\\Documents\\Year 3\\COMP 3607\\Lab5";

        folderTest = new Folder(zipFilePath, destDirectory);

        File unzippedFolder = new File(destDirectory);
        String contents[] = unzippedFolder.list();
        System.out.println("List of files and directories in directory:");

        for(int i=0; i<contents.length; i++) {
            System.out.println(contents[i]);
        }

        // Use iterator pattern to process each student submission folder
        Iterator itr = new Folder(zipFilePath, destDirectory).createIterator();
        
        while (itr.hasNext()) {
            //open each student's submission folder
            //and copy java files to test files folder 

            //ie. Folder test = new Folder(itr.next, testFilesFolder);
        } 
    }
}
