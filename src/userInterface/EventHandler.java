package userInterface;

import java.awt.Desktop;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;


/**
 * The class that handles all the action events, change events and some key events.
 * 
 * @author Supratik Neupane
 * @author Aiden Cox
 * @author Ryan Gangwish
 * @author Sean Mackay
 *
 */
public class EventHandler implements ActionListener, MenuListener, KeyListener, ChangeListener, MouseListener, MouseMotionListener{

	
	/**
	 *  instance of the UI class that shares an association relation with this class.
	 */
	private UI ui;
	/**
	 * instance of ZoomPanel THAT IS A SUBCLASS OF THE FractalPanel Class and shares and association relation with this class.
	 */
	private ZoomPanel z;
	/**
	 * instance of RangeSelector that shares an association relation with this class.
	 */
	private RangeSelector rs;
	
	/**
	 * assigns the instance variable
	 * 
	 * @param ui 'this' passed as parameter in the UI class
	 */
	public EventHandler(UI ui, ZoomPanel z){
		this.ui=ui;
		rs = new RangeSelector();
		this.z=z;
		
		z.addRangeSelector(rs);
				
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//Change the image to the Mandelbrot fractal
		if(e.getSource().equals(ui.mandelbrot)){
			ui.update(new FractalSelector(1,ui));
		}
		//Change the image to the Julia fractal
		if(e.getSource().equals(ui.julia)){
			ui.update(new FractalSelector(2,ui));
		}
		//Change the image to the BurningShip fractal
		if(e.getSource().equals(ui.burningShip)){
			ui.update(new FractalSelector(3,ui));
		}
		//Change the image to the Multibrot fractal
		if(e.getSource().equals(ui.multibrot)){
			ui.update(new FractalSelector(4,ui));
			
		}
		//Exit
		if(e.getSource().equals(ui.exit)){
			System.exit(0);
		}


		//opens the update.txt file

		if(e.getSource().equals(ui.updtxt)){
			try {
				Desktop.getDesktop().open(new java.io.File("src/update.txt"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			}
		//opens the run.txt exception 
		if(e.getSource().equals(ui.runtxt)){
			try {
				Desktop.getDesktop().open(new java.io.File("src/run.txt"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
		}
		//opens the reset.txt file
		if(e.getSource().equals(ui.resettxt)){
			try {
				Desktop.getDesktop().open(new java.io.File("src/reset.txt"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}
		if(e.getSource().equals(ui.save))ui.saveImage();
		}
		
	
	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent e){
		JSlider source = (JSlider)e.getSource();
		//Slider that changes the brightness of the GreyColorModel from darkest to brightest.
		if(e.getSource().equals(ui.chooseG)){
		ui.updateColor(ColorChooser.createGrayColorModel(source.getValue()));
		}
		//Slider that changes the brightness of the BlueColorModel from darkest to brightest.
		if(e.getSource().equals(ui.chooseB)){
			ui.updateColor(ColorChooser.createBluesColorModel(source.getValue()));
		}
		//Slider that changes the brightness of the HawaiianColorModel from darkest to brightest.
		if(e.getSource().equals(ui.chooseR)){
			ui.updateColor(ColorChooser.createRainbowColorModel(source.getValue()));
		}
		//Slider that changes the brightness of the TealColorModel from darkest to brightest.
		if(e.getSource().equals(ui.chooseU)){
			ui.updateColor(ColorChooser.createUserColorModel(source.getValue()));
		}
 
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.MenuListener#menuCanceled(javax.swing.event.MenuEvent)
	 */
	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.MenuListener#menuDeselected(javax.swing.event.MenuEvent)
	 */
	@Override
	public void menuDeselected(MenuEvent e) {
		
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.MenuListener#menuSelected(javax.swing.event.MenuEvent)
	 */
	@Override
	public void menuSelected(MenuEvent e){
		if(e.getSource().equals(ui.distSel)){
			ui.updateDistance();
		}
		if(e.getSource().equals(ui.maxEscTime)){
			ui.updateMaxEscapeTime();
		}
		if(e.getSource().equals(ui.zoom)){
			ui.resetZoom();
		}
		if(e.getSource().equals(ui.threads)){
			ui.updateThreads();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
		
	
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		z.setFirstPoint(new Point(e.getX(), e.getY()));
        double zx = ui.getPanelWidth()/2048;
        double zy = ui.getPanelHeight()/2048;
        
		rs.setFirstPoint(new Point.Double(e.getX() / zx, e.getY() / zy));
		if (rs.getFirstPoint().x > 2048)
			rs.setFirstPoint(new Point.Double(2048, rs.getFirstPoint().y));

		if (rs.getFirstPoint().y > 2048)
			rs.setFirstPoint(new Point.Double(rs.getFirstPoint().x, 2048));
		if (rs.getFirstPoint().x < 0)
			rs.setFirstPoint(new Point.Double(0, rs.getFirstPoint().y));
		if (rs.getFirstPoint().y < 0)
			rs.setFirstPoint(new Point.Double(rs.getFirstPoint().x, 0));

		rs.setSecondPoint(null);
		rs.setMovingPoint(null);

		z.makeFrame();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		 double zx = ui.getPanelWidth()/2048;
	        double zy = ui.getPanelHeight()/2048;
		rs.setSecondPoint(new Point.Double(e.getX() / zx, e.getY() / zy));
		if (rs.getSecondPoint().x > 2048)
			rs.setSecondPoint(new Point.Double(2048, rs.getSecondPoint().y));
		if (rs.getSecondPoint().y > 2048)
			rs.setSecondPoint(new Point.Double(rs.getSecondPoint().x, 2048));
		if (rs.getSecondPoint().x < 0)
			rs.setSecondPoint(new Point.Double(0, rs.getSecondPoint().y));
		if (rs.getSecondPoint().y < 0)
			rs.setSecondPoint(new Point.Double(rs.getSecondPoint().x, 0));

		z.setMovingPoint(new Point(e.getX(), e.getY()));
		z.paintRectangle();
		ui.updateRanges(rs);

		z.dispose();
		return;

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		
		 double zx = ui.getPanelWidth()/2048;
	        double zy = ui.getPanelHeight()/2048;
		z.setMovingPoint(new Point(e.getX(), e.getY()));

		rs.setMovingPoint(new Point.Double(e.getX() / zx, e.getY() / zy));
		if (rs.getMovingPoint().x > 2048)
			rs.setMovingPoint(new Point.Double(2048, rs.getMovingPoint().y));

		if (rs.getMovingPoint().y > 2048)
			rs.setMovingPoint(new Point.Double(rs.getMovingPoint().x, 2048));
		if (rs.getMovingPoint().x < 0)
			rs.setMovingPoint(new Point.Double(0, rs.getMovingPoint().y));
		if (rs.getMovingPoint().y < 0)
			rs.setMovingPoint(new Point.Double(rs.getMovingPoint().x, 0));

		z.showPoints();
		z.paintRectangle();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
