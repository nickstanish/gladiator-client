package views;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.Views;
import utils.ViewManager;


public class Client_Battle_UI extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  public ViewManager manager;

  public Client_Battle_UI(ViewManager manager) {
    this.manager = manager;
    initBattle();
  }

  public void initBattle() {

    JButton back_button = new JButton();
    back_button.setText("Back to Menu");

    back_button.addActionListener(event -> backToMenu(event));
    add(back_button);
  }

  private void backToMenu(ActionEvent event) {
    manager.switchView(Views.main_menu);
  }

}
