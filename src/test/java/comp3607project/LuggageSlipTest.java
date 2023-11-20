package comp3607project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

public class LuggageSlipTest extends TestTemplate{
    
    private static int total = 0;

    private String response;
    boolean passed;
    boolean declaredOnly;
    String typeJavaName;
    String typeName;
    String modifier;
    private Field field;
    Object value;
    private String fieldName;
    private Class<?> testClass;
    LuggageSlip luggageSlip;
    LuggageSlip luggageSlip1;
    Passenger passenger;
    Flight flight;

    public LuggageSlipTest(){
        passenger = new Passenger("jfk","jfk","jfk","jfk");
        flight = new Flight("jfk","jfk","jfk", null);
        luggageSlip = new LuggageSlip(passenger, flight);
        luggageSlip1 = new LuggageSlip(passenger, flight, "xyz");
        testClass = LuggageSlip.class;
    }
    
    @Before
    public void initLuggageSlipTest() {
        
        response = "";
        declaredOnly = false;
        passed = false;
        field = null;
        testClass = LuggageSlip.class;
    }

    @Test
    public void testPassengerField(){
        
        runFieldTest(testClass,"owner", "private", Passenger.class.getName(), "Passenger");

        assertTrue(passed);

        total += 1;

        System.out.println(total);

    }

    @Test
    public void luggageSlipIDCounterField(){
        
        runFieldTest(testClass,"luggageSlipIDCounter", "private static", "int", "int");

        assertTrue(passed);

        total += 1;
                System.out.println(total);

    }

    @Test
    public void luggageSlipIDField(){
        
        runFieldTest(testClass,"luggageSlipID", "private", String.class.getName(), "String");

        assertTrue(passed);

        total += 1;
                System.out.println(total);

    }

    @Test
    public void labelField(){
        
        runFieldTest(testClass,"label", "private", String.class.getName(), "String");

        assertTrue(passed);

        total += 1;
                System.out.println(total);

    }

    @Test
    public void testConstrctor() throws IllegalArgumentException, IllegalAccessException{

        Constructor<?>[] constructors = testClass.getConstructors();

        String expectedConstructor = "public " + testClass.getName() + "("+ Passenger.class.getName() + "," + Flight.class.getName() + ")";

        passed = true;

        field = getField("owner");
        checkSet(passenger, luggageSlip);

        field = getField("label");
        checkSet("", luggageSlip);

        field = getField("luggageSlipIDCounter");
        field.setAccessible(true);
        int value = (int)field.get(luggageSlip);
        checkSet(value, luggageSlip);

        field = getField("luggaeSlipID");
        checkSet(flight.getFlightNo() + "_" + passenger.getLastName() + "_" + "1", luggageSlip);

        if(!expectedConstructor.equals(constructors[0].toGenericString()) && !expectedConstructor.equals(constructors[1].toGenericString()))
            response += "Expected Constructor: public Passenger(String passportNumber, String firstName, String lastName, String flightNo)\n";
        else
            response += "Correct Constructor";

        results.add(new Feedback(className,"Constructor", mark, response));

        assertEquals(expectedConstructor, constructors[0].toGenericString());

        assertTrue(passed);
    }

    @Test
    public void testOverloadedConstrctor() throws IllegalArgumentException, IllegalAccessException{

        Constructor<?>[] constructors = testClass.getConstructors();

        String expectedConstructor = "public " + testClass.getName() + "("+ Passenger.class.getName() + "," + Flight.class.getName() + "," 
                                     + String.class.getName() + ")";

        passed = true;

        field = getField("owner");
        checkSet(passenger, luggageSlip);

        field = getField("label");
        checkSet("xyz", luggageSlip);

        field = getField("luggageSlipIDCounter");

        checkSet(2, luggageSlip);

        field = getField("luggaeSlipID");
        checkSet(flight.getFlightNo() + "_" + passenger.getLastName() + "_" + "1", luggageSlip);

        if(!expectedConstructor.equals(constructors[0].toGenericString()) && !expectedConstructor.equals(constructors[1].toGenericString()))
            response += "Expected Constructor: public Passenger(String passportNumber, String firstName, String lastName, String flightNo)\n";
        else
            response += "Correct Constructor";

        results.add(new Feedback(className,"Constructor", mark, response));

        assertEquals(expectedConstructor, constructors[0].toGenericString());

        assertTrue(passed);
    }

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
        }

        response += fieldName + " is set correctly.\n";
    }
}
