package comp3607project;
import org.junit.*;
import static org.junit.Assert.*;

public class LuggageManagementSystemTest extends TestTemplate {
    
    @Before
    public void init(){
        mark = 0;
        response = "";
        declaredOnly = false;
        passed = false;
        field = null;
        testClass = LuggageManagementSystem.class;
    }

    checkMethod("main", void)
}
