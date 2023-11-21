package comp3607project;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LuggageManagementSystemTest extends TestTemplate {
    
    @Before
    public void init(){
        mark = 0;
        response = "";
        testClass = LuggageManagementSystem.class;
    }

    @Test
    public void testSystemRun(){
        try{
            LuggageManagementSystem.main(null);
            passed = true;
        }catch(Exception e){
            e.printStackTrace();
            passed = false;
        }

        if(!passed)
            passedTestsMark--;

        mark = passed? 10: 0;
        response = passed? "LuggageManagementSystem class runs successfully." : "LuggageManagementSystem class does not run successfully.";
        ReportContent.addFeedback(new Feedback("LuggageManagementSystem", "testSystemRun", mark, response));
        assertTrue(passed);
    }

    public void testToString() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{};

}
