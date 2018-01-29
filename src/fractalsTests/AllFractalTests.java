package fractalsTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * A test suite that runs all the tests at once.
 * 
 * @author Supratik Neupane
 * @author Aiden Cox
 * @author Ryan Gangwish
 * @author Sean Mackay
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ BurningShipTest.class, JuiaTest.class, MandelbrotTest.class, MultibrotTest.class })
public class AllFractalTests {

}
