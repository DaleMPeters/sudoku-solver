import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * A JUnit test suite that runs all tests in the JUnit test cases.
 * 
 * @author Dale Peters
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ CellTest.class, GridTest.class })
public class AllTests {

}
