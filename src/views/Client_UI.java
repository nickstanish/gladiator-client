package views;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

public class Client_UI extends JFrame {

	  /**
	   * @author: Nick Stanish
	   * @author: Joey Imburgia
	   */

	  private static final long serialVersionUID = 1635915325136737729L;
	  private JPanel mainPanel;
	  private JButton connectButton;
	  private JTextField username_field, password_field;

	  public Client_UI() {
	    initLogin();

	  }

	  private void initLogin() {
	    Container contentPane = getContentPane();
	    mainPanel = new JPanel();
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    mainPanel.setLayout(new MigLayout("fillx"));
	    mainPanel.setPreferredSize(new Dimension(500, 500));


	    
	    contentPane.add(mainPanel);
	  }

	
}
