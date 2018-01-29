package fractalsTests;


import java.util.Random;


/**
 * Main class that has method headers for all the fractal set tests.
 * @author Supratik Neupane
 * @author Sean Mackay
 * @author Ryan Gangwish
 * @author Aiden Cox
 *
 */
public abstract class FractalTests {
	/**
	 *  Random used in testing translation of x and y coordinates.
	 */
	protected Random r;
    /**
     * the instance of the fractals super class that is initialized before every test.
     */
    protected fractals.Fractals fractals;
	/**
	 * Initializes random 
	 */
	public FractalTests() {
		r = new Random();
	}
     /**
     * initialize the fractal and set the escapeDistance at the start to 2.0.
     */
    public abstract void init();
	/**
	 * Test if the point has an escapeTime of 255 i.e. the maximum number of passes into the loop
	 */
	public abstract void neverEscapeTest();

	/**
	 * Test if the method to create the 2-d array has 2048 rows and 2048 columns using 4 threads, 
	 * check if each of the WorkerResult instances have 2-d array of 512 columns and 2048 rows.
	 */
	public abstract void arraySizeTest();

	/**
	 * Test if the row is translated to the corresponding x-coordinate
	 */
	public abstract void xCoordinateTest();

	/**
	 * Test if the row is translated to the corresponding y-coordinate
	 */
	public abstract void yCoordinateTest();
	
    /**
     * Test if the given coordinate has escape time of 10 after the escape distance has been changed to 3.0. 
     */
    public abstract void tenAfterChangeToThree();
    /**
     * Test if the given coordinate has escape time of 135 after the max number of iterations is set to 135.
     */
    public abstract void setMaxEscapeTimeToOneThirtyFiveTest();
}
