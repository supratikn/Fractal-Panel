package userInterface;

import fractals.*;

/**
 * Class that switches between fractals when a new fractals button is clicked.
 * 
 *  @author Supratik Neupane
 * @author Aiden Cox
 * @author Ryan Gangwish
 * @author Sean Mackay
 *
 */
public class FractalSelector {

	/**
	 * Instance of the fractals superclass
	 */
	private Fractals fractals;
	/**
	 * final int variables used check which fractal class was selected by the
	 * user and then initialize the respective fractal class.
	 */
	public static final int MANDELBROT = 1, JULIA = 2, BURNINGSHIP = 3, MULTIBROT = 4;
    
	/**
	 * initialize fractals to the chosen subclass
	 * 
	 * @param num
	 *            used to select the final int variables.
	 *   @param send in the instance of the UI class.
	 */
	public FractalSelector(int num, UI ui) {
		if (num == MANDELBROT) {
			fractals = new Mandelbrot(ui);
		} else if (num == JULIA) {
			fractals = new Julia(ui);
		} else if (num == BURNINGSHIP) {
			fractals = new BurningShip(ui);
		} else if (num == MULTIBROT) {
			fractals = new Multibrot(ui);
		} else {
			throw new IllegalArgumentException();
		}

	}

	/**
	 * return the fractal
	 * @return the initialized fractal
	 */
	public Fractals getFractal() {
		return fractals;
	}

}
