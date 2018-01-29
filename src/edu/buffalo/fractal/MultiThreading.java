package edu.buffalo.fractal;

import javax.swing.SwingWorker;

import fractals.Fractals;

/**
 * Subclass of the SwingWorker that calculates the 2-d arrays in the background.
 * @author Supratik Neupane
 * @author Sean Mackay
 * @author Ryan Gangwish
 * @author Aiden Cox
 *
 */
public class MultiThreading extends SwingWorker<WorkerResult,Void>{
private Fractals fractal;
private int start, end;
public MultiThreading(Fractals f, int s, int e){
	fractal =f;
	start =s;
	end=e;
}
	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	protected WorkerResult doInBackground() throws Exception {
		return new WorkerResult(start, fractal.getFractals(start,end));
		
	}
  
}
