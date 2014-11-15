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

import main.Views;
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
  List<Drawable> drawables;

  private Timer paintTimer = new Timer((int) (1 / 40.0 * 1000), event -> timerEvent(event));

  private void timerEvent(ActionEvent e) {
    repaint();
  }

  public Client_Battle_UI(ViewManager manager) {
    this.manager = manager;
    initBattle();
    drawables = new ArrayList<Drawable>();
    drawables.add(new IconButton(50, 50, new File("media/icons/bowman.png")));
    drawables.add(new IconButton(150, 50, new File("media/icons/robe.png")));
    drawables.add(new IconButton(250, 50, new File("media/icons/cloak-dagger.png")));
    drawables.add(new IconButton(350, 50, new File("media/icons/battle-gear.png")));
    addMouseListener(this);
    addMouseMotionListener(this);
    paintTimer.start();

    // JsonUtils.writeToSocket(manager.out, CharacterInfo.makeMage());
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
    g.setColor(Color.white);
    g.fillRect(0, 0, getWidth(), getHeight());

    g.setColor(Color.lightGray);

    g.fillRect(PADDING, PADDING, getWidth() - 2 * PADDING, getHeight() - 2 * PADDING);
    g.setColor(Color.BLACK);
    g.drawString("BLAH", 20, 20);
    for (Drawable drawable : drawables) {
      drawable.draw(g);
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
        for (int i = 0; i <= 180; i++) {

          BattleStatusResponse battle_r =
              JsonUtils.readFromSocket(manager.in, BattleStatusResponse.class);

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
    // TODO Auto-generated method stub

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
