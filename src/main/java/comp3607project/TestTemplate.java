package comp3607project;

import org.junit.*;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.ArrayList;

public abstract class TestTemplate extends Exception{
    protected String response = "";
    protected int mark;
    protected boolean passed;
    protected boolean declaredOnly;
    protected String typeJavaName;
    protected String typeName;
    protected String modifier;
    protected Field field;
    protected Object value;
    protected Passenger passenger;
    protected String fieldName;
    protected String expectedField;
    protected Class<?> testClass;

    //temporary arraylist for storing feedback. should be in the class that generates the pdf
    protected ArrayList<Feedback> results = new ArrayList<Feedback>();


    protected void runFieldTest(Class<?> testClass, String fName, String mod,String typeJ,String typeN){

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

    protected Field getField(String fieldName){
        
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

    protected void checkDeclaredOnly(){
       
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

    protected void checkExpectedField(String fieldName, String expectedField){
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
             