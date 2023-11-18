package comp3607project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class PassengerTest {
    
    private static int total = 0;

    private String response;
    boolean passed;
    boolean declaredOnly;
    String typeJavaName;
    String typeName;
    String modifier;
    private Field field;
    Object value;
    private Passenger passenger;
    private String fieldName;
    private String expectedField;
    private Class<?> testClass;
    
    @Before
    public void initPassengerTest() {
        
        response = "";
        declaredOnly = false;
        passed = false;
        field = null;
        testClass = Passenger.class;
        passenger = new Passenger("checkSet","checkSet","checkSet","checkSet");

    }

    @Test
    public void testPassportNumberField(){

        runFieldTest("passportNumber", "private", String.class.getName(), "String");

        assertTrue(passed);

        total += 1;
                System.out.println(total);


    }

    @Test
    public void testFlightNoField(){
        
        runFieldTest("flightNo", "private", String.class.getName(), "String");

        assertTrue(passed);

        total += 1;
                System.out.println(total);


    }

    @Test
    public void testfirstNameField(){
        
        runFieldTest("firstName", "private", String.class.getName(), "String");

        assertTrue(passed);

        total += 1;
        System.out.println(total);

    }

    @Test
    public void testlastNameField(){
        
        runFieldTest("lastName", "private", String.class.getName(), "String");

        assertTrue(passed);

        total += 1;
                System.out.println(total);

    }

    @Test
    public void testNumLuggageField(){
        
        runFieldTest("numLuggage", "private", "int", "int");

        assertTrue(passed);

        total += 1;
                System.out.println(total);


    }

    @Test
    public void testCabinClassField(){
        
        runFieldTest("cabinClass", "private", "char", "char");

        assertTrue(passed);

        total += 1;
                System.out.println(total);

    }

    @Test
    public void testConstrctor(){
        Constructor<?>[] constructors = testClass.getConstructors();

        String expectedConstructor = "public " + testClass.getName() + "("+ String.class.getName() + "," + String.class.getName()
                                    +"," + String.class.getName() + "," + String.class.getName() + ")";

        passed = true;

        field = getField("passportNumber");
        checkSet("checkSet");

        field = getField("firstName");
        checkSet("checkSet");

        field = getField("lastName");
        checkSet("checkSet");

        field = getField("flightNo");
        checkSet("checkSet");

        if(!expectedConstructor.equals(constructors[0].toGenericString()))
            response += "Expected Constructor: public Passenger(String passportNumber, String firstName, String lastName, String flightNo)\n";
        
        assertEquals(expectedConstructor, constructors[0].toGenericString());
        assertTrue(passed);

        total += 2;
    }
    
    private void runFieldTest(String fName, String mod,String typeJ,String typeN){

        fieldName = fName;
        modifier = mod;
        typeJavaName = typeJ;
        typeName = " " + typeN + " ";
        
        expectedField = modifier + typeName + fieldName;

        field = getField(fieldName);

        if(field != null){
            
            /*  Code to check constructor goes here. Constructors force initialisation so only variables that are not in the constructor should be checked
             *  in this way ~ Z is working on this;
             * 
             * checkDeclaredOnly();
             */

            checkExpectedField(fieldName, expectedField);

        }
        else
            response += "Incorrect. Expected field: " + expectedField + "\n";

        System.out.println(response);
    }

    private Field getField(String fieldName){
        
        Field testField = null;
       
            try {
                testField = testClass.getDeclaredField(fieldName);
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

    private void checkSet(Object expectedValue){
               
        field.setAccessible(true);

        try {
            value = field.get(passenger);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (value == null){
            response += fieldName + " variable is not set.\n";
            passed = false;
            return;
        }

        if (value != expectedValue){
            response += fieldName + " variable is not set to value from constructor.\n";
            passed = false;
        }
    }

    private void checkExpectedField(String fieldName, String expectedField){

        String isExpectedField = field.toString();

        System.out.println(isExpectedField);

        passed = isExpectedField.contains(modifier + " " + typeJavaName);

        if(passed){
            passed = isExpectedField.contains(testClass.getName() + "." + fieldName);
            response += "Correct field.\n";
        }
        else
            response += "Incorrect. Expected field: " + expectedField + ".\n";
    }
}
    

