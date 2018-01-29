package fractals;

import userInterface.UI;

/**
* The BurningShip class is apart of the fractals package
 * BurningShip uses an equation to return the escape time 
 * for a specific coordinate
 * @author Ryan, Aiden, Supratik & Sean
 * @see Fractals.java file
 */


public class BurningShip extends Fractals {

	/**
	 *Calls the constructor in the super class and calculates the ranges.
	 * 
	 */

	public BurningShip(UI ui) {
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
			yCalc = (Math.abs(2 * tempX * yCalc) + y);

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
		startX = -1.8;
		endX = -1.7;
		startY = -0.08;
		endY = 0.025;
		super.calculateRanges();
	}
	
}
