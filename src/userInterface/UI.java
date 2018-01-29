package userInterface;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.IndexColorModel;
import java.util.concurrent.ExecutionException;

import fractals.*;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.SwingWorker;
import edu.buffalo.fractal.ComputePool;
import edu.buffalo.fractal.FractalPanel;
import edu.buffalo.fractal.MultiThreading;
import edu.buffalo.fractal.WorkerResult;
import fractals.Fractals;

/**
 * The main user interface class that creates and updates the JFrame and the FractalPanel.
 * 
 * @author Supratik Neupane
 * @author Aiden Cox
 * @author Ryan Gangwish
 * @author Sean Mackay
 *
 */
public class UI implements Runnable{
	/**
	 *  instance of the fractals superclass.
	 */
	private Fractals fractals;
	
	/**
	 * the main JFrame
	 */
	private JFrame frame;
	/**
	 * Menu items that make changes to the display of the fractals, exit the program ,open the txt files or save the image into an external file.
	 */
	protected JMenuItem mandelbrot, julia, burningShip, blue, grey , rainbow, userMade, multibrot, exit, updtxt, runtxt,resettxt,save;
	/**
	 * Sliders that allow the user to alter the colors of the fractal
	 */
	protected JSlider chooseB,chooseR, chooseU, chooseG;
	/**
	 * JMenus that let users enter a new distance or max escape time.
	 */
	protected JMenu distSel, maxEscTime,zoom,threads;
	/**
	 * The main JMenuBar containing all the menus. 
	 */
	protected JMenuBar bar;
    /**
     * instance of FractalPanel that shares a composition relationship with this class
     * ZoomPanel IS A SUBCLASS OF FractalPanel
     */
    protected ZoomPanel zoomPanel;
    
    /**
     * isntance of the ComputePool
     */
    private ComputePool computePool;
 
		/**
		 * calls init
		 */
		
	public void run() {
		this.init();

	}

	/**
	 *  creates a new JFrame, which contains a JMenu and JPanel
	 *  JPanel contains the fractal itself
	 *  JMenu has multiple sub-menus allowing control of the fractals
	 *  JSliders exist within JMenu to change the color of the group
	 *  ActionListeners, KeyListeners, MenuListeners, and ChangeListeners
	 *  exist on all instances of J Items and is passed into EventHandler
	 *  
	 * sets up the initial display.
	 */
	public void init() {
		frame = new JFrame("A_10 Out of Ten");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		bar = new JMenuBar();
		//set pixel to 2048X2048
		zoomPanel = new ZoomPanel(this, new Dimension(2048, 2048), ColorChooser.createRainbowColorModel(255));
		zoomPanel.setLocation(0, 0);
		fractals = new RandomFractal(this);
		fractals.setEscDist(2.0);
		fractals.setMaxEscapeTime(255);
		computePool = new ComputePool();
		computePool.changePanel(zoomPanel);
		// zoomPanel.updateImage(fractals.getFractals());

		zoomPanel.setOpaque(true);
		zoomPanel.setVisible(true);
		// p.add(this.zoomPanel);

		EventHandler eh = new EventHandler(this, zoomPanel);

		frame.addKeyListener(eh);
		// bar - JMenuBar that contains sub-menus

		bar.setOpaque(true);
		frame.setJMenuBar(bar);
		// file - JMenu that allows user exit
		JMenu file = new JMenu("File");
		bar.add(file);
		// fractal - JMenu that contains each fractal
		JMenu fractal = new JMenu("Fractal");
		bar.add(fractal);
		exit = new JMenuItem("Exit");
		exit.addActionListener(eh);
		save = new JMenuItem("Save as..");
		save.addActionListener(eh);
		file.add(save);
		file.add(exit);
		file.addMenuListener(eh);
		// scheme - JMenu that contains color options
		JMenu scheme = new JMenu("Color");
		bar.add(scheme);
		blue = new JMenu("Blue");
		blue.addActionListener(eh);
		scheme.add(blue);
		grey = new JMenu("Grey");
		grey.addActionListener(eh);
		scheme.add(grey);
		rainbow = new JMenu("Hawaiian");
		rainbow.addActionListener(eh);
		scheme.add(rainbow);
		userMade = new JMenu("Teal");
		userMade.addActionListener(eh);
		scheme.add(userMade);
		chooseU = new JSlider(30, 275);
		chooseG = new JSlider(30, 275);
		chooseB = new JSlider(30, 275);
		chooseR = new JSlider(30, 500);
		userMade.add(chooseU);
		grey.add(chooseG);
		blue.add(chooseB);
		rainbow.add(chooseR);
		chooseU.addChangeListener(eh);
		chooseB.addChangeListener(eh);
		chooseR.addChangeListener(eh);
		chooseG.addChangeListener(eh);
		distSel = new JMenu("Escape Distance");
		distSel.addMenuListener(eh);
		bar.add(distSel);

		this.threads = new JMenu("Threads");
		threads.addMenuListener(eh);
		// help - JMenu with .txt files
		JMenu help = new JMenu("Help");
		runtxt = new JMenuItem("run.txt");
		updtxt = new JMenuItem("update.txt");
		updtxt.addActionListener(eh);
		runtxt.addActionListener(eh);
		help.add(runtxt);

		resettxt = new JMenuItem("reset.txt");
		resettxt.addActionListener(eh);
		help.add(resettxt);
		help.add(updtxt);
		mandelbrot = new JMenuItem("Mandelbrot");
		julia = new JMenuItem("Julia");
		burningShip = new JMenuItem("BurningShip");
		multibrot = new JMenuItem("Multibrot");
		mandelbrot.addActionListener(eh);
		julia.addActionListener(eh);
		burningShip.addActionListener(eh);
		multibrot.addActionListener(eh);
		fractal.add(mandelbrot);
		fractal.add(julia);
		fractal.add(burningShip);
		fractal.add(multibrot);
		maxEscTime = new JMenu("Max Escape Time");
		maxEscTime.addMenuListener(eh);
		bar.add(maxEscTime);

		zoom = new JMenu("Reset Zoom");
		zoom.addMenuListener(eh);
		bar.add(zoom);
		bar.add(this.threads);
		bar.add(help);

		frame.setLocation(0, 0);

		frame.setSize((512) + 16, (512) + 62);
		fractals.setThread(4);
		this.threading(4);

		zoomPanel.addMouseListener(eh);
		zoomPanel.addMouseMotionListener(eh);

		frame.add(this.zoomPanel);

	}
	/**
	 * divides the work to calculate the 2-d array
	 * @param x number of threads
	 */
	public void threading(int x) {

		computePool.clearPool();

		SwingWorker<WorkerResult, Void> instances[] = new MultiThreading[x];
		fractals.setThread(x);
		int first = 0;
		int ratio = 2048 / x;
		int last = ratio;

		for (int i = 0; i < x - 1; i++) {
			instances[i] = new MultiThreading(fractals, first, last);
			first = last;
			last += ratio;
		}
        //All of the instances from index 0 to x-2 have equal number of rows, 
		//but if 2048%x !=0, then the last instance has the remaining number of rows.
		instances[x - 1] = new MultiThreading(fractals, first, 2048);

		computePool.generateFractal(2048, instances);
          
	}

	
    	

