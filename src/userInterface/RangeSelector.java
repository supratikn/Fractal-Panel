package userInterface;

import java.awt.Point;

/**
 * Class that stores the points from the screen while the mouse is dragged
 * 
 * @author Supratik Neupane
 * @author Aiden Cox
 * @author Ryan Gangwish
 * @author Sean Mackay
 *
 */
public class RangeSelector {
	/**
	 * The starting point and ending point of the fractal after zooming, and the moving point used for 
	 * visual representation of the selected points
	 */
	private static Point.Double first, second, moving;
	

	/**
	 * initialize the points to null at start and counter to 0
	 */
	public RangeSelector() {
		first = null;
		second = null;
		moving=null;
		
	}

	/**
	 * assigns value to first
	 * 
	 * @param p assigned to first
	 */
	public void setFirstPoint(Point.Double p) {
		first = p;
	}

	/**
	 * assigns value to second
	 * 
	 * @param p assigned to second
	 */
	public void setSecondPoint(Point.Double p) {
		second = p;
	}

	/**
	 * @return first
	 */
	public Point.Double getFirstPoint() {
		return first;
	}

	/**
	 * @return second
	 */
	public Point.Double getSecondPoint() {
		return second;
	}


	
	/**
	 * assigns value to the moving point
	 * 
	 * @param p assigned to moving
	 */
	public void setMovingPoint(Point.Double p){
		moving=p;
	}
	/**
	 * @return moving
	 */
	public Point.Double getMovingPoint(){
		return moving; 
	}
}
