package comp3607project;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  PassengerTest.class,
  LuggageSlipTest.class,
  LuggageManifestTest.class,
  FlightTest.class
})

public class ClassTestSuite {
  // the class remains empty,
  // used only as a holder for the above annotations
}