package fractals;

import userInterface.UI;

/** 
 * The Multibrot class is apart of the fractals package
 * Multibrot uses an equation to return the escape time 
 * for a specific coordinate
 * @author Ryan, Aiden, Supratik & Sean
 * @see Fractals.java file
 */

public class Multibrot extends Fractals {
	/**
	 *Calls the constructor in the super class and calculates the ranges.
	 */
	public Multibrot(UI ui) {
		super(ui);
		
		resetRanges();
	}

	/* (non-Javadoc)
	 * @see fractals.Fractals#getEscapeTime(double, double)
	 */
	@Override
	public int getEscapeTime(double x, double y) {
		passes = 0;
		double xCalc = x;
		double yCalc = y;
		double dist = distanceFromOrigin(xCalc, yCalc);

		while (dist <= escDist && passes < maxEscTime) {
			// tempX stores the value of xCalc before it's updated and used to update yCalc.
			double tempX = new Double(xCalc);
			xCalc = xCalc*xCalc*xCalc -(3*xCalc*yCalc*yCalc)+x;
			yCalc = (3 * tempX *tempX* yCalc) -yCalc*yCalc*yCalc+ y;
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
		startX = -1.0;
		endX = 1.0;
		startY = -1.3;
		endY = 1.3;
		super.calculateRanges();
	}
}
