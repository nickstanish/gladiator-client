import Views.Client_UI;

import com.alee.laf.WebLookAndFeel;


public class Main {

	public static void main (String [] args){
		
		WebLookAndFeel.install(); 
		
		Client_UI window = new Client_UI();
		window.show();
		
	}
	
}
