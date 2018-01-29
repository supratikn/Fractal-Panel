package fractals;

import userInterface.UI;

/**
 * Super class that provides the base for generating fractals and 
 * calculating escape times for each point in the fractals. 
 * 
 * @author Supratik Neupane
 * @author Aiden Cox
 * @author Ryan Gangwish
 * @author Sean Mackay
 * 
 */
public abstract class Fractals {

	/**
	 * startX stores the starting value of the range of x-coordinates.
	 */
	/**
	 * endX store the final value of the range of x-coordinates.
	 */
	/**
	 * startY stores the starting value of the range of y-coordinates.
	 */
	/**
	 * endY stores the final value of the range of y-coordinates.
	 */
	/**
	 * rangeX stores the distance between two x-coordinates.
	 */
	/**
	 * rangeY stores the distance between two y-coordinates.
	 */
	/**
	 * dist stores the distance between a point and the origin at any given time in the program.
	 */
	/**
	 * xCalc is a variable used the update the value of the x-coordinate in the getEscapeTime method.
	 */
	/**
	 * yCalc is a variable used the update the value of the y-coordinate in the getEscapeTime method.
	 */
	public double startX, endX, startY, endY, rangeX, rangeY, xCalc, yCalc, dist;
	/**
	 * static double variable that is not changed by making new instances of this class or and subclass.
	 */
	protected static double escDist;
	/**
	 * passes is a counter that increases by one every time the loop is entered in the getEscapeTime method; 
	 */
	/**
	 * escapeTime is the final value of passes after it exits the loop.
	 */
	
	protected int passes, escapeTime;
	
    /**
     * The maximum number of iterations a point can pass through the loop.
     */
    /**
     * The number of threads generating the 2-d array;
     */
    protected static int maxEscTime, thread;
    /**
     * instance of the UI class that shares an association relationship with this class.
     */
    private  UI ui;
	/**
	 * Initializes fractals to a new 512 by 512 2-d array of type int.
	 */
	
	public Fractals(UI ui){
		this.ui=ui;
	   //  thread=4;
	}

	/**
	 * Calculates the distance between the point and the origin using the
	 * Pythagorean theorem.
	 * 
	 * @param x
	 *            The point's x-coordinate
	 * @param y
	 *            The point's y-coordinate
	 * @return the distance between the point and the origin
	 */
	public double distanceFromOrigin(double x, double y) {

		return Math.sqrt(x*x+y*y);

	}

	/**
	 * Assigns values to staring and ending coordinates in the sub classes
	 * Calculates the distance between any two equally spaced x-coordinates and y-coordinates using the formula
	 * for arithmetic sequence. common difference =(last-first)/(n-1)
	 */
	public void calculateRanges() {

		/* int bar=(ui==null)?0:ui.getBarHeight();;*/
          
		/*double zRX = ui.getImageWidth() / ui.getPanelWidth();
		double zRY = ui.getImageHeight() / ui.getPanelHeight();*/

		rangeX = ((endX - startX) / (2047)) ;
		rangeY = ((endY - startY) / 2047) ;

	}
    /**
     * assigns the default values to startX, endX, startY, endY
     */
    public abstract void resetRanges();
	/**
	 * Calculates the number of times a point goes through a loop before it's
	 * distance from the origin exceeds the escape distance or if it never
	 * exceeds the escape distance. 
	 * 
	 * The while loop checks if the the dist is<=2.0 or if the passes<255 at run time and then 
	 * checks again if there are any changes to these values.
	 * 
	 * @param x The point's x-coordinate
	 * @param y The point's y-coordinate
	 * @return The number of times the point enters a loop before it escapes
	 */
	public abstract int getEscapeTime(double x, double y);

	/** Calculates the escape time for each of the l*2048 points in the fractal
	 * where l is the length of the 2-d array, the inner array always has a length of 2048
	 * 
	 * @return a 2-d array that holds all the escape times for the l by 2048 combination of 
	 *         rows and columns
	 */
	public int[][] getFractals(int s, int e) {
		int l = e - s;
		int[][] fractals = new int[l][2048];
		// loop through the fractal's rows
		for (int i = 0; i < l; i++) {
			// loop through the fractal's columns
			for (int j = 0; j < 2048; j++) {
				fractals[i][j] = getEscapeTime(getRangeValueX(s), getRangeValueY(j));

			}
			s += 1;
		}
		return fractals;
	}
/**
 * @return call the getFractals method with (0,2048) as parameters.
 */
public int [][] getFractals(){
	return getFractals(0,2048);
}
	/**
	 * Translates the row to corresponding x-coordinate.
	 * If we treat it as an arithmetic sequence with common difference of rangeX,
	 * then the nth term will be startX + (n-1)*rangeX.
	 * But the index in our 2-d array starts from 0 so we disregard the (n-1) and just use n.
	 * 
	 * @param i The row of the 2-d array
	 * @return The translated x-coordinate of the corresponding row.
	 */
	public double getRangeValueX(double i) {
		//type cast i to double
		double a = (double) i;
		return startX + (a * rangeX);

	}
	

	/**
	 * Translates the column to the corresponding y-coordinate.
	 * 
	 * If we treat it as an arithmetic sequence with common difference of rangeY,
	 * then the nth term will be startY + (n-1)*rangeY.
	 * But the index in our 2-d array starts from 0 so we disregard the (n-1) and just use n.
	 * 
	 * @param j The column of the 2-d array
	 * @return The translated y-coordinate of the corresponding column.
	 */
	public double getRangeValueY(double j) {
		//type cast j to double
		double b = (double) j;
		return startY + (b * rangeY);
	}
	
	/**
	 * If d>0.0 then d is assigned to escDist. 
	 * Otherwise throws an illegal argument exception.
 	 * 
	 * @param d assigned to escDist if it's legal.
	 */
	public void setEscDist(double d){
		if(d>0.0)
		escDist=d;
		else{
			throw new IllegalArgumentException();
		}

	}
	/**
	 * if i>0 and i< 255 then i is assigned to maxEscTime
	 * Otherwise throws an illegal argument exception.
	 * 
	 * @param i assigned to maxEscTime if it's legal.
	 */
	public void setMaxEscapeTime(int i){
		if(i>0 && i<256){
			maxEscTime=i;

		}
		else{
			throw new IllegalArgumentException();
		}
	}
	/**
	 * assigns new values to the endPoints
	 * 
	 * @param startX assigned to the field startX
	 * @param startY assigned to the field startY
	 * @param endX assigned to the field endX
	 * @param endY assigned to the field endY
	 */
	public void setEndPoints(double startX, double startY, double endX, double endY){
		
		this.startX=startX;
		this.startY=startY;
		this.endX = endX;
		this.endY = endY;
	}
	/**
	 * sets the desired number of threads to the static variable thread
	 * @param t assigned to thread
	 */
	public void setThread(int t){
		thread=t;
	}
	/**
	 * returns thread
	 * @return the number of threads
	 */
	public int getThread(){
		return thread;
	}
}
