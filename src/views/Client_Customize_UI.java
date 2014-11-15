package views;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.Views;
import net.miginfocom.swing.MigLayout;
import utils.ViewManager;


public class Client_Customize_UI extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  public ViewManager manager;

  public Client_Customize_UI(ViewManager manager) {
    this.manager = manager;
    initCustomize();
  }

  public void initCustomize() {

    this.setLayout(new MigLayout());

    JButton back_button = new JButton();
    back_button.setText("Back to Menu");

    back_button.addActionListener(event -> backToMenu(event));
    add(back_button, "");

    JButton warrior_selection = new JButton();
    warrior_selection.setText("Warrior");

    JButton thief_selection = new JButton();
    thief_selection.setText("Thief");

    JButton mage_selection = new JButton();
    mage_selection.setText("Mage");

    add(warrior_selection, "nextline");
    add(thief_selection, "");
    add(mage_selection, "");



  }

  private void backToMenu(ActionEvent event) {
    manager.switchView(Views.main_menu);
  }

}
