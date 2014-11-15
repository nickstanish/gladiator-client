package views;

import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import main.Views;
import utils.ViewManager;


public class Client_Battle_UI extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  public ViewManager manager;
  public SwingWorker<Void, Void> worker;

  public Client_Battle_UI(ViewManager manager) {
    this.manager = manager;
    initBattle();

    // JsonUtils.writeToSocket(manager.out, CharacterInfo.makeMage());
  }

  public void initBattle() {

    JButton back_button = new JButton();
    back_button.setText("Back to Menu");

    back_button.addActionListener(event -> backToMenu(event));
    // add(back_button);

  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.drawString("BLAH", 20, 20);
    g.drawRect(200, 200, 200, 200);
  }

  private void backToMenu(ActionEvent event) {
    manager.switchView(Views.main_menu);
  }

  public void waitForTurn() {
    worker = new SwingWorker<Void, Void>() {
      @Override
      protected Void doInBackground() throws Exception {
        // Simulate doing something useful.
        for (int i = 0; i <= 180; i++) {

          // BattleStatusResponse battle_r =
          // JsonUtils.readFromSocket(manager.in, BattleStatusResponse.class);

          Thread.sleep(1000);
        }
        return null;
      }
    };
    worker.execute();

  }


}
