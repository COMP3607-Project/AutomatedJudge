package comp3607project;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.time.LocalDateTime;

public class FlightTest extends TestTemplate{
    private Flight flight1;
    private Field[] flightFields = Flight.class.getDeclaredFields();


    public FlightTest(){
        flight1 = new Flight("POS123", "JFK", "POS", );
    }

    @Before
    public void init(){
        response = "";
        mark = 0;
        grade = true; //determines if student get grade
        found = false; //determines if field was found aka adheres to naming convention
    }

    @Test
    public void testFlightNo(){
        checkAttribute(flightFields, "flightNo", "private", "String");

        if(found)
            response += "Correct naming convention";
        else{
            response += "Incorrect naming convention -> Expected: flightNo";
            grade = false;
        }

        mark = grade ? 1 : 0;
        results.add(new Feedback("Flight", "flightNo Attribute", mark, response));
    }

    @Test
    public void testDestination(){
        checkAttribute(flightFields, "destination", "private", "String");

        if(found)
            response += "Correct naming convention";
        else{
            response += "Incorrect naming convention -> Expected: destination";
            grade = false;
        }

        mark = grade ? 1 : 0;
        results.add(new Feedback("Flight", "destination Attribute", mark, response));
    }

    @Test
    public void testOrigin(){
        checkAttribute(flightFields, "origin", "private", "String");

        if(found)
            response += "Correct naming convention";
        else{
            response += "Incorrect naming convention -> Expected: origin";
            grade = false;
        }

        mark = grade ? 1 : 0;
        results.add(new Feedback("Flight", "origin Attribute", mark, response));
    }

    @Test
    public void testFlightDate(){
        checkAttribute(flightFields, "flightDate", "private", "LocalDateTime");

        if(found)
            response += "Correct naming convention";
        else{
            response += "Incorrect naming convention -> Expected: flightDate";
            grade = false;
        }

        mark = grade ? 1 : 0;
        results.add(new Feedback("Flight", "flightDate Attribute", mark, response));
    }

    @Test
    public void testManifest(){
        checkAttribute(flightFields, "manifest", "private", "LuggageManifest");

        if(found)
            response += "Correct naming convention";
        else{
            response += "Incorrect naming convention -> Expected: manifest";
            grade = false;
        }

        mark = grade ? 1 : 0;
        results.add(new Feedback("Flight", "manifest Attribute", mark, response));
    }

}