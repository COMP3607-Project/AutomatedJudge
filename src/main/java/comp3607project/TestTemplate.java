package comp3607project;

import org.junit.*;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.ArrayList;

public abstract class TestTemplate extends Exception{
    protected boolean grade = true;
    protected int mark = 0;
    protected boolean found = false;
    protected String response = "";

    //temporary arraylist for storing feedback. should be in the class that generates the pdf
    protected ArrayList<Feedback> results = new ArrayList<Feedback>();
    
    public void checkAttribute(Object obj, Field[] classFields, String fieldName, String mod, String type){
        for(Field f: classFields){
            if(f.getName().equals(fieldName)){
                found = true;

                try{
                    assertEquals(0, f.toGenericString().indexOf(mod));
                    response += "Correct access modifier.\n";
                }catch(Exception e){
                    response += "Incorrect access modifier.\n";
                    grade = false;
                }

                try{
                    assertTrue(f.toGenericString().contains(type));
                    response += "Correct data type.\n";
                }catch(Exception e){
                    response += "Incorrect data type.\n";
                    grade = false;
                }

                try{
                    
                    if (obj instanceof Flight){
                        Flight testClass = (Flight) obj;
                        f.setAccessible(true);
                        assertNull(f.get(testClass));

                    }else if (obj instanceof Passenger){
                        Passenger testClass = (Passenger) obj;
                        f.setAccessible(true);
                        assertNull(f.get(testClass));

                    }else if (obj instanceof LuggageSlip){
                        LuggageSlip testClass = (LuggageSlip) obj;
                        f.setAccessible(true);
                        assertNull(f.get(testClass));

                    }else if (obj instanceof LuggageManifest){
                        LuggageManifest testClass = (LuggageManifest) obj;
                        f.setAccessible(true);
                        assertNotNull(f.get(testClass));
                    }
                    response += "Has only been declared.\n";
                }catch(Exception e){
                    response += "Has been initialized.\n"; 
                    grade = false;
                }
            }
        }
    }
}
