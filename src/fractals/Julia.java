package fractals;

import userInterface.UI;

/**
 * The Julia class is apart of the fractals package
 * Julia uses an equation to return the escape time 
 * for a specific coordinate
 * @author Ryan, Aiden, Supratik & Sean
 * @see Fractals.java file
 */


public class Julia extends Fractals {

	/**
	 *Calls the constructor in the super class and calculates the ranges.
	 */


	public Julia(UI ui) {
		super(ui);
		
		resetRanges();
	}

	/* (non-Javadoc)
	 * @see fractals.Fractals#getEscapeTime(double, double)
	 */
	@Override
	public int getEscapeTime(double x, double y) {
		passes = 0;
		double k = -0.72689;
		double l = 0.188887;
		xCalc = x;
		yCalc = y;
		dist = distanceFromOrigin(xCalc, yCalc);

		while (dist <= escDist && passes < maxEscTime) {
			// tempX stores the value of xCalc before it's updated and used to update yCalc.
			double tempX = new Double(xCalc);
			xCalc = xCalc * xCalc - yCalc * yCalc + k;
			yCalc = 2 * tempX * yCalc + l;
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
		startX = -1.7;
		endX = 1.7;
		startY = -1.0;
		endY = 1.0;
		super.calculateRanges();
	}
      
}
