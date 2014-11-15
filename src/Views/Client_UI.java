package Views;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;


public class Client_UI extends JFrame {
	
	
	 /**
	 * @author: Nick Stanish
	 * @author: Joey Imburgia
	 */
	
	private static final long serialVersionUID = 1635915325136737729L;
	private JPanel mainPanel;
	
	public Client_UI(){
		initLogin();
		
	}
	
	private void initLogin(){
		Container contentPane = getContentPane();
		mainPanel = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 mainPanel.setLayout(new MigLayout("fillx, nogrid"));
		 mainPanel.setPreferredSize(new Dimension(300, 200));
		 
		 JLabel login_text = new JLabel();
		 login_text.setText("Login");

		 mainPanel.add(login_text, "span 3, wrap");

		 JLabel username_field_text = new JLabel();
		 username_field_text.setText("Username: ");
		 JTextField username_field = new JTextField();
		 
		 mainPanel.add(username_field_text, "left");
		 mainPanel.add(username_field, "growx, w ::100%, span 2, wrap");
		 
		 JLabel password_field_text = new JLabel();
		 password_field_text.setText("Password:  ");
		 JTextField password_field = new JTextField();
		 
		 mainPanel.add(password_field_text, "left");
		 mainPanel.add(password_field, "growx, w ::100%, span 2, wrap");

		 
		contentPane.add(mainPanel); 
	}
	
	
	
	
	
	
	
}
