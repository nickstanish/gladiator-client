package Views;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;


public class Client_UI extends JFrame {
	
	
	 /**
	 * @author: Nick Stanish
	 * @author: Joey Imburgia
	 */
	
	private static final long serialVersionUID = 1635915325136737729L;
	private JFrame window;
	private JPanel mainPanel;
	
	public Client_UI(){
		
		window = new JFrame();
		Container contentPane = window.getContentPane();
		mainPanel = new JPanel();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 mainPanel.setLayout(new MigLayout());
		 mainPanel.setPreferredSize(new Dimension(300, 500));
		 
		contentPane.add(mainPanel); 
		window.show();
	}
	
	
	
	
	
	
	
}
