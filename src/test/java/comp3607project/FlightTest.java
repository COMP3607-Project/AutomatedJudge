package comp3607project;

import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FlightTest extends TestTemplate{
    private Flight flight1;
    private Field[] flightFields = Flight.class.getDeclaredFields();


    public FlightTest(){
        flight1 = new Flight("POS123", "JFK", "POS", LocalDateTime.of(2023, 1, 23, 10, 00, 00));
    }

    @Before
    public void init(){
        mark = 0;
        response = "";
        declaredOnly = false;
        passed = false;
        field = null;
        testClass = Flight.class;
    }

    @Test
    public void testFlightNo(){
        runFieldTest(testClass, "flightNo", "private", String.class.getName(), "String");

        assertTrue(passed);    

        mark = passed ? 1 : 0;
        results.add(new Feedback("Flight", "flightNo Attribute", mark, response));
    }

    @Test
    public void testDestination(){
        runFieldTest(testClass, "destination", "private", String.class.getName(), "String");
        assertTrue(passed); 
        mark = passed ? 1 : 0;
        results.add(new Feedback("Flight", "destination Attribute", mark, response));
    }

    @Test
    public void testOrigin(){
        runFieldTest(testClass, "origin", "private", String.class.getName(), "String");
        assertTrue(passed); 
        mark = passed ? 1 : 0;
        results.add(new Feedback("Flight", "origin Attribute", mark, response));
    }

    @Test
    public void testFlightDate(){
        runFieldTest(testClass, "flightDate", "private", LocalDateTime.class.getName(), "LocalDateTime");
        assertTrue(passed); 
        mark = passed ? 1 : 0;
        results.add(new Feedback("Flight", "flightDate Attribute", mark, response));
    }

    @Test
    public void testManifest(){
        runFieldTest(testClass, "manifest", "private", LuggageManifest.class.getName(), "LuggageManifest");
        assertTrue(passed); 
        mark = passed ? 1 : 0;
        results.add(new Feedback("Flight", "manifest Attribute", mark, response));
    }

    @Test
    public void testConstructor(){
        try{
            assertEquals("POS123",flight1.getFlightNo());
            assertEquals("JFK",flight1.getDestination());
            assertEquals("POS",flight1.getOrigin());
            assertEquals("2023-01-23T10:00",flight1.getFlightDate());
            assertTrue(flight1.getManifest() instanceof LuggageManifest);
            response += "Correct initialization of attributes";
            mark = 2;
        }catch (AssertionError e){

            response += "Incorrect initialization of attributes";
            mark = 0;
        }finally{
            results.add(new Feedback("Flight", "toString method", mark, response));
        }
    }

    @Test 
    public void testCheckInLuggage(){
        try{
            Passenger p = new Passenger("TA890789", "Joe", "Bean", "POS123");
            
            if(p.getFlightNo().equals(flight1.getFlightNo())){
                String details = p.toString();
                String cost = flight1.getManifest().getExcessLuggageCostByPassenger("TA890789");
                if(cost.equals("No Cost"))
                    cost = "0.00";

                if(p.getNumLuggage() == 0)
                    details += " \nNo Luggage to add. \n";
                else
                    details += " Pieces Added: (" + p.getNumLuggage() + "). ExcessCost: $" + cost;
                
                assertEquals(details ,flight1.checkInLuggage(p));
            }else
                assertEquals("Invalid flight", flight1.checkInLuggage(p));

            mark = 5;
            response += "Correct functionality exhibited.";
        }catch (AssertionError e){
            mark = 5;
            response += "Incorrect functionality exhibited.";
        }finally{
            results.add(new Feedback("Flight", "checkInLuggage method", mark, response));
        }
    }

    @Test 
    public void testPrintLuggageManifest(){
        try{
            Passenger p = new Passenger("TA890789", "Joe", "Bean", "POS123");
            flight1.checkInLuggage(p);

            assertEquals(flight1.getManifest().toString(), flight1.printLuggageManifest());
            mark = 1;
            response += "Correct respresentation of flight manifest";
        }catch (AssertionError e){
            mark = 0;
            response += "Incorrect respresentation of flight manifest";            
        }finally{
            results.add(new Feedback("Flight", "printLuggageManifest method", mark, response));
        }
    }

    @Test 
    public void testGetAllowedLuggage(){
        Method allowedLuggage;
        try{
            allowedLuggage = Flight.class.getDeclaredMethod("getAllowedLuggage", char.class);
        }catch(NoSuchMethodException e){
            passed = false;
        }

        if(passed){
            passed = false;
            assertTrue(allowedLuggage.toGenericString().contains(" class "));
            assertEquals(3, Flight.getAllowedLuggage('F'));
            assertEquals(2, Flight.getAllowedLuggage('B'));
            assertEquals(1, Flight.getAllowedLuggage('P'));
            assertEquals(0, Flight.getAllowedLuggage('E'));
        }
        
        mark = passed ? 2: 0;
        response = passed ? "Correct assignment of luggage pieces": "Incorrect assignment of luggage pieces";
        results.add(new Feedback("Flight", "getAllowedLuggage method", mark, response));
    }

    @Test 
    public void testToString(){
        assertEquals("POS123 DESTINATION: JFK ORIGIN: POS 2023-01-23T10:00", flight1.toString().strip());
        passed = true;
        
        if (passed){
            response += "Correct format";
            mark = 1;
        }else{
            response += "Incorrect format";
            mark = 0;
        }
        results.add(new Feedback("Flight", "toString method", mark, response));
    }
}
