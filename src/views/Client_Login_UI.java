package views;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import request.Request;
import utils.JsonUtils;

import com.google.gson.Gson;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;


public class Client_Login_UI extends JFrame {

  /**
   * @author: Nick Stanish
   * @author: Joey Imburgia
   */

  private static final long serialVersionUID = 1635915325136737729L;
  private JPanel mainPanel;
  private JButton connectButton;
  private JTextField username_field, password_field;

  public Client_Login_UI() {
    initLogin();

  }

  private void initLogin() {
    Container contentPane = getContentPane();
    mainPanel = new JPanel();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainPanel.setLayout(new MigLayout("fill, nogrid"));
    mainPanel.setPreferredSize(new Dimension(300, 200));

    JLabel login_text = new JLabel();
    login_text.setText("Login");

    CC componentConstraints = new CC();
    componentConstraints.alignX("center").spanX();
    componentConstraints.span();
    componentConstraints.wrap();
    
    mainPanel.add(login_text, componentConstraints);

    JLabel username_field_text = new JLabel();
    username_field_text.setText("Username: ");
    username_field = new JTextField();

    mainPanel.add(username_field_text, "left");
    mainPanel.add(username_field, "growx, w ::100%, span 2, wrap");

    JLabel password_field_text = new JLabel();
    password_field_text.setText("Password:  ");
    password_field = new JTextField();

    mainPanel.add(password_field_text, "left");
    mainPanel.add(password_field, "growx, w ::100%, span 2, wrap");

    CC connectButtonConstraints = new CC();
    connectButtonConstraints.alignX("center").spanX();
    connectButtonConstraints.span();
    connectButtonConstraints.wrap();
    
    
    connectButton = new JButton("Connect");
    // java 8 lambda
    connectButton.addActionListener(event -> connect(event));
    mainPanel.add(connectButton, connectButtonConstraints);


    contentPane.add(mainPanel);
  }

  private void connect(ActionEvent event) {
    
    username_field.getText();
    try {
      Socket socket = new Socket("localhost", 8080);
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      // out.println("asdfkhgasdhjf");
      
        Request userInfo = new Request(username_field.getText(),password_field.getText());
        JsonUtils.writeToSocket(out, userInfo);
        
      socket.close();
      // String validation = in.readLine();
    } catch (IOException e) {
    }

    Client_UI window = new Client_UI();
	window.pack();
	window.setVisible(true);
    
  }
}


