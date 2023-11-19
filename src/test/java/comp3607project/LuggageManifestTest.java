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
        luggageManifest1.getSlips().clear();
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
        passed = luggageManifest1.getSlips() instanceof ArrayList;

        mark = passed ? 1 : 0;
        response = passed ? "Correct initialization": "Incorrect initialization";
        
        assertTrue(luggageManifest1.getSlips() instanceof ArrayList);
        results.add(new Feedback("LuggageManifest", "Constructor", mark, response));
    }

    @Test 
    public void testAddLuggage(){
        Passenger p = new Passenger("TA890789", "Joe", "Bean", "POS123");
        Flight f = new Flight("POS123", "JFK", "POS", LocalDateTime.of(2023, 1, 23, 10, 00, 00));
        luggageManifest1.addLuggage(p, f);
        String details = p.toString();
        double excessCost = luggageManifest1.getExcessLuggageCost(p.getNumLuggage(), Flight.getAllowedLuggage(p.getCabinClass()));

        if(p.getNumLuggage() == 0)
            details += "\nNo Luggage to add.";
        else{
            String cost = luggageManifest1.getExcessLuggageCostByPassenger(p.getPassportNumber());
            if(cost.equals("No Cost"))
                details += "\nPieces Added: (" + p.getNumLuggage() + "). Excess Cost: $0.00\n";
            else
                details += "\nPieces Added: (" + p.getNumLuggage() + "). Excess Cost: $" + cost + "\n";
        }
            
        for (Object obj : luggageManifest1.getSlips()){
            LuggageSlip l = (LuggageSlip) obj;
            System.out.println("yh");
            if(l.hasOwner(p.getPassportNumber())){
                System.out.println("no");
            n       if(excessCost == 0.0 && l.getLabel().equals(""))
                    passed = true;
                else if (l.getLabel().equals(String.format("%.2f", excessCost)));
                    passed = true;
            }
        }

        System.out.println(passed);
        if(passed){
            passed = details.contains(luggageManifest1.addLuggage(p, f));
            System.out.println(details + "\n" + luggageManifest1.addLuggage(p, f));
            System.out.println(passed);
            if(passed){
                passed = luggageManifest1.getSlips().isEmpty() ? false: true;
                System.out.println(passed);
                if(passed)
                    passed = (p.getNumLuggage() == luggageManifest1.getSlips().size());
                    
            }
        }
        mark = passed ? 6 : 0;
        response = passed ? "Correct functionality displayed for adding luggage.": "Incorrect functionality displayed for adding luggage.";
        
        System.out.println(mark + " " + response);
        System.out.println(p.getCabinClass() + " " + p.getNumLuggage());
        System.out.println(details);
        System.out.println(luggageManifest1.addLuggage(p, f));

        for (Object obj : luggageManifest1.getSlips()){
            LuggageSlip l = (LuggageSlip) obj;
            if(l.hasOwner(p.getPassportNumber())){
                if(excessCost == 0.0 && l.getLabel().equals(""))
                    assertEquals(l.getLabel(),"");
                else
                   assertEquals(l.getLabel(), String.format("%.2f", excessCost));
            }
        }

        assertTrue(details.contains(luggageManifest1.addLuggage(p, f)));
        assertFalse(luggageManifest1.getSlips().isEmpty());
        assertEquals(p.getNumLuggage(), luggageManifest1.getSlips().size());
        
        results.add(new Feedback("LuggageManifest", "addLuggage Method", mark, response));
        
    }

    @Test
    public void testGetExcessLuggageCost(){
        passed = (70.00 == luggageManifest1.getExcessLuggageCost(4,2));
        mark = passed ? 1 : 0;
        response = passed ? "Correct calculation of excess cost displayed.": "Incorrect calculation of excess cost displayed.";

        assertEquals(70.00, luggageManifest1.getExcessLuggageCost(4,2),0.0);
        assertEquals(0.00, luggageManifest1.getExcessLuggageCost(2,2),0.0);
        assertEquals(35.00, luggageManifest1.getExcessLuggageCost(3,2),0.0);
        
        results.add(new Feedback("LuggageManifest", "getExcessLuggageCost Method", mark, response));
    }

    @Test 
    public void testGetExcessLuggageCostByPassenger(){
        Passenger p = new Passenger("TA890789", "Joe", "Bean", "POS123"); 
        Flight f = new Flight("POS123", "JFK", "POS", LocalDateTime.of(2023, 1, 23, 10, 00, 00));
        luggageManifest1.addLuggage(p, f);
        String costDetails = "";
        
        for (Object obj: luggageManifest1.getSlips()){
            LuggageSlip l = (LuggageSlip) obj;
            if(l.hasOwner(p.getPassportNumber())){
                costDetails = l.getLabel();
            }
        }
        
        if(costDetails.equals("0.00") || costDetails.equals(""))
            costDetails =  "No Cost";

        passed = costDetails.equals(luggageManifest1.getExcessLuggageCostByPassenger(p.getPassportNumber()));
        mark = passed ? 5: 0;
        response = passed ? "Correct cost of excess luggage returned for passenger.": "Incorrect cost of excess luggage returned for passenger.";

        assertEquals(costDetails, luggageManifest1.getExcessLuggageCostByPassenger(p.getPassportNumber()));
        results.add(new Feedback("LuggageManifest", "getExcessLuggageCostByPassenger Method", mark, response)); 
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
                details += "\n" + l.toString();
            }
        } else 
            details += "\nNo luggage boarded.";
            
        passed = luggageManifest1.toString().strip().equals(details.strip());     
        mark = passed ? 1 : 0;
        response = passed ? "Correct format": "Incorrect format";
        assertEquals(details, luggageManifest1.toString().strip());

        results.add(new Feedback("LuggageManifest", "toString method", mark, response));
        
    }
}
