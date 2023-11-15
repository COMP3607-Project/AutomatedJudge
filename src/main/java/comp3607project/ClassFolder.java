package comp3607project;
 
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ClassFolder extends Folders{
    private File studentIDs = new File("test.pdf"); //contains ids of all students in class
    private ArrayList<StudentFolder> studentSubFolder; //contains subfolders with each student's submission

    public ClassFolder (String directory) throws IOException{
        super(directory);

        this.studentSubFolder = new ArrayList<StudentFolder>();
        loadStudentFolders();
    }

    public void loadStudentFolders() {
        String[] folderContents = this.getDirPath().list(); //names of each subfolder
        for (String contents: folderContents) { 
            studentSubFolder.add(new StudentFolder(contents)); 
        }
    }

    // @Override
    // public void processContents() {
    //     this.studentSubFolder = new ArrayList<StudentFolder>();
    //     loadStudentFolders();
    // }
}
