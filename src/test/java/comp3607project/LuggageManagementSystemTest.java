package comp3607project;
import org.junit.*;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

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
}
