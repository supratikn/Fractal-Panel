package fractalsTests;

/** 
 * 'MultibrotTest' is a JUnit test that evaluates
 * the Multibrot file
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
import fractals.Multibrot;


public class MultibrotTest extends FractalTests{
	

	/* (non-Javadoc)
	 * @see fractalsTests.FractalTests#neverEscapeTest()
	 */
	@Test
	@Override
	public void neverEscapeTest() {
		
		assertEquals(255, fractals.getEscapeTime(0.5859375, 0.24375000000000108));
	}
	
	/**
	 * escapeAfterOneLoopTest runs getEscapeTime of Multibrot and confirms that
	 * a point escapes after one run through the algorithm
	 */
	
    @Test
    public void escapeAfterOneLoopTest(){
    
    	assertEquals(1, fractals.getEscapeTime(0.9921875, 1.05625));
    }
    
    
    /* (non-Javadoc)
     * @see fractalsTests.FractalTests#arraySizeTest()
     */
    @Test
	@Override
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
					assertEquals(2048,instance.get().getRow(511).length);
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
    @Test
	@Override
	public void xCoordinateTest() {
    	
		int z = r.nextInt(2048);
		double x = (z *fractals.rangeX) + fractals.startX;
		assertEquals(x,fractals.getRangeValueX(z), 0.01);
		assertEquals(fractals.startX,fractals.getRangeValueX(0),0.01);
		assertEquals(fractals.endX,fractals.getRangeValueX(2047),0.01);
	}
     
    /* (non-Javadoc)
     * @see fractalsTests.FractalTests#yCoordinateTest()
     */
    @Test
	@Override
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
		fractals = new Multibrot(null);
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
	       assertEquals(10, fractals.getEscapeTime(0.7025440313111545, -0.5520547945205528));
		
	}

	/* (non-Javadoc)
	 * @see fractalsTests.FractalTests#setMaxEscapeTimeToOneThirtyFiveTest()
	 */
	@Override
	@Test
	public void setMaxEscapeTimeToOneThirtyFiveTest() {
		fractals.setMaxEscapeTime(135);
		assertEquals(135,fractals.getEscapeTime(0.5859375, 0.24375000000000108));
		
	}
    
	

}
