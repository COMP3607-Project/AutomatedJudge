package comp3607project;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;
import java.lang.reflect.*;

public class FlightTest {
    private Flight flight1;
    private int marks = 0;
    ArrayList<String> feedback = new ArrayList<String>();
    /* 
        flightNo String 1 Private, instance variable, declared only.
        destination String 1 Private, instance variable, declared only.
        origin String 1 Private, instance variable, declared only.
        flightDate LocalDateTime 1 Private, instance variable, declared only.
        manifest LuggageManifest 1 Private, instance variable, d
    */

    public FlightTest(){
        flight1 = new Flight();
    }

    @Test
    public void testFlightNo(){
        String accessor = Modifier.toString(checkFieldModifier("flightNo"));
        Feedback f = new Feedback();
        if(assertEquals("private", accessor)){
            
        }
    }


    @Test
    public int checkFieldModifier(String fieldName){
        Field flightField = Flight.class.getDeclaredField(fieldName);
        return flightField.getModifiers();
    }

    @Test
    public int checkMethodModifier(String methodName){
        Method flightMethod = Flight.class.getDeclaredMethod(methodName);
        return flightMethod.getModifiers();
    }

}
