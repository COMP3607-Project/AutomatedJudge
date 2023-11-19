package comp3607project;

import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.time.LocalDateTime;

public class FlightTest extends TestTemplate{
    private Flight flight1;

    @Before
    public void init(){
        mark = 0;
        response = "";
        declaredOnly = false;
        passed = false;
        field = null;
        testClass = Flight.class;
        flight1 = new Flight("POS123", "JFK", "POS", LocalDateTime.of(2023, 1, 23, 10, 00, 00));
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
        passed = "POS123".equals(flight1.getFlightNo()) &&
         "JFK".equals(flight1.getDestination()) &&
         "POS".equals(flight1.getOrigin()) &&
         LocalDateTime.of(2023, 1, 23, 10, 00, 00).equals(flight1.getFlightDate()) &&
         (flight1.getManifest() instanceof LuggageManifest);


        mark = passed ? 2: 0;
        response = passed ? "Correct initialization of attributes.": "Incorrect initialization of attributes.";
        results.add(new Feedback("Flight", "toString method", mark, response));

        assertEquals("POS123",flight1.getFlightNo());
        assertEquals("JFK",flight1.getDestination());
        assertEquals("POS",flight1.getOrigin());
        assertEquals(LocalDateTime.of(2023, 1, 23, 10, 00, 00), flight1.getFlightDate());
        assertTrue(flight1.getManifest() instanceof LuggageManifest);
    }

    @Test 
    public void testCheckInLuggage(){
        Passenger p = new Passenger("TA890789", "Joe", "Bean", "POS1234");
        String details = "";
        if(p.getFlightNo().equals(flight1.getFlightNo())){
            details = flight1.getManifest().addLuggage(p, flight1);
            passed =  details.strip().equals(flight1.checkInLuggage(p).strip());
        }else
            passed = flight1.checkInLuggage(p).strip().equals("Invalid flight");

        mark = passed ? 5: 0;
        response = passed ? "Correct functionality displayed for checking in luggage.": "Incorrect functionality displayed for checking in luggage.";
        
        results.add(new Feedback("Flight", "checkInLuggage method", mark, response));

        if(p.getFlightNo().equals(flight1.getFlightNo()))
            assertEquals(details.strip(),flight1.checkInLuggage(p).strip());
        else
            assertEquals("Invalid flight", flight1.checkInLuggage(p).strip());
    }

    @Test 
    public void testPrintLuggageManifest(){
        Passenger p = new Passenger("TA890789", "Joe", "Bean", "POS123");
        flight1.checkInLuggage(p);

        passed = flight1.getManifest().toString().strip().contains(flight1.printLuggageManifest().strip());

        mark = passed ? 1: 0;
        response = passed ? "Correct respresentation of flight manifest": "Incorrect respresentation of flight manifest";         

        results.add(new Feedback("Flight", "printLuggageManifest method", mark, response));
        assertTrue(flight1.getManifest().toString().strip().contains(flight1.printLuggageManifest().strip()));
    }

    @Test 
    public void testGetAllowedLuggage(){
        Method allowedLuggage = null;
        try{
            allowedLuggage = Flight.class.getDeclaredMethod("getAllowedLuggage", char.class);
            passed = true;
        }catch(NoSuchMethodException e){
            passed = false;
        }

        passed = allowedLuggage.toGenericString().contains(" static ") &&
         (3==Flight.getAllowedLuggage('F')) &&
         (2==Flight.getAllowedLuggage('B')) &&
         (1==Flight.getAllowedLuggage('P')) &&
         (0==Flight.getAllowedLuggage('E'));
 
        mark = passed ? 2: 0;
        response = passed ? "Correct assignment of luggage pieces": "Incorrect assignment of luggage pieces";
        results.add(new Feedback("Flight", "getAllowedLuggage method", mark, response));

        assertTrue(allowedLuggage.toGenericString().contains(" static "));
        assertEquals(3, Flight.getAllowedLuggage('F'));
        assertEquals(2, Flight.getAllowedLuggage('B'));
        assertEquals(1, Flight.getAllowedLuggage('P'));
        assertEquals(0, Flight.getAllowedLuggage('E'));
    }

    @Test 
    public void testToString(){
        passed = flight1.toString().strip().equals("POS123 DESTINATION: JFK ORIGIN: POS 2023-01-23T10:00");
                
        mark = passed ? 1: 0;
        response = passed ? "Correct format": "Incorrect format";
        results.add(new Feedback("Flight", "toString method", mark, response));

        assertEquals("POS123 DESTINATION: JFK ORIGIN: POS 2023-01-23T10:00", flight1.toString().strip());
    }
}
