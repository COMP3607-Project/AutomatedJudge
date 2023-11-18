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
