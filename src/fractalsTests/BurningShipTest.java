package fractalsTests;

	/**
	 * 'BurningShipTest' is a JUnit test that evaluates
	 * the BurningShip file
	 * 'FractalTests' is the superclass for this JUnit.
	 * @author Ryan, Aiden, Supratik & Sean
	 * @see fractals.FractalTests.java file
	 */


import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.junit.Before;
import org.junit.Test;

import edu.buffalo.fractal.ComputePool;
import edu.buffalo.fractal.FractalPanel;
import edu.buffalo.fractal.MultiThreading;
import edu.buffalo.fractal.WorkerResult;
import fractals.BurningShip;


public class BurningShipTest extends FractalTests {
	

	/* (non-Javadoc)
	 * @see fractalsTests.FractalTests#neverEscapeTest()
	 */
	@Override
	@Test
	public void neverEscapeTest() {
		
		assertEquals(255, fractals.getEscapeTime(-1.7443359374999874, -0.017451171875000338));

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
				assertEquals(2048,instance.get().getRow(0).length);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

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
		double x = (z * fractals.rangeX) +fractals.startX;
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
        assertEquals(fractals.endY,fractals.getRangeValueY(2047),0.001);
	}

	/**makes sure no fractals escape time is equal
	 * to 0 or 1
	 */
	@Test
	public void noOneOrZeroEscapeTimeTest() {
		
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
		 for(int i=0;i<instances.length;i++){
			
			
			try {
				for(int k=0;k<instances[i].get().getNumberRows();k++){
				int [] array = instances[i].get().getRow(k);
			
				for (int j : array) {
					assertFalse(j==0);
					assertFalse(j==1);}
			}
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
				

			
		}
		 }
		 
		
	


	/* (non-Javadoc)
	 * @see fractalsTests.FractalTests#init()
	 */
	@Override
	@Before
	public void init() {
		fractals = new BurningShip(null);
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
	       assertEquals(10, fractals.getEscapeTime(-1.6999999999999802, 0.0030136986301371603));
	}


	/* (non-Javadoc)
	 * @see fractalsTests.FractalTests#setMaxEscapeTimeToOneThirtyFiveTest()
	 */
	@Override
	@Test
	public void setMaxEscapeTimeToOneThirtyFiveTest() {
		fractals.setMaxEscapeTime(135);
		assertEquals(135,fractals.getEscapeTime(-1.7443359374999874, -0.017451171875000338));
		
	}

}
