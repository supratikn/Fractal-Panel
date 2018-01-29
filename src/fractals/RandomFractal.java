package fractals;

import java.util.Random;

import userInterface.UI;

public class RandomFractal extends Fractals{


	
	private Random r;
	
	public RandomFractal(UI ui){
		super(ui);
		r= new Random();
		
	}
	
	@Override
	public int getEscapeTime(double x, double y) {
	  return r.nextInt(256);
	}

	@Override
	public void resetRanges() {
		// TODO Auto-generated method stub
		
	}

	

	
}
