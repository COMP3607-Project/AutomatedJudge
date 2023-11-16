package comp3607project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class PassengerTest {

    private String response;
    private int total;
    boolean passed;
    boolean declaredOnly;
    String typeName;
    String modifier;
    private Field field;
    Object value;
    private Passenger passenger;
    private String fieldName;
    private String expectedField;
    // ArrayList<String> expectedNames;
    
    @Before
    public void initPassengerTest() {
        
        response = "";
        total = 0;
        declaredOnly = false;
        passed = false;
        field = null;
        passenger = new Passenger("jfk","jfk","jfk","jfk");

    }

    @Test
    public void testPassportNumberField(){
        
        fieldName = "passportNumber";

        field = getField(fieldName);

        if(field != null){
            
            typeName = String.class.getName();
            modifier = "private";
            expectedField = modifier + " String " + fieldName;

            /*  Code to check constructor goes here. COnstructors force initialisation so only variables that are not in the constructor should be checked
             *  in this way ~ Z is working on this;
             * 
             * checkDeclaredOnly();
             */

            checkExpectedField(fieldName, expectedField);

        }
        else
            response += "Incorrect. Expected field: " + expectedField + "\n";

        assertTrue(passed);

        total += 1;

    }

    private Field getField(String fieldName){
        
        Field testField = null;
       
            try {
                testField = Passenger.class.getDeclaredField(fieldName);
                passed = true;
                return testField;
            }
            catch (NoSuchFieldException e) {
                    return testField;
            }       
    }

    private void checkDeclaredOnly(){
       
        field.setAccessible(true);

        try {
            value = field.get(passenger);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (value != null){
            response += "Variable should ONLY be declared\n";
            declaredOnly = false;
            
        }

        if(declaredOnly == false)
            passed = false;
    }

    private void checkExpectedField(String fieldName, String expectedField){

        String isExpectedField = field.toString();

        System.out.println(isExpectedField);

        passed = isExpectedField.contains(modifier + " " + typeName);

        if(passed){
            passed = isExpectedField.contains("Passenger." + fieldName);
            response += "Correct field\n";
        }
        else
            response += "Incorrect. Expected field: " + expectedField + "\n";
    }
}
    

