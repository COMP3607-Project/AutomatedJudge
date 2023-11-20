package comp3607project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class PassengerTest extends TestTemplate{
    
    public PassengerTest(){
        passenger = new Passenger("checkSet","checkSet","checkSet","checkSet");
        testClass = Passenger.class;
        className = "Passenger";
    }

    @Before
    public void init() {
        
        response = "";
        mark = 0;
        declaredOnly = false;
        passed = false;
        field = null;

    }

    @Test
    public void testPassportNumberField(){

        runFieldTest(testClass, "passportNumber", "private", String.class.getName(), "String");
        
        if(passed)
            mark += 1;

        ReportContent.addFeedback(new Feedback(className,"passportNumber Field", mark, response));

        if(!passed)
            passedTestsMark--;
            
        assertTrue(passed);
        
    }

    @Test
    public void testFlightNoField(){
        
        runFieldTest(testClass, "flightNo", "private", String.class.getName(), "String");

        if(passed)
            mark += 1;
        
        ReportContent.addFeedback(new Feedback(className,"flightNo Field", mark, response));
        
        if(!passed)
            passedTestsMark--;

        assertTrue(passed);

    }

    @Test
    public void testfirstNameField(){
        
        runFieldTest(testClass, "firstName", "private", String.class.getName(), "String");

        if(passed)
            mark += 1;
        
        ReportContent.addFeedback(new Feedback(className,"firstName Field", mark, response));

        if(!passed)
            passedTestsMark--;

        assertTrue(passed);

    }

    @Test
    public void testlastNameField(){
        
        runFieldTest(testClass,"lastName", "private", String.class.getName(), "String");

        if(passed)
            mark += 1;
        
        ReportContent.addFeedback(new Feedback(className,"lastName Field", mark, response));

        if(!passed)
            passedTestsMark--;

        assertTrue(passed);

    }

    @Test
    public void testNumLuggageField(){
        
        runFieldTest(testClass,"numLuggage", "private", "int", "int");

        if(passed)
            mark += 1;
         
        ReportContent.addFeedback(new Feedback(className,"numLuggage Field", mark, response));
        
        if(!passed)
            passedTestsMark--;

        assertTrue(passed);

    }

    @Test
    public void testCabinClassField(){
        
        runFieldTest(testClass,"cabinClass", "private", "char", "char");

        if(passed)
            mark += 1;
         
        ReportContent.addFeedback(new Feedback(className,"cabinClass Field", mark, response));

        if(!passed)
            passedTestsMark--;

        assertTrue(passed);

    }

    @Test
    public void testConstructor(){

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
        
        if(expectedConstructor.equals(constructors[0].toGenericString()))   
            mark += 2;

        ReportContent.addFeedback(new Feedback(className,"Constructor", mark, response));

        if(!passed)
            passedTestsMark--;

        assertEquals(expectedConstructor, constructors[0].toGenericString());
        assertTrue(passed);

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

        if(passed)
            mark += 2;

        ReportContent.addFeedback(new Feedback(className,"assignRandomCabinClass Method", mark, response));

        if(!passed)
            passedTestsMark--;

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
        String actualToString = (p.toString().replaceAll(" ","")).toLowerCase().replaceAll(".","").replaceAll(":","");

        expectedToString = (expectedToString.replaceAll(" ","")).toLowerCase().replaceAll(".","").replaceAll(":","");

        
        passed = actualToString.contains(expectedToString);     
        mark = passed ? 3 : 0;
        response = passed ? "Correct format": "Incorrect format. Please verify with rubric.";
        
        ReportContent.addFeedback(new Feedback("Passenger", "toString Method", mark, response));
        
        if(!passed)
            passedTestsMark--;

        assertEquals(expectedToString, actualToString);
        assertTrue(passed);

    }

    
    
    //methods
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
    

 