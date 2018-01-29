package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.image.IndexColorModel;

import javax.swing.JFrame;
import javax.swing.JLabel;
import edu.buffalo.fractal.FractalPanel;

/**
 * Subclass of FractalPanel, also the class that the UI calls to draw fractals
 * This class was required to draw the rectangle while the mouse is dragged...
 * 
 * @author Supratik Neupane
 * @author Aiden Cox
 * @author Ryan Gangwish
 * @author Sean Mackay
 * 
 *
 */
public class ZoomPanel extends FractalPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * starting and ending points of the drawn rectangle
	 */
	private Point first, moving;
	/**
	 * instance of RangeSelector class to get the points on the screen
	 */
	private RangeSelector rs;
	/**
	 * JFrame that pops up to show the selected points while the mouse is being dragged
	 */
	private JFrame zf;
	/**
	 * JLabels on the screen that show the exact coordinates being selected on the screen
	 */
	private JLabel one, two, three, four;
	/**
	 * instance of UI class that shares an association relation with this class.
	 */
	private UI userInterface;

	/**
	 * calls the constructor in the super class 
	 * 
	 * @param ui assigned to the field ui
	 */
	public ZoomPanel(UI ui, Dimension d, IndexColorModel cMod){
	super(d,cMod);
	userInterface=ui;
}
	/**
	 * assigns the value for the "first" instance variable
	 * 
	 * @param p assigned to first
	 */
	
	public void setFirstPoint(Point p) {
		first = p;
	}

	/**
	 * assigns the value for the "moving" instance variable
	 * 
	 * @param p assigned to moving
	 */
	public void setMovingPoint(Point p) {
		moving = p;
	}

	/**
	 * draws the rectangle on the screen that gives visual representation of what points are being selected 
	 */
	public void paintRectangle() {

		this.paintRectangle(super.getGraphics());
	}

	/**
	 * 
	 * draws the rectangle on the screen that gives visual representation of what points are being selected 
	 * 
	 * @param g  Graphics JComponent of the super class
	 */
	private void paintRectangle(Graphics g) {

		g.setColor(Color.WHITE.brighter().brighter());

		if (rs.getFirstPoint() != null && rs.getMovingPoint() != null) {
			int x = Math.min(first.x, moving.x);
			int y = Math.min(first.y, moving.y);
			int w = Math.abs(first.x - moving.x);
			int h = Math.abs(first.y - moving.y);
			g.drawRect(x, y, w, h);
		}
		super.repaint();
	}

	/**
	 * makes an association relationship with the RangeSelecto class
	 * 
	 * @param r assign it to the field rs
	 */
	public void addRangeSelector(RangeSelector r) {
		rs = r;
	}

	/**
	 * updates the JLabels on the "ZoomPoints" JFrame while the mouse is being dragged
	 */
	public void showPoints() {

		Point.Double first = rs.getFirstPoint();
		Point.Double fourth = rs.getMovingPoint();
		if (rs.getFirstPoint().x > rs.getMovingPoint().x && rs.getFirstPoint().y > rs.getMovingPoint().y) {
			Point.Double temp = first;
			first = fourth;
			fourth = temp;
		}
		if (rs.getFirstPoint().y > rs.getMovingPoint().y && rs.getFirstPoint().x < rs.getMovingPoint().x) {
			Point.Double temp = first;
			first = new Point.Double(first.x, fourth.y);
			fourth = new Point.Double(fourth.x, temp.y);
		}
		if (rs.getFirstPoint().y < rs.getMovingPoint().y && rs.getFirstPoint().x > rs.getMovingPoint().x) {
			Point.Double temp = first;
			first = new Point.Double(fourth.x, first.y);
			fourth = new Point.Double(temp.x, fourth.y);
		}
		Point.Double second = new Point.Double(first.x, fourth.y);
		Point.Double third = new Point.Double(fourth.x, first.y);
		one.setText("(" + (int)first.x + "," + (int)first.y + ")");
		two.setText("(" + (int)third.x + "," + (int)third.y + ")");
		three.setText("(" + (int)second.x + "," + (int)second.y + ")");
		four.setText("(" + (int)fourth.x + "," + (int)fourth.y + ")");

	}

	/**
	 * disposes the "ZoomPoints" JFrame after the mouse is released
	 */
	public void dispose() {
		zf.dispose();
	}

	/**
	 * creates the "ZoomPoints" JFrame
	 */
	public void makeFrame() {
		zf = new JFrame("ZoomPoints");
		zf.setVisible(true);
		
		zf.setLocation(userInterface.getFrameLocation().x+userInterface.getFrameWidth()-15,userInterface.getFrameLocation().y);
		zf.getContentPane().setLayout(new GridLayout(2, 2));
		one = new JLabel("First");
		two = new JLabel("Second");
		three = new JLabel("Third");
		four = new JLabel("Fourth");
		one.setFont(new Font("Helvetica", 20, 20));
		two.setFont(new Font("Helvetica", 20, 20));
		three.setFont(new Font("Helvetica", 20, 20));
		four.setFont(new Font("Helvetica", 20, 20));
		one.setForeground(Color.RED);
		two.setForeground(Color.GREEN.darker().darker());
		three.setForeground(Color.BLUE);
		four.setForeground(Color.YELLOW.darker().darker());
		zf.setSize(250, 250);
		zf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		zf.add(one);
		zf.add(two);
		zf.add(three);
		zf.add(four);
		
	}
}
