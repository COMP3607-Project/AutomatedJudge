package comp3607project;


import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

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
        mark = passed ? 1 : 0;
        ReportContent.addFeedback(new Feedback("Flight", "flightNo Attribute", mark, response));

        if(!passed)
            passedTestsMark--;
        assertTrue(passed);    
    }

    @Test
    public void testDestination(){
        runFieldTest(testClass, "destination", "private", String.class.getName(), "String");
        if(!passed)
            passedTestsMark--; 

        mark = passed ? 1 : 0;
        ReportContent.addFeedback(new Feedback("Flight", "destination Attribute", mark, response));
        assertTrue(passed);
    }

    @Test
    public void testOrigin(){
        runFieldTest(testClass, "origin", "private", String.class.getName(), "String");
        if(!passed)
            passedTestsMark--;
        mark = passed ? 1 : 0;
        ReportContent.addFeedback(new Feedback("Flight", "origin Attribute", mark, response));
        assertTrue(passed);
    }

    @Test
    public void testFlightDate(){
        runFieldTest(testClass, "flightDate", "private", LocalDateTime.class.getName(), "LocalDateTime");
        if(!passed)
            passedTestsMark--;
        mark = passed ? 1 : 0;
        ReportContent.addFeedback(new Feedback("Flight", "flightDate Attribute", mark, response));
        assertTrue(passed);
    }

    @Test
    public void testManifest(){
        runFieldTest(testClass, "manifest", "private", LuggageManifest.class.getName(), "LuggageManifest");
        if(!passed)
            passedTestsMark--;
        mark = passed ? 1 : 0;
        ReportContent.addFeedback(new Feedback("Flight", "manifest Attribute", mark, response));
        assertTrue(passed);
    }

    @Test
    public void testConstructor() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
        Field field1 = Flight.class.getDeclaredField("flightNo");
        Field field2 = Flight.class.getDeclaredField("destination");
        Field field3 = Flight.class.getDeclaredField("origin");
        Field field4 = Flight.class.getDeclaredField("flightDate");
        Field field5 = Flight.class.getDeclaredField("manifest");
        field1.setAccessible(true);
        field2.setAccessible(true);
        field3.setAccessible(true);
        field4.setAccessible(true);
        field5.setAccessible(true);

        String flightNoValue = (String) field1.get(flight1);
        String destinationValue = (String) field2.get(flight1);
        String originValue = (String) field3.get(flight1);
        LocalDateTime flightDateValue = (LocalDateTime) field4.get(flight1);
        LuggageManifest manifestValue = (LuggageManifest) field5.get(flight1);

        passed = "POS123".equals(flightNoValue) &&
         "JFK".equals(destinationValue) &&
         "POS".equals(originValue) &&
         LocalDateTime.of(2023, 1, 23, 10, 00, 00).equals(flightDateValue) &&
         (manifestValue instanceof LuggageManifest);

        if(!passed)
            passedTestsMark--;
        mark = passed ? 2: 0;
        response = passed ? "Correct initialization of attributes.": "Incorrect initialization of attributes.";
        ReportContent.addFeedback(new Feedback("Flight", "Constructor", mark, response));

        assertTrue(passed);
    }

    @Test 
    public void testCheckInLuggage() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
        checkMethod("checkInLuggage", String.class.getName(), "String", "Passenger", Passenger.class);
        
        Passenger p = new Passenger("TA890789", "Joe", "Bean", "POS1234");
        String details = "";

        Field pFlightNo = Passenger.class.getDeclaredField("flightNo");
        Field fFlightNo = Flight.class.getDeclaredField("flightNo");
        Field fManifest = Flight.class.getDeclaredField("manifest");
        pFlightNo.setAccessible(true);
        fFlightNo.setAccessible(true);
        fManifest.setAccessible(true);
        String pFlightValue = (String) pFlightNo.get(p);
        String fFlightValue = (String) fFlightNo.get(flight1);
        LuggageManifest fManifestValue = (LuggageManifest) fManifest.get(flight1);

        if (passed){
            if(pFlightValue.equals(fFlightValue)){
                details = fManifestValue.addLuggage(p, flight1);
                passed =  flight1.checkInLuggage(p).strip().replaceAll(" ","").toLowerCase().contains(details.strip().toLowerCase().replaceAll(" ",""));
            }else
                passed = flight1.checkInLuggage(p).strip().replaceAll(" ","").toLowerCase().contains("invalid flight".replaceAll(" ",""));
        }

        if(!passed)
            passedTestsMark--;
        mark = passed ? 5: 0;
        response = passed ? "Correct functionality displayed for checking in luggage.": "Incorrect functionality displayed for checking in luggage.";
        
        ReportContent.addFeedback(new Feedback("Flight", "checkInLuggage method", mark, response));
        assertTrue(passed);
    }

    @Test 
    public void testPrintLuggageManifest() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
        checkMethod("printLuggageManifest", String.class.getName(), "String", null, (Class<?>[]) null);
        
        Passenger p = new Passenger("TA890789", "Joe", "Bean", "POS123");
        flight1.checkInLuggage(p);

        field = Flight.class.getDeclaredField("manifest");
        field.setAccessible(true);
        LuggageManifest manifestValue = (LuggageManifest)field.get(flight1);

        if(passed)
            passed = flight1.printLuggageManifest().strip().replaceAll(" ","").toLowerCase().contains(manifestValue.toString().replaceAll(" ","").toLowerCase().strip());
        
        if(!passed)
            passedTestsMark--;
        mark = passed ? 1: 0;
        response = passed ? "Correct respresentation of flight manifest": "Incorrect respresentation of flight manifest";         

        ReportContent.addFeedback(new Feedback("Flight", "printLuggageManifest method", mark, response));
        assertTrue(passed);
    }

    @Test 
    public void testGetAllowedLuggage(){
        checkMethod("getAllowedLuggage", int.class.getName(), "int", "char", char.class);
        
        Method allowedLuggage = null;
        try{
            allowedLuggage = Flight.class.getDeclaredMethod("getAllowedLuggage", char.class);
            passed = true;
        }catch(NoSuchMethodException e){
            passed = false;
        }
        
        if(passed)
            passed = allowedLuggage.toGenericString().contains(" static ") &&
            (3==Flight.getAllowedLuggage('F')) &&
            (2==Flight.getAllowedLuggage('B')) &&
            (1==Flight.getAllowedLuggage('P')) &&
            (0==Flight.getAllowedLuggage('E'));
        
        if(!passed)
            passedTestsMark--;
        mark = passed ? 2: 0;
        response = passed ? "Correct assignment of luggage pieces": "Incorrect assignment of luggage pieces";
        ReportContent.addFeedback(new Feedback("Flight", "getAllowedLuggage method", mark, response));

        assertTrue(passed);
    }

    @Test 
    public void testToString(){
        passed = flight1.toString().strip().toLowerCase().replaceAll(" ","").contains("POS123 DESTINATION: JFK ORIGIN: POS 2023-01-23T10:00".replaceAll(" ","").toLowerCase());     
        
        if(!passed)
            passedTestsMark--;
        mark = passed ? 1: 0;
        response = passed ? "Correct format": "Incorrect format";
        ReportContent.addFeedback(new Feedback("Flight", "toString method", mark, response));
        assertTrue(passed);
    }
}
