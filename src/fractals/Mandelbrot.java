package fractals;

import userInterface.UI;

/**
 *The Mandelbrot class is apart of the
 * fractals package Mandelbrot uses an equation to return the escape time
 * for a specific coordinate
 * 
 * @author superkid Ryan, Aiden, Supratik & Sean
 * @see Fractals.java file
 */
public class Mandelbrot extends Fractals {

	
	
	/**
	 * Calls the constructor in the super class and calculates the ranges.
	 */
	public Mandelbrot(UI ui) {
		super(ui);
		
		resetRanges();
	}

	
	/* (non-Javadoc)
	 * @see fractals.Fractals#getEscapeTime(double, double)
	 */
	@Override
	public int getEscapeTime(double x, double y) {
		passes = 0;
		xCalc = x;
		yCalc = y;
		dist = distanceFromOrigin(xCalc, yCalc);
    
		while (dist <= escDist && passes < maxEscTime) {
            // tempX stores the value of xCalc before it's updated and used to update yCalc.
			double tempX = new Double(xCalc);
			xCalc = xCalc * xCalc - yCalc * yCalc + x;
			yCalc = (2 * tempX * yCalc) + y;

			dist = distanceFromOrigin(xCalc, yCalc);
			passes++;

		}
		
		escapeTime = passes;
		return escapeTime;
	}
    
	/* (non-Javadoc)
	 * @see fractals.Fractals#resetRanges()
	 */
	@Override
	public void resetRanges(){
		startX = -2.15;
		startY = -1.3;
		endX = 0.6;
		endY = 1.3;
		super.calculateRanges();
	}
	
	
}