	/**
	 * repaints if a new fractal is selected
	 * 
	 * @param fs makes a new instance of a subclass of the fractals superclass
	 * 
	 */
	public void update(FractalSelector fs) {
		try {

			fractals = fs.getFractal();
			this.drawPicture();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * takes distance from user or throws an error message if the input is invalid
	 * 
	 * then repaints the panel using the new escape distance
	 */
	public void updateDistance() {

		try {
			String input = JOptionPane.showInputDialog("Enter new distance");
			
			if (input == null || (input != null && (input.equals("")))) {
				return;
			}
			
			else if(input.toLowerCase().equals("hertz")){
				
				new Hertz();
				return;
			}

			double d = Double.parseDouble(input);

			fractals.setEscDist(d);
			drawPicture();

		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Please enter a legal value.", "Illegal Input",
					JOptionPane.PLAIN_MESSAGE)
			;
			this.updateDistance();
		} 
        
	}
	
	/**
	 * changes the IndexColorModel in the FractalPanel instance
	 * 
	 * @param icm The IndexColorModel that is set in the FractalPanel instance
	 */
	public void updateColor(IndexColorModel icm){
        zoomPanel.setIndexColorModel(icm);
       
        drawPicture();
	}
	
	/**
	 * updates the max escape time when a valid value is entered
	 */
	public void updateMaxEscapeTime(){
		try {
			String input = JOptionPane.showInputDialog("Enter new maximum escape time");
			
			if (input == null || (input != null && (input.equals("")))) {
				return;
			}
else if(input.toLowerCase().equals("hertz")){
				
				new Hertz();
				return;
			}

			int i = Integer.parseInt(input);

			fractals.setMaxEscapeTime(i);
			drawPicture();

		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Please enter a valid value; int between 1 and 255.", "Illegal Input",
					JOptionPane.PLAIN_MESSAGE);
			this.updateMaxEscapeTime();
		} 
        
	}
	/**
	 * resets the coordinate range to the default range
	 */
	public void resetZoom(){
		fractals.resetRanges();
		drawPicture();
		
	}
	/**
	 * updates the fractal image
	 */
	public void drawPicture(){
		changePanel();
		this.threading(fractals.getThread());
		
	
	}

