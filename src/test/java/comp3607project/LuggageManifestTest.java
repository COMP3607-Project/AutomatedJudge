package comp3607project;

import org.junit.*;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class LuggageManifestTest extends TestTemplate{
    private LuggageManifest luggageManifest1;

    @Before
    public void init(){
        mark = 0;
        response = "";
        declaredOnly = false;
        passed = false;
        field = null;
        testClass = LuggageManifest.class;
        luggageManifest1 = new LuggageManifest();
    }

    @Test
    public void testSlips(){
        runFieldTest(testClass, "slips", "private", ArrayList.class.getName(), "ArrayList<LuggageSlip>");
        if(!passed)
            passedTestsMark--;        
        mark = passed ? 2 : 0;
        results.add(new Feedback("LuggageManifest", "slips Attribute", mark, response));
        assertTrue(passed);
    }

    @Test 
    public void testCreateLuggageManifest() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
        field = LuggageManifest.class.getDeclaredField("slips");
        field.setAccessible(true);
        ArrayList<LuggageSlip> slipsValue = (ArrayList<LuggageSlip>) field.get(luggageManifest1);
        
        passed = slipsValue instanceof ArrayList;

        if(!passed)
            passedTestsMark--;
        mark = passed ? 1 : 0;
        response = passed ? "Correct initialization": "Incorrect initialization";
        results.add(new Feedback("LuggageManifest", "Constructor", mark, response));
        assertTrue(slipsValue instanceof ArrayList);
    }

    @Test 
    public void testAddLuggage() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
        checkMethod("addLuggage", "String", "String", "Passenger, Flight", (Class<?>[])(Passenger, Flight));

        Passenger p = new Passenger("TA890789", "Joe", "Bean", "POS123");
        Flight f = new Flight("POS123", "JFK", "POS", LocalDateTime.of(2023, 1, 23, 10, 00, 00));
        String details = "";
        String costDetails = "";
        double excessCost = 0.00;

        Field numLuggage = Passenger.class.getDeclaredField("numLuggage");
        numLuggage.setAccessible(true);
        int numLuggageValue = (int) numLuggage.get(p);

        Field cabin = Passenger.class.getDeclaredField("cabinClass");
        cabin.setAccessible(true);
        char cabinValue = (char) cabin.get(p);

        Field passport = Passenger.class.getDeclaredField("passportNumber");
        passport.setAccessible(true);
        String passportValue = (String) passport.get(p);

        field = LuggageManifest.class.getDeclaredField("slips");
        field.setAccessible(true);
        ArrayList<LuggageSlip> slipsValue = (ArrayList<LuggageSlip>) field.get(luggageManifest1);
        
        if(numLuggageValue != 0){            
            if(numLuggageValue > Flight.getAllowedLuggage(cabinValue)){
                excessCost = luggageManifest1.getExcessLuggageCost(numLuggageValue, Flight.getAllowedLuggage(cabinValue));
                for(int i = 0; i < numLuggageValue; i++){
                    slipsValue.add(new LuggageSlip(p, f, String.format("%.2f", excessCost)));
                } 
            } else {
                for(int i = 0; i < numLuggageValue; i++){
                    slipsValue.add(new LuggageSlip(p, f));
                } 
            }

            costDetails = luggageManifest1.getExcessLuggageCostByPassenger(passportValue);
            if(costDetails.equals("No Cost"))
                costDetails = "0.00";
            
            details = p.toString() + "\n" 
                + "Pieces Added: (" + numLuggageValue
                + "). Excess Cost: $" + costDetails + "\n";
        } else 
            details = p.toString() + " \nNo Luggage to add \n";
        

        passed = luggageManifest1.addLuggage(p, f).strip().toLowerCase().replaceAll(" ","").contains(details.replaceAll(" ","").toLowerCase().strip());
        if(passed){
            if(numLuggageValue > 0)
                passed = numLuggageValue == slipsValue.size()/2;
            else
                passed = numLuggageValue == slipsValue.size();
        }

        if(!passed)
            passedTestsMark--;
        mark = passed ? 6 : 0;
        response = passed ? "Correct functionality displayed for adding luggage.": "Incorrect functionality displayed for adding luggage.";
        results.add(new Feedback("LuggageManifest", "addLuggage Method", mark, response));

        for (Object obj : slipsValue){
            LuggageSlip l = (LuggageSlip) obj;

            Field label = LuggageSlip.class.getDeclaredField("label");
            label.setAccessible(true);
            String labelValue = (String) label.get(l);

            if(l.hasOwner(passportValue)){
                if(excessCost == 0.0 && labelValue.equals(""))
                    assertEquals("",labelValue);
                else
                   assertEquals(String.format("%.2f", excessCost), labelValue);
            }
        }

        assertTrue(luggageManifest1.addLuggage(p, f).strip().toLowerCase().replaceAll(" ","").contains(details.replaceAll(" ","").toLowerCase().strip()));
        if(numLuggageValue > 0)
            assertEquals(numLuggageValue, slipsValue.size()/3);
        else
            assertEquals(numLuggageValue, slipsValue.size());
    }

    @Test
    public void testGetExcessLuggageCost(){
        passed = (70.00 == luggageManifest1.getExcessLuggageCost(4,2)) &&
            (0.00 == luggageManifest1.getExcessLuggageCost(2,2)) &&
            (35.00 == luggageManifest1.getExcessLuggageCost(3,2));
        
        if(!passed)
            passedTestsMark--;

        mark = passed ? 1 : 0;
        response = passed ? "Correct calculation of excess cost displayed.": "Incorrect calculation of excess cost displayed.";
        results.add(new Feedback("LuggageManifest", "getExcessLuggageCost Method", mark, response));
    
        assertEquals(70.00, luggageManifest1.getExcessLuggageCost(4,2),0.0);
        assertEquals(0.00, luggageManifest1.getExcessLuggageCost(2,2),0.0);
        assertEquals(35.00, luggageManifest1.getExcessLuggageCost(3,2),0.0);
    }

    @Test 
    public void testGetExcessLuggageCostByPassenger() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
        Passenger p = new Passenger("TA890789", "Joe", "Bean", "POS123"); 
        Flight f = new Flight("POS123", "JFK", "POS", LocalDateTime.of(2023, 1, 23, 10, 00, 00));
        luggageManifest1.addLuggage(p, f);
        String costDetails = "";

        field = LuggageManifest.class.getDeclaredField("slips");
        field.setAccessible(true);
        ArrayList<LuggageSlip> slipsValue = (ArrayList<LuggageSlip>) field.get(luggageManifest1);

        Field passportNo = Passenger.class.getDeclaredField("passportNumber");
        passportNo.setAccessible(true);
        String passportNoValue = (String) passportNo.get(p);

        for (Object obj: slipsValue){
            LuggageSlip l = (LuggageSlip) obj;
            if(l.hasOwner(passportNoValue)){

                Field label = LuggageSlip.class.getDeclaredField("label");
                label.setAccessible(true);
                String labelValue = (String) label.get(l);

                costDetails = labelValue;
            }
        }
        
        if(costDetails.equals("0.00") || costDetails.equals(""))
            costDetails =  "No Cost";

        passed = luggageManifest1.getExcessLuggageCostByPassenger(passportNoValue).strip().replaceAll(" ","").toLowerCase().contains(costDetails.replaceAll(" ","").toLowerCase().strip());
        if(!passed)
            passedTestsMark--;
        
        mark = passed ? 5: 0;
        response = passed ? "Correct cost of excess luggage returned for passenger.": "Incorrect cost of excess luggage returned for passenger.";
        results.add(new Feedback("LuggageManifest", "getExcessLuggageCostByPassenger Method", mark, response)); 
        
        System.out.println(luggageManifest1.getExcessLuggageCostByPassenger(passportNoValue));
        System.out.println(costDetails);
        assertTrue(luggageManifest1.getExcessLuggageCostByPassenger(passportNoValue).strip().replaceAll(" ","").toLowerCase().contains(costDetails.replaceAll(" ","").toLowerCase().strip()));
    }

    @Test 
    public void testToString() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
        Passenger p = new Passenger("TA890789", "Joe", "Bean", "POS123");
        Flight f = new Flight("POS123", "JFK", "POS", LocalDateTime.of(2023, 1, 23, 10, 00, 00));
        luggageManifest1.addLuggage(p, f);
        String details = "LUGGAGE MANIFEST:";

        field = LuggageManifest.class.getDeclaredField("slips");
        field.setAccessible(true);
        ArrayList<LuggageSlip> slipsValue = (ArrayList<LuggageSlip>) field.get(luggageManifest1);
 
        if(!slipsValue.isEmpty()){
            for(Object obj: slipsValue){
                LuggageSlip l = (LuggageSlip) obj;
                details += "\n" + l.toString();
            }

            passed = luggageManifest1.toString().strip().replaceAll(" ","").toLowerCase().contains(details.replaceAll(" ","").toLowerCase().strip());     
            if(!passed)
                passedTestsMark--;
            
            mark = passed ? 1 : 0;
            response = passed ? "Correct format": "Incorrect format";
            results.add(new Feedback("LuggageManifest", "toString method", mark, response));
            System.out.println(details);
            System.out.println(luggageManifest1.toString());
            assertTrue(luggageManifest1.toString().strip().replaceAll(" ","").toLowerCase().contains(details.replaceAll(" ","").toLowerCase().strip()));
        }else{
            passed = true;
            mark = passed ? 1 : 0;
            response = passed ? "Correct format": "Incorrect format";
            results.add(new Feedback("LuggageManifest", "toString method", mark, response));
        }
    }
}
