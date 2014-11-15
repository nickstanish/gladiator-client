package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
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
  public BufferedImage warrior;
  public BufferedImage thief;
  public BufferedImage mage;
  public BufferedImage hero;
  public Random gen = new Random();
  public int percent_health_foe;
  public int percent_health_you;

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
    // battle_r = new BattleStatusResponse(false, false);


    try {
      warrior = ImageIO.read(new File("media/icons/Warrior.png"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    try {
      mage = ImageIO.read(new File("media/icons/mage.png"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    try {
      hero = ImageIO.read(new File("media/icons/hero.png"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    try {
      thief = ImageIO.read(new File("media/icons/thief.png"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

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

  private void skill1() {
    System.out.println("skill 1 pr essed");

    if (battle_r != null && battle_r.your_turn && battle_r.game_ready) {

      if (manager.charType == CharacterClass.mage) {
        battle_r =
            JsonUtils.makeRequest(manager.out, new BattleStatusRequest(-5.0), manager.in,
                BattleStatusResponse.class);
      }
      if (manager.charType == CharacterClass.theif) {
        battle_r =
            JsonUtils.makeRequest(manager.out, new BattleStatusRequest(15.0), manager.in,
                BattleStatusResponse.class);
      }
      if (manager.charType == CharacterClass.warrior) {
        battle_r =
            JsonUtils.makeRequest(manager.out, new BattleStatusRequest(25.0), manager.in,
                BattleStatusResponse.class);
      }

      waitForTurn();
    }
  }

  private void skill2() {
    System.out.println("skill 2 pressed");

    if (battle_r != null && battle_r.your_turn && battle_r.game_ready) {

      if (manager.charType == CharacterClass.mage) {
        battle_r =
            JsonUtils.makeRequest(manager.out, new BattleStatusRequest(20.0), manager.in,
                BattleStatusResponse.class);
      }
      if (manager.charType == CharacterClass.theif) {
        battle_r =
            JsonUtils.makeRequest(manager.out, new BattleStatusRequest(15.0), manager.in,
                BattleStatusResponse.class);
      }
      if (manager.charType == CharacterClass.warrior) {
        battle_r =
            JsonUtils.makeRequest(manager.out, new BattleStatusRequest(12.0), manager.in,
                BattleStatusResponse.class);
      }

      waitForTurn();
    }
  }

  private void skill3() {
    System.out.println("skill 3 pressed");

    if (battle_r != null && battle_r.your_turn && battle_r.game_ready) {
      if (manager.charType == CharacterClass.mage) {
        battle_r =
            JsonUtils.makeRequest(manager.out, new BattleStatusRequest(-10.0), manager.in,
                BattleStatusResponse.class);
      }
      if (manager.charType == CharacterClass.theif) {
        battle_r =
            JsonUtils.makeRequest(manager.out, new BattleStatusRequest(-6.0), manager.in,
                BattleStatusResponse.class);
      }
      if (manager.charType == CharacterClass.warrior) {
        battle_r =
            JsonUtils.makeRequest(manager.out, new BattleStatusRequest(-5.0), manager.in,
                BattleStatusResponse.class);
      }
      waitForTurn();
    }
  }

  private void skill4() {
    System.out.println("skill 4 pressed");

    if (battle_r != null && battle_r.your_turn && battle_r.game_ready) {

      if (manager.charType == CharacterClass.mage) {
        battle_r =
            JsonUtils.makeRequest(manager.out, new BattleStatusRequest(15.0), manager.in,
                BattleStatusResponse.class);
      }
      if (manager.charType == CharacterClass.theif) {
        battle_r =
            JsonUtils.makeRequest(manager.out, new BattleStatusRequest(20.0), manager.in,
                BattleStatusResponse.class);
      }
      if (manager.charType == CharacterClass.warrior) {
        battle_r =
            JsonUtils.makeRequest(manager.out, new BattleStatusRequest(10.0), manager.in,
                BattleStatusResponse.class);
      }
      waitForTurn();
    }
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
    if (battle_r != null)
      g.drawString(battle_r.foe + "'s Health: ", 24, 28);
    g.setColor(Color.WHITE);
    g.fillRect(448, 358, 758 - 450, 2 * 9);
    g.setColor(Color.RED);
    if (battle_r != null) {
      percent_health_you = (int) ((760 - 450) * (battle_r.me_health / battle_r.me_maxhealth));
    } else
      percent_health_you = (int) (760 - 450);
    g.fillRect(450, 360, percent_health_you, 2 * 10);



    if (battle_r != null) {
      percent_health_foe = (int) ((760 - 450) * (battle_r.foe_health / battle_r.foe_maxhealth));
    } else
      percent_health_foe = (int) (760 - 450);



    if (battle_r != null && battle_r.foe_class == CharacterClass.warrior) {
      g.drawImage(warrior, null, 447, 79);
    }
    if (battle_r != null && battle_r.foe_class == CharacterClass.theif) {
      g.drawImage(thief, null, 477, 15);
    }
    if (battle_r != null && battle_r.foe_class == CharacterClass.mage) {
      g.drawImage(mage, null, 450, 145);
    }

    g.drawImage(hero, null, 40, 92);

    g.setColor(Color.WHITE);
    g.fillRect(30, 38, 758 - 450, 2 * 9);
    g.setColor(Color.RED);
    g.fillRect(32, 40, percent_health_foe, 2 * 10);

    if (battle_r != null && battle_r.isValid() && battle_r.game_ready && battle_r.your_turn) {

      g.drawString("Your Turn!", this.getSize().width / 2, this.getSize().height / 2);

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

          battle_r =
              JsonUtils.makeRequest(manager.out, new BattleStatusRequest(null), manager.in,
                  BattleStatusResponse.class);
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
