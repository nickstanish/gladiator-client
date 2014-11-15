package views;


import java.awt.CardLayout;
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

import net.miginfocom.swing.MigLayout;
import request.Request;
import utils.JsonUtils;


public class Client_Login_UI extends JFrame {

  /**
   * @author: Nick Stanish
   * @author: Joey Imburgia
   */

  private static final long serialVersionUID = 1635915325136737729L;
  private JPanel mainPanel, cardsPanel;
  private JButton connectButton;
  private JTextField username_field, password_field;
  private Container contentPane;
  private static final String LOGIN_SCREEN = "Login";
  private static final String MAINMENU_SCREEN = "MainMenu";

  public Client_Login_UI() {
    initLogin();

  }

  private void initLogin() {
    contentPane = getContentPane();
    contentPane.setLayout(new CardLayout());
    mainPanel = new JPanel();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainPanel.setLayout(new MigLayout("fill, nogrid"));
    mainPanel.setPreferredSize(new Dimension(300, 200));

    JLabel login_text = new JLabel();
    login_text.setText("Login");

    mainPanel.add(login_text, "alignX center, span, wrap");

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

    connectButton = new JButton("Connect");
    // java 8 lambda
    connectButton.addActionListener(event -> connect(event));
    mainPanel.add(connectButton, "alignX center, span, wrap");

    contentPane.add(mainPanel);
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

    contentPane.add(new Client_MainMenu_UI(), MAINMENU_SCREEN);
    switchView(1);

  }

  private void switchView(int screen) {
    CardLayout cl = (CardLayout) (contentPane.getLayout());
    switch (screen) {
      case 0:
        cl.show(contentPane, LOGIN_SCREEN);
        break;
      case 1:
        cl.show(contentPane, MAINMENU_SCREEN);
        break;
    }
  }

}
