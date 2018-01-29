package edu.buffalo.cse116;



/**
 * The main class from where the program runs.
 * 
 * @author Supratik Neupane
 * @author Aiden Cox
 * @author Ryan Gangwish
 * @author Sean Mackay
 *
 */
public class Main {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new userInterface.UI());
       /* Thread thread = new Thread(new userInterface.UI());
        thread.start();*/
	}
    
}
