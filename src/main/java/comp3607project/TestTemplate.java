package comp3607project;


import org.junit.runner.JUnitCore;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public abstract class TestTemplate extends Exception{
    protected String response = "";
    protected int mark;
    protected boolean passed;
    protected boolean declaredOnly;
    protected String typeJavaName;
    protected String typeName;
    protected String modifier;
    protected String className;
    protected Field field;
    protected Object value;
    protected Passenger passenger;
    protected String fieldName;
    protected String expectedField;
    protected Class<?> testClass;

    protected static int cleanCodeMark = 5;
    protected static int passedTestsMark = 14;



    protected void runFieldTest(Class<?> testClass, String fName, String mod,String typeJ,String typeN){

        fieldName = fName;
        modifier = mod;
        typeJavaName = typeJ;
        typeName = " " + typeN + " ";
        
        expectedField = modifier + typeName + fieldName;

        field = getField(fieldName);

        if(field != null){
            

            checkExpectedField(fieldName, expectedField);

        }
        else{
            response += "Incorrect. Expected field: " + expectedField + "\n";
            cleanCodeMark--;
        }

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

        passed = isExpectedField.contains(modifier + " " + typeJavaName);

        if(passed){
            passed = isExpectedField.contains(testClass.getName() + "." + fieldName);
            response += "Correct field.\n";
        }
        else
            response += "Incorrect. Expected field: " + expectedField + ".\n";
    }

    protected void checkMethod(String methodName, String genericReturnType, String normalReturnType, String normalParaTypes, Class<?>... classSum){

        Method testMethod = null;
        
        if(normalParaTypes == null)
            normalParaTypes = "";

        String javaTypes = "";

        if(classSum != null){
            for(Class<?> c: classSum)
                javaTypes += c.getName() + ",";

            javaTypes = javaTypes.substring(0, javaTypes.length() - 1);
        }

        String errorMessage = "Expected Method Return Type: " + normalReturnType + "\nExpected Method: " + methodName + "(" + normalParaTypes +")\n";

        try {
            testMethod = testClass.getDeclaredMethod(methodName, classSum);


                if(testMethod.getReturnType().getName().equals(genericReturnType)){

                    passed = true;
                    
                    if(testMethod.toString().contains("." + methodName + "(" + javaTypes + ")"))
                        response += "Correct method\n";
                    else{
                        response += errorMessage;
                        passed = false;
                        cleanCodeMark--;
                    }

                }
                else
                    response += errorMessage;
        }
        catch(Exception e)
        {
            passed = false;
            cleanCodeMark--;
        }



    }

    public static int getPassedTestsMark(){
        return passedTestsMark;
    }

    public static int getCleanCodeMarks(){
        return cleanCodeMark;
    }

    //abstracts
    public abstract void init();
    public abstract void testToString() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException;
}
             
