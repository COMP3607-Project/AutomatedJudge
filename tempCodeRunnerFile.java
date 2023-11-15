import java.util.ArrayList;
import java.lang.reflect.*;

/**
 * Hello world!
 *
 */

    public static void main( String[] args )
    {
        ArrayList<String> slips;
        try {
            Field field = App.class.getDeclaredField("slips");
            String genericString = field.toGenericString();
            System.out.println("Generic String: " + genericString);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
