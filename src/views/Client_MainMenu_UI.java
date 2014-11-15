package views;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Views;
import net.miginfocom.swing.MigLayout;
import utils.ViewManager;

public class Client_MainMenu_UI extends JPanel {

  /**
   * @author: Nick Stanish
   * @author: Joey Imburgia
   */

  private static final long serialVersionUID = 1635915325136737729L;
  public ViewManager manager;

  public Client_MainMenu_UI(ViewManager manager) {
    this.manager = manager;
    initMainMenu();
  }



  private void initMainMenu() {

    setLayout(new MigLayout("fill, nogrid"));

    JButton battle_button = new JButton();
    battle_button.setText("Arena");

    battle_button.addActionListener(event -> showBattleSearch(event));

    JButton customize_button = new JButton();
    customize_button.setText("Customize Character");

    customize_button.addActionListener(event -> showCustomize(event));

    JButton leaderboards_button = new JButton();
    leaderboards_button.setText("Leaderboards");

    leaderboards_button.addActionListener(event -> showLeaderboards(event));

    JLabel title = new JLabel();
    title.setText("Gladiator");

    // add(title, "");
    add(battle_button, "w :: 50%, grow");
    add(customize_button, "w ::50%, grow");
    add(leaderboards_button, "newline, grow, w ::100%");

  }



  private void showLeaderboards(ActionEvent event) {
    manager.switchView(Views.leaderboards);
  }



  private void showCustomize(ActionEvent event) {
    manager.switchView(Views.customize);
  }



  private void showBattleSearch(ActionEvent event) {
    manager.switchView(Views.battle_wait_screen);
  }



}
