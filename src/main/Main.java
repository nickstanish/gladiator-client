package main;
import views.Client_Login_UI;

import com.alee.laf.WebLookAndFeel;


public class Main {

	public static void main (String [] args){
		
		WebLookAndFeel.install(); 
		
		Client_Login_UI window = new Client_Login_UI();
		window.pack();
		window.setVisible(true);
	}
	
}
