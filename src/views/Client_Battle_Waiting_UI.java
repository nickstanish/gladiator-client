package views;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.Views;
import utils.ViewManager;


public class Client_Battle_Waiting_UI extends JPanel {

  public ViewManager manager;

  public Client_Battle_Waiting_UI(ViewManager manager) {
    this.manager = manager;
    initBattlePend();
  }

  public void initBattlePend() {

    JButton back_button = new JButton();
    back_button.setText("Back to Menu");

    back_button.addActionListener(event -> backToMenu(event));
    add(back_button);
  }

  private void backToMenu(ActionEvent event) {
    manager.switchView(Views.main_menu);
  }

}
