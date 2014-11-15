package views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class Client_MainMenu_UI extends JPanel {

  /**
   * @author: Nick Stanish
   * @author: Joey Imburgia
   */

  private static final long serialVersionUID = 1635915325136737729L;

  public Client_MainMenu_UI() {
    initMainMenu();
  }



  private void initMainMenu() {

    setLayout(new MigLayout("fill, nogrid"));

    JButton battle_button = new JButton();
    battle_button.setText("Areana");

    JButton customize_button = new JButton();
    customize_button.setText("Customize Character");

    JButton leaderboards_button = new JButton();
    leaderboards_button.setText("Leaderboards");

    JLabel title = new JLabel();
    title.setText("Gladiator");

    add(title, "");
    add(battle_button, "w :: 50%");
    add(customize_button, "w ::50%");
    add(leaderboards_button, "w ::100%");

  }


}
