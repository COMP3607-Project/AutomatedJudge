package comp3607project;

import org.junit.*;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.ArrayList;

public abstract class TestTemplate {
    protected boolean grade = true;
    protected int mark = 0;
    protected boolean found = false;
    protected String response = "";

    //temporary arraylist for storing feedback. should be in the class that generates the pdf
    protected ArrayList<Feedback> results = new ArrayList<Feedback>();
    
    public void checkAttribute(Field[] classFields, String fieldName, String mod, String type){
        for(Field f: classFields){
            if(f.getName().equals(fieldName)){
                found = true;

                if(assertEquals(0, f.toGenericString().indexOf(mod)))
                    response += "Correct access modifier.\n";
                else{
                    response += "Incorrect access modifier.\n";
                    grade = false;
                }

                if(assertTrue(f.toGenericString().contains(type)))
                    response += "Correct data type.\n";
                else{
                    response += "Incorrect data type.\n";
                    grade = false;
                }

                if(assertNull(f))
                    response += "Has only been declared.\n";
                else{
                    response += "Has been initialized.\n";
                    grade = false;
                }
            }
        }
    }
}
