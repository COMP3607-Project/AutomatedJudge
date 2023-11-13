package comp3607project;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;
import java.lang.reflect.*;
import java.time.LocalDateTime;

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
        String response = "";
        int mark = 0;
        boolean passed = true;

        try{
            Field flightField = Flight.class.getDeclaredField("flightNo");
            response = response + "Correct naming convention.";
        }catch(Exception e){
            response = response + "Imcorrect naming convention.";
            return; 
        }

        //tests information hiding
        if(checkFieldModifier("private", "flightNo")){
            response = response + "Attribute is private.";
        }else{
            response = response + "Attribute is not private.";
            passed = false;
        }

        //tests data type
        try{
            if(assertTrue(flight1.getFlightNo() instanceof String)){
                response = response + " Is of type String ";
            }else{
                response = response + " Is not of type String.";
                passed = false;
            }

            if(assertNull(flight1.getFlightNo())){
                response = response + " Has only been declared.";
            }else{
                response = response + "Has been initialized.";
                passed = false;
            }

        }catch(Exception e){
            response = response + " No accessor method available.";
        }

        if(passed) mark = 1;
        Feedback f = new Feedback("Flight", "flightNo Attribute", mark, response);
    }

    @Test
    public void testDestination(){
        String response = "";
        int mark = 0;
        boolean passed = true;

        try{
            Field flightField = Flight.class.getDeclaredField("destination");
            response = response + "Correct naming convention.";
        }catch(Exception e){
            response = response + "Imcorrect naming convention.";
            return; 
        }

        //tests information hiding
        if(checkFieldModifier("private", "destination")){
            response = response + "Attribute is private.";
        }else{
            response = response + "Attribute is not private.";
            passed = false;
        }

        //tests data type
        try{
            if(assertTrue(flight1.getDestination() instanceof String)){
                response = response + " Is of type String ";
            }else{
                response = response + " Is not of type String.";
                passed = false;
            }

            if(assertNull(flight1.getDestination())){
                response = response + " Has only been declared.";
            }else{
                response = response + "Has been initialized.";
                passed = false;
            }

        }catch(Exception e){
            response = response + " No accessor method available.";
        }

        if(passed) mark = 1;
        Feedback f = new Feedback("Flight", "destination Attribute", mark, response);
    }

    @Test
    public void testOrigin(){
        String response = "";
        int mark = 0;
        boolean passed = true;

        try{
            Field flightField = Flight.class.getDeclaredField("origin");
            response = response + "Correct naming convention.";
        }catch(Exception e){
            response = response + "Imcorrect naming convention.";
            return; 
        }

        //tests information hiding
        if(checkFieldModifier("private", "origin")){
            response = response + "Attribute is private.";
        }else{
            response = response + "Attribute is not private.";
            passed = false;
        }

        //tests data type
        try{
            if(assertTrue(flight1.getOrigin() instanceof String)){
                response = response + " Is of type String ";
            }else{
                response = response + " Is not of type String.";
                passed = false;
            }

            if(assertNull(flight1.getOrigin())){
                response = response + " Has only been declared.";
            }else{
                response = response + "Has been initialized.";
                passed = false;
            }

        }catch(Exception e){
            response = response + " No accessor method available.";
        }

        if(passed) mark = 1;
        Feedback f = new Feedback("Flight", "origin Attribute", mark, response);
    }

    @Test
    public void testFlightDate(){
        String response = "";
        int mark = 0;
        boolean passed = true;

        try{
            Field flightField = Flight.class.getDeclaredField("flightDate");
            response = response + "Correct naming convention.";
        }catch(Exception e){
            response = response + "Imcorrect naming convention.";
            return; 
        }

        //tests information hiding
        if(checkFieldModifier("private", "flightDate")){
            response = response + "Attribute is private.";
        }else{
            response = response + "Attribute is not private.";
            passed = false;
        }

        //tests data type
        try{
            if(assertTrue(flight1.getFlightDate() instanceof LocalDateTime)){
                response = response + " Is of type String ";
            }else{
                response = response + " Is not of type String.";
                passed = false;
            }

            if(assertNull(flight1.getFlightDate())){
                response = response + " Has only been declared.";
            }else{
                response = response + "Has been initialized.";
                passed = false;
            }

        }catch(Exception e){
            response = response + " No accessor method available.";
        }

        if(passed) mark = 1;
        Feedback f = new Feedback("Flight", "flightDate Attribute", mark, response);
    }

    @Test
    public void testManifest(){
        String response = "";
        int mark = 0;
        boolean passed = true;

        try{
            Field flightField = Flight.class.getDeclaredField("manifest");
            response = response + "Correct naming convention.";
        }catch(Exception e){
            response = response + "Imcorrect naming convention.";
            return; 
        }

        //tests information hiding
        if(checkFieldModifier("private", "manifest")){
            response = response + "Attribute is private.";
        }else{
            response = response + "Attribute is not private.";
            passed = false;
        }

        //tests data type
        try{
            if(assertTrue(flight1.getManifest() instanceof LuggageManifest)){
                response = response + " Is of type String ";
            }else{
                response = response + " Is not of type String.";
                passed = false;
            }

            if(assertNull(flight1.getManifest())){
                response = response + " Has only been declared.";
            }else{
                response = response + "Has been initialized.";
                passed = false;
            }

        }catch(Exception e){
            response = response + " No accessor method available.";
        }

        if(passed) mark = 1;
        Feedback f = new Feedback("Flight", "manifest Attribute", mark, response);
    }

    @Test
    public boolean checkFieldModifier(String mod, String fieldName){
        Field flightField = Flight.class.getDeclaredField(fieldName);
        return assertEquals(mod, Modifier.toString(flightField.getModifiers()));
    }

    @Test
    public boolean checkMethodModifier(String mod, String methodName){
        Method flightMethod = Flight.class.getDeclaredMethod(methodName);
        return assertEquals(mod, Modifier.toString(flightMethod.getModifiers()));
    }

}
