package comp3607project;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class LuggageSlipTest {
    
    private static int total = 0;

    private String response;
    boolean passed;
    boolean declaredOnly;
    String typeJavaName;
    String typeName;
    String modifier;
    private Field field;
    Object value;
    private LuggageSlip luggageSlip;
    private String fieldName;
    private String expectedField;
    private Class<?> testClass;

    @Before
    public void initLuggageSlipTest() {
        
        response = "";
        declaredOnly = false;
        passed = false;
        field = null;
        testClass = LuggageSlip.class;
        luggageSlip = new LuggageSlip(new Passenger ("jfk","jfk","jfk","jfk"), new Flight ("jfk","jfk","jfk",null));

    }

    @Test
    public void testPassengerField(){
        
        runTest("owner", "private", Passenger.class.getName(), "Passenger");

        assertTrue(passed);

        total += 1;

        System.out.println(total);

    }

    @Test
    public void luggageSlipIDCounterField(){
        
        runTest("luggageSlipIDCounter", "private static", "int", "int");

        assertTrue(passed);

        total += 1;
                System.out.println(total);

    }

    @Test
    public void luggageSlipIDField(){
        
        runTest("luggageSlipID", "private", String.class.getName(), "String");

        assertTrue(passed);

        total += 1;
                System.out.println(total);

    }

    @Test
    public void labelField(){
        
        runTest("label", "private", String.class.getName(), "String");

        assertTrue(passed);

        total += 1;
                System.out.println(total);

    }



    private void runTest(String fName, String mod,String typeJ,String typeN){

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
                testField = testClass.getDeclaredField(fieldName); //change "Class".class.getDeclaredField(fieldName), depending on the class you are getting the field from
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
            value = field.get(null);
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

        passed = isExpectedField.contains(modifier + " " + typeJavaName);

        System.out.println(passed);

        if(passed){
            passed = isExpectedField.contains(testClass.getName() + "." + fieldName);
            response += "Correct field.\n";
        }
        else
            response += "Incorrect. Expected field: " + expectedField + ".\n";
    }
}
