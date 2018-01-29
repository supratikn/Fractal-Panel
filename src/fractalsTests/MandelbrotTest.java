
package fractalsTests;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.junit.Before;
import org.junit.Test;

import edu.buffalo.fractal.ComputePool;
import edu.buffalo.fractal.FractalPanel;
import edu.buffalo.fractal.MultiThreading;
import edu.buffalo.fractal.WorkerResult;
import fractals.Mandelbrot;

/** @author Ryan,Aiden,Supratik & Sean
* 'MandelBrotTest' is a JUnit test that evaluates
* the MandelBrot file
* 'FractalTests' is the superclass for this JUnit test.
* @see fractals.FractalTests.java file
*/
public class MandelbrotTest extends FractalTests {
	
	/* (non-Javadoc)
	 * @see fractalsTests.FractalTests#neverEscapeTest()
	 */
	@Override
	@Test
	public void neverEscapeTest() {
		
		assertEquals(255, fractals.getEscapeTime(0.3207031250000001, -0.07109374999999386));

	}
     
	/**
	 * Test if the given point has the escape time of 1 
	 */
	@Test
	public void escapesAfterOneLoopTest() {
		
		assertEquals(1, fractals.getEscapeTime(0.5946289062500001, 1.2949218750000122));
	}
       
	/* (non-Javadoc)
	 * @see fractalsTests.FractalTests#arraySizeTest()
	 */
	@Override 
	@Test
	public void arraySizeTest() {

		ComputePool computePool = new ComputePool();
		computePool.clearPool();
		SwingWorker<WorkerResult, Void> instances[] = new MultiThreading[4];
		int first = 0;
		int r = 2048 / 4;
		int last = r;
		for (int i = 0; i < 4; i++) {
			instances[i] = new MultiThreading(fractals, first, last);
			
			first = last;
			last += r;
			
		}
		computePool.changePanel(new FractalPanel());
		computePool.generateFractal(2048, instances);
		 for(SwingWorker<WorkerResult, Void> instance:instances){
			 try {
				assertEquals(512,instance.get().getNumberRows());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				try {
					assertEquals(2048,instance.get().getRow(0).length);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		
	}

	/* (non-Javadoc)
	 * @see fractalsTests.FractalTests#xCoordinateTest()
	 */
	@Override
	@Test
	public void xCoordinateTest() {
		
		int z = r.nextInt(2048);
		double x = (z * fractals.rangeX) + fractals.startX;
		assertEquals(x, fractals.getRangeValueX(z), 0.01);

	}

	/* (non-Javadoc)
	 * @see fractalsTests.FractalTests#yCoordinateTest()
	 */
	@Override
	@Test
	public void yCoordinateTest() {
	
		int z = r.nextInt(2048);
		double y = (z * fractals.rangeY) + fractals.startY;
		assertEquals(y, fractals.getRangeValueY(z), 0.01);

	}

	/* (non-Javadoc)
	 * @see fractalsTests.FractalTests#init()
	 */
	@Override
	@Before
	public void init() {
		fractals = new Mandelbrot(null);
		fractals.setEscDist(2.0);
		fractals.setMaxEscapeTime(255);
	}

	/* (non-Javadoc)
	 * @see fractalsTests.FractalTests#tenAfterChangeToThree()
	 */
	@Override
	@Test
	public void tenAfterChangeToThree() {
		fractals.setEscDist(3.0);
       assertEquals(10, fractals.getEscapeTime(0.46007827788650374, -0.3383561643835661));		
	}

	/* (non-Javadoc)
	 * @see fractalsTests.FractalTests#setMaxEscapeTimeToOneThirtyFiveTest()
	 */
	@Override
	@Test
	public void setMaxEscapeTimeToOneThirtyFiveTest() {
		fractals.setMaxEscapeTime(135);
		assertEquals(135,fractals.getEscapeTime(0.3207031250000001, -0.07109374999999386));
		
	}
	

}
