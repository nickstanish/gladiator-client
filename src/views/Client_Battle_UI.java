package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import main.CharacterClass;
import main.Views;
import request.BattleStatusRequest;
import responses.BattleStatusResponse;
import ui.Drawable;
import ui.IconButton;
import utils.JsonUtils;
import utils.ViewManager;

import com.alee.laf.WebLookAndFeel;


public class Client_Battle_UI extends JPanel implements MouseListener, MouseMotionListener {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  public ViewManager manager;
  public SwingWorker<Void, Void> worker;
  private static final int PADDING = 15;
  public List<Drawable> drawables;
  public BattleStatusResponse battle_r;

  private Timer paintTimer = new Timer((int) (1 / 40.0 * 1000), event -> timerEvent(event));

  private void timerEvent(ActionEvent e) {
    repaint();
  }

  public ArrayList<String> descriptions;

  public Client_Battle_UI(ViewManager manager) {
    this.manager = manager;
    initBattle();
    descriptions = new ArrayList<String>();
    drawables = new ArrayList<Drawable>();
    battle_r = new BattleStatusResponse(false, false);
    battle_r = JsonUtils.readFromSocket(manager.in, BattleStatusResponse.class);

    if (manager.charType == CharacterClass.warrior) {
      drawables
          .add(new IconButton(50, 320, new File("media/icons/bloody-stash.png"), a -> skill1()));
      descriptions.add("Bloody Slash");
      drawables.add(new IconButton(150, 320, new File("media/icons/shield-reflect.png"),
          a -> skill2()));
      descriptions.add("Reflect DMG");
      drawables.add(new IconButton(250, 320, new File("media/icons/round-shield.png"),
          a -> skill3()));
      descriptions.add("Block Blow");
      drawables
          .add(new IconButton(350, 320, new File("media/icons/mailed-fist.png"), a -> skill4()));
      descriptions.add("Heavy Punch");
    } else if (manager.charType == CharacterClass.theif) {
      drawables.add(new IconButton(50, 320, new File("media/icons/machete.png"), a -> skill1()));
      descriptions.add("Poison Stab");
      drawables
          .add(new IconButton(150, 320, new File("media/icons/heavy-arrow.png"), a -> skill2()));
      descriptions.add("Arrow Rain");
      drawables.add(new IconButton(250, 320, new File("media/icons/flash-grenade.png"),
          a -> skill3()));
      descriptions.add("Smoke Bomb");
      drawables.add(new IconButton(350, 320, new File("media/icons/backstab.png"), a -> skill4()));
      descriptions.add("Backstab");
    } else if (manager.charType == CharacterClass.mage) {
      drawables
          .add(new IconButton(50, 320, new File("media/icons/heart-bottle.png"), a -> skill1()));
      descriptions.add("Health Vial");
      drawables.add(new IconButton(150, 320, new File("media/icons/smoking-finger.png"),
          a -> skill2()));
      descriptions.add("Firebolt");
      drawables.add(new IconButton(250, 320, new File("media/icons/embrassed-energy.png"),
          a -> skill3()));
      descriptions.add("Focus Power");
      drawables.add(new IconButton(350, 320, new File("media/icons/broken-bottle.png"),
          a -> skill4()));
      descriptions.add("Throw Vials");
    }
    addMouseListener(this);
    addMouseMotionListener(this);
    paintTimer.start();
    waitForTurn();
    // JsonUtils.writeToSocket(manager.out, CharacterInfo.makeMage());
  }

  private Drawable skill1() {
    System.out.println("skill 1 pressed");

    if (battle_r.your_turn && battle_r.game_ready) {
      JsonUtils.toJsonString(new BattleStatusRequest());

      waitForTurn();
    }
    return null;
  }

  private Drawable skill2() {
    System.out.println("skill 2 pressed");

    if (battle_r.your_turn && battle_r.game_ready) {
      JsonUtils.toJsonString(new BattleStatusRequest());

      waitForTurn();
    }
    return null;
  }

  private Drawable skill3() {
    System.out.println("skill 3 pressed");

    if (battle_r.your_turn && battle_r.game_ready) {
      JsonUtils.toJsonString(new BattleStatusRequest());

      waitForTurn();
    }
    return null;
  }

  private Drawable skill4() {
    System.out.println("skill 4 pressed");

    if (battle_r.your_turn && battle_r.game_ready) {
      JsonUtils.toJsonString(new BattleStatusRequest());

      waitForTurn();
    }
    return null;
  }

  public void initBattle() {

    JButton back_button = new JButton();
    back_button.setText("Back to Menu");

    back_button.addActionListener(event -> backToMenu(event));
    // add(back_button);

  }

  @Override
  public void paintComponent(Graphics g1) {
    super.paintComponent(g1);
    Graphics2D g = (Graphics2D) g1;
    g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
        RenderingHints.VALUE_COLOR_RENDER_QUALITY);
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, getWidth(), getHeight());

    g.setColor(Color.lightGray);

    g.fillRect(PADDING, PADDING, getWidth() - 2 * PADDING, getHeight() - 2 * PADDING);
    g.setColor(Color.BLACK);

    g.drawString(descriptions.get(0), 50, 400);
    g.drawString(descriptions.get(1), 150, 400);
    g.drawString(descriptions.get(2), 250, 400);
    g.drawString(descriptions.get(3), 350, 400);

    for (Drawable drawable : drawables) {
      drawable.draw(g);
    }

    g.drawString("Health: ", 450, 345);
    g.setColor(Color.WHITE);
    g.fillRect(448, 358, 758 - 450, 2 * 9);
    g.setColor(Color.RED);
    g.fillRect(450, 360, 760 - 450, 2 * 10);


    if (battle_r != null && battle_r.isValid() && battle_r.game_ready && battle_r.your_turn) {
      g.drawString("Waiting for your Turn!", PADDING, PADDING);
    }

  }

  private void backToMenu(ActionEvent event) {
    manager.switchView(Views.main_menu);
  }

  public void waitForTurn() {
    worker = new SwingWorker<Void, Void>() {
      @Override
      protected Void doInBackground() throws Exception {
        // Simulate doing something useful.
        for (int i = 0; i <= 1000; i++) {

          JsonUtils.toJsonString(new BattleStatusRequest());

          BattleStatusResponse battle_r =
              JsonUtils.readFromSocket(manager.in, BattleStatusResponse.class);

          if (battle_r.game_ready && battle_r.your_turn) {
            worker.cancel(true);
          }

          Thread.sleep(1000);
        }
        return null;
      }
    };
    worker.execute();

  }

  public static void main(String[] args) {
    WebLookAndFeel.install();
    Client_Battle_UI ui = new Client_Battle_UI(null);
    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.getContentPane().add(ui);
    window.pack();
    window.setVisible(true);
    window.setSize(600, 600);

  }

  @Override
  public void mouseDragged(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseMoved(MouseEvent e) {
    for (Drawable drawable : drawables) {
      if (drawable.contains(e.getPoint())) {
        drawable.onHover();
      } else {
        drawable.onUnHover();
      }
    }

  }

  @Override
  public void mouseClicked(MouseEvent e) {
    for (Drawable drawable : drawables) {
      if (drawable.contains(e.getPoint())) {
        drawable.onClick();
      }
    }
    System.out.print(e.getPoint().x + ", " + e.getPoint().y + "\n");
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub

  }

}
