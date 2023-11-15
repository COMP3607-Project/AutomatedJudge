package comp3607project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class PassengerTest {

    private String response;
    private int total;
    boolean passed;
    String typeName;
    String modifier;
    private Field field;
    // ArrayList<String> expectedNames;

    @Before
    public void initPassengerTest() {
        response = "";
        total = 0;
        passed = true;
        field = null;
        /*
         * expectedNames = new ArrayList<>();
         * expectedNames.add("passportNumber");
         * expectedNames.add("flightNo");
         * expectedNames.add("firstName");
         * expectedNames.add("lastName");
         * expectedNames.add("numLuggage");
         * expectedNames.add("cabinClass");
         */
    }

    @Test
    public void testPassportNumberField(){
   
        field = getField("passportNumber");

        typeName = String.class.getName();
        modifier = "private";

        if(field != null){
            String isExpectedField = field.toString();
            System.out.println(isExpectedField);

            passed = isExpectedField.contains(modifier + " " + typeName);

            if(passed){
                passed = isExpectedField.contains("Passenger.passportNumber");
                total += 1;
                response = "Correct field";
            }

            if(!passed)
                response += "Incorrect. Expected field: private String passportNumber";

        }

        assertTrue(passed);

        /*
        field = checkNamingConvention("passportNumber");

        // tests information hiding
        if (checkFieldModifier("private", "manifest")) {
            response += "Attribute is private.";
        } else {
            response += "Attribute is not private.";
            passed = false;
        }

        // tests data type
        try {
            if (assertTrue(flight1.getManifest() instanceof LuggageManifest)) {
                response = response + " Is of type String ";
            } else {
                response = response + " Is not of type String.";
                passed = false;
            }

            if (assertNull(flight1.getManifest())) {
                response = response + " Has only been declared.";
            } else {
                response = response + "Has been initialized.";
                passed = false;
            }

        } catch (Exception e) {
            response = response + " No accessor method available.";
        }

        if (passed)
            mark = 1;
        Feedback f = new Feedback("Flight", "manifest Attribute", mark, response);
        assertNotNull(field);*/
    /*}

    private boolean checkFieldModifier(String mod, String fieldName){
        Field testField = Passenger.class.getDeclaredField(fieldName);
        String testMod = Modifier.toString(testField.getModifiers());
        
            return mod.equals(testMod);

        
    }

    private Field checkNamingConvention(String attributeName) {

        Field testField = null;

        try {
            testField = Passenger.class.getDeclaredField(attributeName);
            response = response + "Correct naming convention.";
            return testField;
        } catch (Exception e) {
            response += "Incorrect. Expected naming convention: " + attributeName + "\n";
        }

        return testField;*/
    }

    private Field getField(String fieldName){
        Field testField = null;
       
        try {
            testField = Passenger.class.getDeclaredField(fieldName);
            return testField;
        }
            catch (NoSuchFieldException e) {
            return testField;
        }
    }
}
