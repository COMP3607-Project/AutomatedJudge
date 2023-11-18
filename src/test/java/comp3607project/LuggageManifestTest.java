package comp3607project;

import org.junit.*;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class LuggageManifestTest extends TestTemplate{
    private LuggageManifest luggageManifest1;
    private Field[] lmFields = LuggageManifest.class.getDeclaredFields();

    public LuggageManifestTest(){
        luggageManifest1 = new LuggageManifest();
    }

    @Before
    public void init(){
        mark = 0;
        response = "";
        declaredOnly = false;
        passed = false;
        field = null;
        testClass = LuggageManifest.class;
    }

    @Test
    public void testSlips(){

        runFieldTest(testClass, "slips", "private", ArrayList.class.getName(), "ArrayList<LuggageSlip>");

        assertTrue(passed);        

        mark = passed ? 2 : 0;
        results.add(new Feedback("LuggageManifest", "slips Attribute", mark, response));
    }

    @Test 
    public void testCreateLuggageManifest(){
        try{
            assertTrue(luggageManifest1.getSlips() instanceof ArrayList);
            response += "Correct initialization";
            mark = 1;
        }catch (AssertionError e){
            response += "Incorrect initialization";
            mark = 0;
        }finally{
            results.add(new Feedback("LuggageManifest", "Constructor", mark, response));
        }
    }

    @Test 
    public void testAddLuggage(){
        try{
            Passenger p = new Passenger("TA890789", "Joe", "Bean", "POS123");
            Flight f = new Flight("POS123", "JFK", "POS", LocalDateTime.of(2023, 1, 23, 10, 00, 00));
            String details = p.toString();

            if(p.getNumLuggage() == 0)
                details += "\n No Luggage to add";
            else{
                String cost = luggageManifest1.getExcessLuggageCostByPassenger(p.getPassportNumber());
                if(cost.equals("No Cost"))
                    details += "\nPieces Added: (" + p.getNumLuggage() + ") Excess Cost: $0.00";
                else
                    details += "\nPieces Added: (" + p.getNumLuggage() + ") Excess Cost: $" + cost;
            }

            assertEquals(details ,luggageManifest1.addLuggage(p, f));
            
            for (Object obj : luggageManifest1.getSlips()){
                LuggageSlip l = (LuggageSlip) obj;
                if(l.hasOwner(p.getPassportNumber())){
                    assertEquals(l.getLabel(), luggageManifest1.getExcessLuggageCost(p.getNumLuggage(), Flight.getAllowedLuggage(p.getCabinClass())));
                }
            }

            assertFalse(luggageManifest1.getSlips().isEmpty());
            assertEquals(p.getNumLuggage(), luggageManifest1.getSlips().size());
            mark = 6;
            response += "Correct functionality displayed";
            
        }catch (AssertionError e){
            mark = 0;
            response += "Incorrect functionality displayed";
            
        }finally{
            results.add(new Feedback("LuggageManifest", "addLuggage Method", mark, response));
        }
    }

    @Test
    public void testGetExcessLuggageCost(){
        try{
            assertEquals(70.00, luggageManifest1.getExcessLuggageCost(4,2),0.0);
            assertEquals(0.00, luggageManifest1.getExcessLuggageCost(2,2),0.0);
            assertEquals(35.00, luggageManifest1.getExcessLuggageCost(3,2),0.0);
            mark = 3;
            response += "Correct calculation of excess luggage cost.";

        }catch (AssertionError e){
            mark = 0;
            response += "Incorrect calculation of excess luggage cost.";

        }finally{
            results.add(new Feedback("LuggageManifest", "getExcessLuggageCost Method", mark, response));
        }
    }

    @Test 
    public void testGetExcessLuggageCostByPassenger(){
        try{
            Passenger p = new Passenger("TA890789", "Joe", "Bean", "POS123"); 
            String costDetails = "";
        
            for (Object obj: luggageManifest1.getSlips()){
                LuggageSlip l = (LuggageSlip) obj;
                if(l.hasOwner(p.getPassportNumber())){
                    costDetails = l.getLabel();
                }
            }
        
            if(costDetails.equals("0.00") || costDetails.equals(""))
                costDetails =  "No Cost";

            assertEquals(costDetails, luggageManifest1.getExcessLuggageCostByPassenger(p.getPassportNumber()));
            mark = 5;
            response += "Correct cost of excess luggage returned for passenger";
        }catch (AssertionError e){
            mark = 0;
            response += "Incorrect cost of excess luggage returned for passenger";

        }finally{
            results.add(new Feedback("LuggageManifest", "getExcessLuggageCostByPassenger Method", mark, response));
        }
    }


    @Test 
    public void testToString(){
        Passenger p = new Passenger("TA890789", "Joe", "Bean", "POS123");
        Flight f = new Flight("POS123", "JFK", "POS", LocalDateTime.of(2023, 1, 23, 10, 00, 00));
        luggageManifest1.addLuggage(p, f);
        String details = "LUGGAGE MANIFEST:";
 
        if(!luggageManifest1.getSlips().isEmpty()){
            for(Object obj: luggageManifest1.getSlips()){
                LuggageSlip l = (LuggageSlip) obj;
                details += "\n" + l.toString() + "hello";
            }
        } else 
            details += "\nNo luggage boarded///.";

        if(passed){
            passed = isExpectedField.contains(testClass.getName() + "." + fieldName);
            response += "Correct field.\n";
        }
        else
            response += "Incorrect. Expected field: " + expectedField + ".\n";
        
        assertEquals(luggageManifest1.toString(), details);
        response += "Correct format";
        mark = 2;
        
            System.out.println(details);
            System.out.println(luggageManifest1.toString());
            results.add(new Feedback("LuggageManifest", "toString method", mark, response));
        
    }
}
