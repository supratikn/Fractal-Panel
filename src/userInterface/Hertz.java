package userInterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Hertz implements ActionListener, WindowListener{
	private JFrame frame = new JFrame();
	private JButton btn = new JButton("Spin");
	private JLabel a = new JLabel();
	
	private JLabel b = new JLabel();
	private JLabel c = new JLabel();
	private int a1;
	private int b1;
	private int c1;
	
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	
	
	public Hertz(){
		

		a1 = repaint(a);
		b1 = repaint(b);
		c1 = repaint(c);
		
		
		
		while(matchingPhotos()){
			a1 = repaint(a);
			b1 = repaint(b);
			c1 = repaint(c);
		}
		p1.setSize(200, 200);
		p1.add(a);
		p1.add(b);
		p1.add(c);
		p2.add(btn);
		
		frame.add(p1);
		frame.add(p2);
		
		btn.addActionListener(this);
		
		frame.setVisible(true);
		frame.setLayout(new GridLayout(2,1));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public JLabel painter(){
		JLabel temp = new JLabel();
		Random rand = new Random();
		int i = rand.nextInt(3);
		//System.out.println(i);
		if(i == 0){
			temp.setIcon(new ImageIcon("Images/shirt1.jpg"));
			temp.setText("0");
		}
		if(i == 1){
			temp.setIcon(new ImageIcon("Images/shirt22.jpg"));
			temp.setText("1");
		}
		if(i == 2){
			temp.setIcon(new ImageIcon("Images/shirt33.jpg"));
			temp.setText("2");
		}
		return temp;
	}
	public int repaint(JLabel x){
		Random rand = new Random();
		int i = rand.nextInt(3);
		
		if(i == 0){
			x.setIcon(new ImageIcon("Images/shirt1.jpg"));
			//x.setText("0");
			return 0;
		}
		if(i == 1){
			x.setIcon(new ImageIcon("Images/shirt22.jpg"));
			//return 1;
		}
		if(i == 2){
			x.setIcon(new ImageIcon("Images/shirt33.jpg"));
			return 2;
		}
		return 3;
	}
	public boolean matchingPhotos(){
		
		return a1==b1 && a1==c1;
	}
	public void update(){
		a1 = repaint(a);
		b1 = repaint(b);
		c1 = repaint(c);
		
		if(matchingPhotos()){
			btn.setText("You've done it!");
			btn.setEnabled(false);
			hertzFlood();
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		update();
		
	}
	public void hertzFlood(){
		int i = 0;
		while(i<26){
			Random r = new Random();
			JFrame temp = new JFrame();
			temp.setVisible(true);
			
	        JLabel label = new JLabel();
	        //System.out.println("Images/matt"+r.nextInt(4)+".jpg");
	        label.setIcon(new ImageIcon("Images/matt"+r.nextInt(4)+".jpg"));
	        temp.add(label);
	        temp.setLayout(new GridLayout(1,1));
	        temp.pack();
	        temp.setLocation(r.nextInt(2000), r.nextInt(1000));
	        temp.addWindowListener(this);
	        i++;
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		hertzFlood();
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
