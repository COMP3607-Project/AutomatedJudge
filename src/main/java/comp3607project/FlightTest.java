package comp3607project;

import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.time.LocalDateTime;

public class FlightTest extends TestTemplate{
    private Flight flight1;
    private Field[] flightFields = Flight.class.getDeclaredFields();


    public FlightTest(){
        flight1 = new Flight("POS123", "JFK", "POS", LocalDateTime.of(2023, 1, 23, 10, 00, 00));
    }

    @Before
    public void init(){
        response = "";
        mark = 0;
        grade = true; //determines if student get grade
        found = false; //determines if field was found aka adheres to naming convention
    }

    @Test
    public void testFlightNo(){
        checkAttribute(flight1, flightFields, "flightNo", "private", " java.lang.String ");

        if(found)
            response += "Correct naming convention";
        else{
            response += "Incorrect naming convention -> Expected: flightNo";
            grade = false;
        }

        mark = grade ? 1 : 0;
        results.add(new Feedback("Flight", "flightNo Attribute", mark, response));
    }

    @Test
    public void testDestination(){
        checkAttribute(flight1, flightFields, "destination", "private", " java.lang.String ");

        if(found)
            response += "Correct naming convention";
        else{
            response += "Incorrect naming convention -> Expected: destination";
            grade = false;
        }

        mark = grade ? 1 : 0;
        results.add(new Feedback("Flight", "destination Attribute", mark, response));
    }

    @Test
    public void testOrigin(){
        checkAttribute(flight1, flightFields, "origin", "private", " java.lang.String ");

        if(found)
            response += "Correct naming convention";
        else{
            response += "Incorrect naming convention -> Expected: origin";
            grade = false;
        }

        mark = grade ? 1 : 0;
        results.add(new Feedback("Flight", "origin Attribute", mark, response));
    }

    @Test
    public void testFlightDate(){
        checkAttribute(flight1, flightFields, " flightDate ", "private", " java.time.LocalDateTime ");

        if(found)
            response += "Correct naming convention";
        else{
            response += "Incorrect naming convention -> Expected: flightDate";
            grade = false;
        }

        mark = grade ? 1 : 0;
        results.add(new Feedback("Flight", "flightDate Attribute", mark, response));
    }

    @Test
    public void testManifest(){
        checkAttribute(flight1, flightFields, " manifest ", "private", "LuggageManifest");

        if(found)
            response += "Correct naming convention";
        else{
            response += "Incorrect naming convention -> Expected: manifest";
            grade = false;
        }

        mark = grade ? 1 : 0;
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
        try{
            Method allowedLuggage = Flight.class.getDeclaredMethod("getAllowedLuggage", char.class);
            assertTrue(allowedLuggage.toGenericString().contains(" class "));
            assertEquals(3, Flight.getAllowedLuggage('F'));
            assertEquals(2, Flight.getAllowedLuggage('B'));
            assertEquals(1, Flight.getAllowedLuggage('P'));
            assertEquals(0, Flight.getAllowedLuggage('E'));
            mark = 2;
            response += "Correct assignment of luggage pieces";

        }catch (AssertionError | NoSuchMethodException | SecurityException e){
            mark = 0;
            response += "Incorrect assignment of luggage pieces";
            
        }finally{
            results.add(new Feedback("Flight", "getAllowedLuggage method", mark, response));
        }
    }

    @Test 
    public void testToString(){
        try{
            assertEquals("POS123 DESTINATION: JFK ORIGIN: POS 2023-01-23T10:00", flight1.toString());
            response += "Correct format";
            mark = 1;
        }catch (AssertionError e){
            response += "Incorrect format";
            mark = 0;
        }finally{
            results.add(new Feedback("Flight", "toString method", mark, response));
        }
    }
}
