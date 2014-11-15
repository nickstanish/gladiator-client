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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Views;
import net.miginfocom.swing.MigLayout;
import request.Request;
import utils.JsonUtils;
import utils.ViewManager;


public class Client_Login_UI extends JPanel {

  /**
   * @author: Nick Stanish
   * @author: Joey Imburgia
   */

  private static final long serialVersionUID = 1635915325136737729L;
  private JPanel mainPanel, cardsPanel;
  private JButton connectButton;
  private JTextField username_field, password_field;
  public ViewManager manager;
  public Container contentPane;


  public Client_Login_UI(ViewManager manager) {
    this.manager = manager;
    initLogin();
  }

  private void initLogin() {

    setLayout(new MigLayout("fill, nogrid"));
    setPreferredSize(new Dimension(300, 200));

    JLabel login_text = new JLabel();
    login_text.setText("Login");

    add(login_text, "alignX center, span, wrap");

    JLabel username_field_text = new JLabel();
    username_field_text.setText("Username: ");
    username_field = new JTextField();

    add(username_field_text, "left");
    add(username_field, "growx, w ::100%, span 2, wrap");

    JLabel password_field_text = new JLabel();
    password_field_text.setText("Password:  ");
    password_field = new JTextField();

    add(password_field_text, "left");
    add(password_field, "growx, w ::100%, span 2, wrap");

    connectButton = new JButton("Connect");
    // java 8 lambda
    connectButton.addActionListener(event -> connect(event));
    add(connectButton, "alignX center, span, wrap");
  }

  private void connect(ActionEvent event) {

    username_field.getText();
    try {
      Socket socket = new Socket("localhost", 8080);
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      // out.println("asdfkhgasdhjf");

      Request userInfo = new Request(username_field.getText(), password_field.getText());
      JsonUtils.writeToSocket(out, userInfo);

      // socket.close();
      // String validation = in.readLine();
    } catch (IOException e) {
    }
    manager.switchView(Views.main_menu);
  }


}
