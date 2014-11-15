package views;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.Views;
import net.miginfocom.swing.MigLayout;
import utils.ViewManager;


public class Client_Leaderboards_UI extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  public ViewManager manager;

  public Client_Leaderboards_UI(ViewManager manager) {
    this.manager = manager;
    initBattlePend();
  }

  public void initBattlePend() {

    setLayout(new MigLayout("Fill"));
    JButton back_button = new JButton();
    back_button.setText("Back to Menu");

    back_button.addActionListener(event -> backToMenu(event));

    add(back_button, "south");

  }

  private void backToMenu(ActionEvent event) {
    manager.switchView(Views.main_menu);
  }

}
