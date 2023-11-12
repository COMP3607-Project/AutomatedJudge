package comp3607project;

import java.util.ArrayList;

public class StudentFolder extends Folders{
    private ArrayList<Files> asgFiles; //contains individual student submission files
 
    public StudentFolder(String folder) {
        super(folder);

       this.asgFiles = new ArrayList<Files>();
    }
}
