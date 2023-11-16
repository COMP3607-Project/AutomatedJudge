package comp3607project;

import org.junit.*;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.ArrayList;

public class LuggageManifestTest extends TestTemplate{
    private LuggageManifest luggageManifest1;
    private Field[] lmFields = LuggageManifest.class.getDeclaredFields();

    public LuggageManifestTest(){
        luggageManifest1 = new LuggageManifest();
    }

    @Before
    public void init(){
        response = "";
        mark = 0;
        grade = true; //determines if student get grade
        found = false; //determines if field was found aka adheres to naming convention
    }

    @Test
    public void testSlips(){
        checkAttribute(" slips ", "private", " ArrayList<LuggageSlip> ");

        if(found){
            response += "Correct naming convention";
            if(assertNotNull(luggageManifest1))
                
        }else{
            response += "Incorrect naming convention -> Expected: slips";
            grade = false;
        }

        mark = grade ? 2 : 0;
        results.add(new Feedback("LuggageManifest", "slips Attribute", mark, response));
    }
}
