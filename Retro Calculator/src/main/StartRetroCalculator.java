package main;

import javax.swing.JFrame;
import gui.RetroCalculator;

public class StartRetroCalculator {

	// Main method to start application
	public static void main(String[] args) {
			    JFrame frame = new RetroCalculator();
			    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			    frame.setBounds( 200, 200, 500, 500 );
			    frame.setTitle( "Retro Calculator" );
			    frame.setVisible( true );

	}

}
