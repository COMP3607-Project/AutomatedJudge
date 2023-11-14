package comp3607project;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Before;
import java.lang.reflect.*;
import java.time.LocalDateTime;

public class FlightTest {
    private String response;
    private int mark;
    private boolean grade;
    private Flight flight1;

    public FlightTest(){
        flight1 = new Flight();
    }

    @Before
    public void init(){
        response = "";
        mark = 0;
        grade = true;
    }

    @Test
    public void testFlightNo(){
        checkAttribute("flightNo", "private", "String");

        mark = if(grade) ? 1 : 0;
        results.add(new Feedback("Flight", "flightNo Attribute", mark, response));
    }

    public void checkAttribute(String fieldName, String mod, String type){
        Field[] flightFields = Flight.class.getDeclaredFields();
        for(Field f: flightFields){
            if(f.getName().equals(fieldName)){
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