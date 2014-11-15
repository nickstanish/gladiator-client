package views;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Views;
import net.miginfocom.swing.MigLayout;
import request.Request;
import responses.Response;
import utils.JsonUtils;
import utils.ViewManager;


public class Client_Login_UI extends JPanel {

  /**
   * @author: Nick Stanish
   * @author: Joey Imburgia
   */

  private static final long serialVersionUID = 1635915325136737729L;
  private JButton connectButton;
  private JLabel error_text;
  private JTextField username_field;
  private JPasswordField password_field;
  public ViewManager manager;
  public Container contentPane;


  public Client_Login_UI(ViewManager manager) {
    this.manager = manager;
    initLogin();
  }

  private void initLogin() {

    setLayout(new MigLayout("fillx, nogrid"));
    setPreferredSize(new Dimension(300, 200));

    JLabel login_text = new JLabel();
    login_text.setText("Login");

    add(login_text, "alignX center, span, wrap");

    JLabel username_field_text = new JLabel();
    username_field_text.setText("Username: ");
    username_field = new JTextField();

    add(username_field_text, "left");
    add(username_field, "growx, w ::100%, span 2");

    JLabel password_field_text = new JLabel();
    password_field_text.setText("Password:  ");
    password_field = new JPasswordField();

    // add(password_field_text, "left");
    // add(password_field, "growx, w ::100%, span 2, wrap");

    connectButton = new JButton("Connect");
    // java 8 lambda
    connectButton.addActionListener(event -> connect(event));
    add(connectButton, "newline, alignX center");

    error_text = new JLabel();
    error_text.setForeground(Color.RED);
    add(error_text, "alignX center, span, wrap");

  }

  private void connect(ActionEvent event) {

    try {
      manager.socket = new Socket("localhost", 8080);
      manager.in = new BufferedReader(new InputStreamReader(manager.socket.getInputStream()));
      manager.out = new PrintWriter(manager.socket.getOutputStream(), true);

      Request userInfo = new Request(username_field.getText().trim(), password_field.getText());

      Response response = JsonUtils.makeRequest(manager.out, userInfo, manager.in, Response.class);

      if (response != null && response.success != null) {
        if (response.success) {
          manager.switchView(Views.battle_wait_screen);
        } else {
          error_text.setText(response.message);
        }

      }

    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
