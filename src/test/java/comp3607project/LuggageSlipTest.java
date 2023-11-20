package comp3607project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.lang.reflect.Constructor;
import org.junit.Before;
import org.junit.Test;

public class LuggageSlipTest extends TestTemplate{

    private Flight flight;
    private LuggageSlip luggageSlip;
    private LuggageSlip luggageSlip1;


    public LuggageSlipTest(){
        passenger = new Passenger("jfk","jfk","jfk","jfk");
        flight = new Flight("jfk","jfk","jfk", null);
        luggageSlip = new LuggageSlip(passenger, flight);
        luggageSlip1 = new LuggageSlip(passenger, flight, "xyz");
        testClass = LuggageSlip.class;
        className = "LuggageSlip";
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
    public void testPassengerField(){
        
        runFieldTest(testClass, "owner", "private", Passenger.class.getName(), "Passenger");
        
        if(passed)
            mark += 1;

        ReportContent.addFeedback(new Feedback(className,"Passenger Field", mark, response));

        if(!passed)
            passedTestsMark--;
            
        assertTrue(passed);

    }

    @Test
    public void testluggageSlipIDCounterField(){
        
        runFieldTest(testClass, "luggageSlipIDCounter", "private static", "int", "int");

        if(passed)
            mark += 1;

        ReportContent.addFeedback(new Feedback(className,"luggageSlipCounter Field", mark, response));

        if(!passed)
            passedTestsMark--;
        
        assertTrue(passed);
    
    }

    @Test
    public void testluggageSlipIDField(){
        
        runFieldTest(testClass,"luggageSlipID", "private", String.class.getName(), "String");

        if(passed)
            mark += 1;

        ReportContent.addFeedback(new Feedback(className,"luggageSlipID Field", mark, response));

        if(!passed)
            passedTestsMark--;
            
        assertTrue(passed);
        
    }

    @Test
    public void labelField(){
        
        runFieldTest(testClass,"label", "private", String.class.getName(), "String");

        if(passed)
            mark += 1;

        ReportContent.addFeedback(new Feedback(className,"label Field", mark, response));

        if(!passed)
            passedTestsMark--;
        
        assertTrue(passed);
    

    }

    @Test
    public void testConstructor() throws IllegalArgumentException, IllegalAccessException{

        Constructor<?>[] constructors = testClass.getConstructors();

        String expectedConstructor = "public " + testClass.getName() + "("+ Passenger.class.getName() + "," + Flight.class.getName() + ")";

        boolean track = true;

        mark = 3;

        passed = true;

        fieldName = "owner";
        field = getField(fieldName);
        checkSet(passenger, luggageSlip);

        if(!passed){
            mark-=1;
            track = false;
        }

        passed =true;

        fieldName = "label";
        field = getField(fieldName);
        checkSet("", luggageSlip);

        if(!passed){
            mark-=1;
            track = false;
        }

        passed = true;
        
        fieldName = "luggageSlipID";
        field = getField(fieldName);
        field.setAccessible(true);

        if(field.get(luggageSlip) == null){
            passed = false;
            mark-=1;
        }

        if(!expectedConstructor.equals(constructors[0].toGenericString()) && !expectedConstructor.equals(constructors[1].toGenericString())){
            response += "Expected Constructor: public LuggageSlip(Passenger p, Flight f, String label)\n";
            if(mark > 0){
                mark -= 1;
                track = false;
            }
        }
        else
            response += "Correct Constructor";

        results.add(new Feedback(className,"Constructor", mark, response));

        if(!passed)
            passedTestsMark--;

        assertTrue(track);

    }

    @Test
    public void testOverloadedConstrctor() throws IllegalArgumentException, IllegalAccessException{

        Constructor<?>[] constructors = testClass.getConstructors();

        String expectedConstructor = "public " + testClass.getName() + "("+ Passenger.class.getName() + "," + Flight.class.getName() + "," 
                                     + String.class.getName() + ")";
        boolean track = true;

        mark = 3;

        passed = true;

        fieldName = "owner";
        field = getField(fieldName);
        checkSet(passenger, luggageSlip1);

        if(!passed){
            mark-=1;
            track = false;
        }

        passed = true;

        fieldName = "label";
        field = getField(fieldName);
        checkSet("xyz", luggageSlip1);

        if(!passed){
            mark-=1;
            track = false;
        }

        passed = true;
        
        fieldName = "luggageSlipID";
        field = getField(fieldName);
        field.setAccessible(true);
          
        if(field.get(luggageSlip1) == null){
            passed = false;
            mark-=1;
        }

        if(!expectedConstructor.equals(constructors[0].toGenericString()) && !expectedConstructor.equals(constructors[1].toGenericString())){
            response += "Expected Constructor: public LuggageSlip(Passenger p, Flight f, String label)\n";
            if(mark > 0)
                mark -= 1;
                track = false;
        }
        else
            response += "Correct Constructor";

        results.add(new Feedback(className,"Overloaded Constructor", mark, response));

        
        if(!track)
            passedTestsMark--;

        assertTrue(track);
    }

    @Test
    public void hasOwnerTest(){

        checkMethod("hasOwner","boolean","boolean","String", String.class);

        passed = false;

        if(luggageSlip.hasOwner("jfk")){
            if(!luggageSlip.hasOwner("huhh"))
                passed = true;
                mark += 2;
                response = "Correct Functionality!";
        }

        if(!passed)
            response += "Incorrect functionality\n";

        results.add(new Feedback(className,"hasOwner Method", mark, response));

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
        LuggageSlip ls = new LuggageSlip(p, flight);

        testClass = Passenger.class;
        
        field = getField("numLuggage");
        field.setAccessible(true);
        
        int luggageValue = (int)field.get(p);

        field = getField("cabinClass");
        field.setAccessible(true);

        char classValue = (char)field.get(p);

        testClass = LuggageSlip.class;

        String expectedToString = ls.getLuggageSlipID() + " PP NO. " + passportNum + " NAME: " + fName.charAt(0) + "." + lName +" NUMLUGGAGE: " + luggageValue +  " CLASS: " + classValue;
        String actualToString = (ls.toString().replaceAll(" ","")).toLowerCase().replaceAll(".","").replaceAll(":","");

        expectedToString = (expectedToString.replaceAll(" ","")).toLowerCase().replaceAll(".","").replaceAll(":","");

        passed = actualToString.contains(expectedToString);     
        mark = passed ? 2 : 0;
        response = passed ? "Correct format": "Incorrect format. Please verify with rubric\n";
        
        ReportContent.addFeedback(new Feedback("Passenger", "toString Method", mark, response));
        
        if(!passed)
            passedTestsMark--;

        assertEquals(expectedToString, actualToString);
        assertTrue(passed);

    }

    //methods
    private void checkSet(Object expectedValue, Object thisClass){
               
        field.setAccessible(true);

        try {
            value = field.get(thisClass);
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
            return;
        }

        response += fieldName + " is set correctly.\n";
    }
}
