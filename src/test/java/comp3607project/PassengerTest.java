package comp3607project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;


import org.junit.Before;
import org.junit.Test;

public class PassengerTest extends TestTemplate{
    
    @Before
    public void initPassengerTest() {
        
        response = "";
        mark = 0;
        declaredOnly = false;
        passed = false;
        field = null;
        testClass = Passenger.class;
        passenger = new Passenger("checkSet","checkSet","checkSet","checkSet");

    }

    @Test
    public void testPassportNumberField(){

        runFieldTest(testClass, "passportNumber", "private", String.class.getName(), "String");

        assertTrue(passed);

        mark += 1;
                System.out.println(mark);


    }

    @Test
    public void testFlightNoField(){
        
        runFieldTest(testClass, "flightNo", "private", String.class.getName(), "String");

        assertTrue(passed);

        mark += 1;
                System.out.println(mark);


    }

    @Test
    public void testfirstNameField(){
        
        runFieldTest(testClass, "firstName", "private", String.class.getName(), "String");

        assertTrue(passed);

        mark += 1;
        System.out.println(mark);

    }

    @Test
    public void testlastNameField(){
        
        runFieldTest(testClass,"lastName", "private", String.class.getName(), "String");

        assertTrue(passed);

        mark += 1;
                System.out.println(mark);

    }

    @Test
    public void testNumLuggageField(){
        
        runFieldTest(testClass,"numLuggage", "private", "int", "int");

        assertTrue(passed);

        mark += 1;
                System.out.println(mark);


    }

    @Test
    public void testCabinClassField(){
        
        runFieldTest(testClass,"cabinClass", "private", "char", "char");

        assertTrue(passed);

        mark += 1;
                System.out.println(mark);

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
        else
            response += "Correct Constructor";

        checkRandom("numLuggage");

        if(passed)
            mark += 2;

        checkRandom("cabinClass");

        if(passed)
            mark += 1;

        assertEquals(expectedConstructor, constructors[0].toGenericString());
        assertTrue(passed);

        mark += 2;

    }

    @Test
    public void assignRandomCabinClassTest() throws IllegalArgumentException, IllegalAccessException{

        field = getField("cabinClass");
        field.setAccessible(true);
        
        passed = false;

        checkMethod("assignRandomCabinClass","void","void",null,(Class<?>[]) null);

        boolean passedF = false;
        boolean passedB = false;
        boolean passedP = false;
        boolean passedE = false;

        int i = 0;

        while (passedF == false || passedB == false || passedP == false || passedE == false){

            Passenger test = new Passenger("checkSet","checkSet","checkSet","checkSet");

            char value = (char) field.get(test);

            if(value == 'F')
                passedF = true;

            if(value == 'B')
                passedB = true;

            if(value == 'P')
                passedP = true;

            if(value == 'E')
                passedE = true;

            if(i == 20)
                break;

            i++;

        }

        if(passedF == false)
            response += "cabinClass value 'F' does not exist\n";

        if(passedB == false)
            response += "cabinClass value 'B' does not exist\n";

        if(passedP == false)
            response += "cabinClass value 'P' does not exist\n";

        if(passedE == false)
            response += "cabinClass value 'E' does not exist\n";

        if(!passedF || !passedB || !passedP || !passedE)
            passed = false;

        assertTrue(passed);
  
    }

    @Test 
    public void testToString() throws IllegalArgumentException, IllegalAccessException{

        String passportNum = "TA890789";
        String fName = "Joe";
        String lName = "Bean";
        String flightNo = "POS123";

        Passenger p = new Passenger(passportNum, fName, lName, flightNo);
        
        field = getField("numLuggage");
        field.setAccessible(true);
        
        int luggageValue = (int)field.get(p);

        field = getField("cabinClass");
        field.setAccessible(true);

        char classValue = (char)field.get(p);

        String expectedToString = "PP NO. " + passportNum + " NAME: " + fName.charAt(0) + "." + lName +" NUMLUGGAGE: " + luggageValue +  " CLASS: " + classValue;

        
        passed = p.toString().replaceAll(" ", "").contains(expectedToString.replaceAll(" ", ""));     
        mark = passed ? 1 : 0;
        response = passed ? "Correct format": "Incorrect format";
        
        results.add(new Feedback("Passenger", "toString method", mark, response));

        assertTrue(passed);

    }

    private void checkRandom(String fieldName){

        ArrayList<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger("checkSet","checkSet","checkSet","checkSet"));
        passengers.add(new Passenger("checkSet","checkSet","checkSet","checkSet"));
        passengers.add(new Passenger("checkSet","checkSet","checkSet","checkSet"));
        passengers.add(new Passenger("checkSet","checkSet","checkSet","checkSet"));
        passengers.add(new Passenger("checkSet","checkSet","checkSet","checkSet"));

        passed = false;

        field = getField("numLuggage");
        field.setAccessible(true);
        
        for (int i = 0; i< passengers.size() - 1; i++){
            Passenger p = passengers.get(i);
            Passenger p1 = passengers.get(i+1);

            try {
                Object value1 = field.get(p);
                Object value2 = field.get(p1);

                if(value1 != value2)
                    passed = true;

            } catch (IllegalArgumentException e) {

                e.printStackTrace();
            } catch (IllegalAccessException e) {

                e.printStackTrace();
            }
        }

    }

    //check to make sure all good
    private void checkMethod(String methodName, String genericReturnType, String normalReturnType, String normalParaTypes, Class<?>... classSum){

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
                    }

                }
                else
                    response += errorMessage;
        }
        catch(Exception e)
        {
            passed = false;
        }

    }

   /*  private void testMethodParameters(Method testMethod){
        testMethod.getP
    }*/



    private void checkSet(Object expectedValue){
               
        field.setAccessible(true);

        try {
            value = field.get(passenger);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (value == null){
            response += fieldName + " is not set.\n";
            passed = false;
            return;
        }

        if (value != expectedValue){
            response += fieldName + " is not set to value from constructor.\n";
            passed = false;
        }

        response += fieldName + " is set correctly.\n";
    }
 
}
    

 