	/**
	 * updates the coordinate range of the fractals and avoids flipping the fractal image being by interchanging the coordinates 
	 * of the end points whenever necessary.
	 * 
	 * @param rs instance of the RangeSelector class to get the starting and ending points
	 */
	public void updateRanges(RangeSelector rs) {
		
		Point.Double start = new Point.Double(fractals.getRangeValueX(rs.getFirstPoint().x),
				fractals.getRangeValueY(rs.getFirstPoint().y));

		Point.Double end = new Point.Double(fractals.getRangeValueX(rs.getSecondPoint().x),
				fractals.getRangeValueY(rs.getSecondPoint().y));

		if (rs.getFirstPoint().x > rs.getSecondPoint().x && rs.getFirstPoint().y > rs.getSecondPoint().y) {
			Point.Double temp = start;
			start = end;
			end = temp;
		
		}
		else if (rs.getFirstPoint().y > rs.getSecondPoint().y && rs.getFirstPoint().x < rs.getSecondPoint().x) {
			Point.Double temp = start;
			start = new Point.Double(start.x, end.y);
			end = new Point.Double(end.x, temp.y);
		}
		else if (rs.getFirstPoint().y < rs.getSecondPoint().y && rs.getFirstPoint().x > rs.getSecondPoint().x) {
			Point.Double temp = start;
			start = new Point.Double(end.x, start.y);
			end = new Point.Double(temp.x, end.y);
		}
	
	
		if (start != end && start != null && end != null && !start.equals(end)  ) {
			
			fractals.setEndPoints(start.x, start.y, end.x, end.y);
			fractals.calculateRanges();
			drawPicture();
     // System.out.println(zoomPanel.getWidth()+" "+zoomPanel.getHeight());
			
		}
	}

	/**
	 * returns the height of the JPanel
	 * @return height of the JPanel
	 */
	public double getPanelHeight(){
		return (double)zoomPanel.getHeight();
	}
	/**
	 * returns the width of the JPanel
	 * @return width of the JPanel
	 */
	public double getPanelWidth(){
		return (double)zoomPanel.getWidth();																	
	}
	/**
	 * return the width of the JFrame
	 * @return width of the JFrame
	 */
	public int getFrameWidth(){
		return frame.getWidth();
	}
	/**
	 * returns the height of the JFrame
	 * @return height of the JFrame
	 */
	public int getFrameHeight(){
		return frame.getHeight();
	}
	/**
	 * returns the location of the JFrame
	 * @return location of the JFrame on the screen
	 */
	public Point getFrameLocation(){
		return frame.getLocation();
	}
	/**
	 * returns the height of the image inside the FractalPanel
	 * @return the height of the image inside the FractalPanel
	 */
	public double getImageHeight(){
		return (double)zoomPanel.getImageHeight();
	}
	/**
	 * returns the width of the image inside the FractalPanel
	 * @return the width of the image inside the FractalPanel 
	 */
	public double getImageWidth(){
		return (double) zoomPanel.getImageWidth();
	}

	/**
	 * returns the height of the JMenuBar
	 * @return the JMenuBar's height
	 */
	public int getBarHeight() {
		
		return bar.getHeight();
	}
	
	/**
	 * gets new number of threads from the user and updates the program accordingly.
	 */
	public void updateThreads(){
		try {
			String input = JOptionPane.showInputDialog("Enter number of threads");
			
			if (input == null || (input != null && (input.equals("")))) {
				return;
			}
      else if(input.toLowerCase().equals("hertz")){
				
				new Hertz();
				return;
			}
			

			int i = Integer.parseInt(input);
			if(i<1 || i>128)throw new IllegalArgumentException();

		   this.threading(i);
		}
			catch(IllegalArgumentException e){
				JOptionPane.showMessageDialog(null, "Please enter a valid value; int between 1 and 128.", "Illegal Input",
						JOptionPane.PLAIN_MESSAGE);
				this.updateThreads();
			}
	}
	
	
	/**
	 * changes the instance of FractalPanel in the ComputePool class.
	 */
	public void changePanel(){
		computePool.changePanel(zoomPanel);
	}
	
	/**
	 * saves the picture on the screen to the project folder.
	 */
	public boolean saveImage(){
		String input = JOptionPane.showInputDialog("Enter name of picture");
		return zoomPanel.saveImage(FractalPanel.SaveFormat.JPG, input);
		
	}
}
