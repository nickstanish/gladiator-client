package views;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import main.Views;
import net.miginfocom.swing.MigLayout;
import request.CancelRequest;
import request.GameRequest;
import utils.JsonUtils;
import utils.ViewManager;


public class Client_Battle_Waiting_UI extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  public ViewManager manager;
  public boolean searching = true;
  public JLabel searching_text;
  public JButton cancel_button;
  public SwingWorker<Void, Void> worker;

  public Client_Battle_Waiting_UI(ViewManager manager) {
    this.manager = manager;
    initBattlePend();

    search();

  }

  public void search() {
    worker = new SwingWorker<Void, Void>() {
      @Override
      protected Void doInBackground() throws Exception {
        // Simulate doing something useful.

        try {
          @SuppressWarnings("resource")
          Socket socket = new Socket("localhost", 8080);
          BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          PrintWriter out = new PrintWriter(socket.getOutputStream(), true);


          GameRequest g_request = new GameRequest();
          JsonUtils.writeToSocket(out, g_request);
        } catch (IOException e) {
        }

        for (int i = 0; i <= 1000; i++) {

          if (i % 4 == 0) {
            searching_text.setText("Searching for your Opponent");
          }
          if (i % 4 == 1) {
            searching_text.setText("Searching for your Opponent.");
          }
          if (i % 4 == 2) {
            searching_text.setText("Searching for your Opponent..");
          }
          if (i % 4 == 3) {
            searching_text.setText("Searching for your Opponent...");
          }


          Thread.sleep(1000);
        }
        return null;
      }
    };
    worker.execute();



  }


  public void initBattlePend() {

    setLayout(new MigLayout("fillx"));
    JButton back_button = new JButton();
    back_button.setText("Back to Menu");

    back_button.addActionListener(event -> backToMenu(event));
    add(back_button);

    JButton temp = new JButton();
    temp.setText("Temp to Battle");

    temp.addActionListener(event -> onConnect(event));
    // add(temp);

    searching_text = new JLabel();
    searching_text.setText("Searching for your Opponent");
    add(searching_text, "newline, alignx center");

    cancel_button = new JButton();
    cancel_button.setText("Cancel Search");
    add(cancel_button, "newline, span, wrap, alignx center");

    cancel_button.addActionListener(event -> searchToggle(event));

  }

  private void searchToggle(ActionEvent event) {
    if (searching) {
      searching = false;
      try {
        @SuppressWarnings("resource")
        Socket socket = new Socket("localhost", 8080);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        CancelRequest c_request = new CancelRequest();
        JsonUtils.writeToSocket(out, c_request);
      } catch (IOException e) {
      }
      worker.cancel(true);
      searching_text.setText("");
      cancel_button.setText("Resume Searching");
    } else {
      searching = true;
      search();
    }
  }

  public void onConnect(ActionEvent event) {
    manager.switchView(Views.battle);
  }


  private void backToMenu(ActionEvent event) {
    manager.switchView(Views.main_menu);
  }

}
