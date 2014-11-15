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

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import main.Views;
import net.miginfocom.swing.MigLayout;
import ui.Drawable;
import ui.IconButton;
import utils.ViewManager;


public class Client_Customize_UI extends JPanel implements MouseListener, MouseMotionListener {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  public ViewManager manager;
  private static final int PADDING = 15;
  public ArrayList<Drawable> drawables;


  private Timer paintTimer = new Timer((int) (1 / 40.0 * 1000), event -> timerEvent(event));

  private void timerEvent(ActionEvent e) {
    repaint();
  }


  public Client_Customize_UI(ViewManager manager) {
    this.manager = manager;
    initCustomize();
    drawables = new ArrayList<Drawable>();
    drawables.add(new IconButton(75, 100, new File("media/icons/spartan.png"),
        a -> warriorPressed()));
    drawables.add(new IconButton(175, 100, new File("media/icons/hood.png"), a -> theifPressed()));
    drawables.add(new IconButton(275, 100, new File("media/icons/pointy-hat.png"),
        a -> magePressed()));
    addMouseListener(this);
    addMouseMotionListener(this);
    paintTimer.start();

  }

  private void magePressed() {
    // JsonUtils.writeToSocket(manager.out, CharacterInfo.makeMage());
    // manager.switchView(Views.battle);
  }


  private void theifPressed() {
    // JsonUtils.writeToSocket(manager.out, CharacterInfo.makeThief());
    // manager.switchView(Views.battle);
  }


  private void warriorPressed() {
    // JsonUtils.writeToSocket(manager.out, CharacterInfo.makeWarrior());
    // manager.switchView(Views.battle);
  }


  public void initCustomize() {

    setLayout(new MigLayout("Fill"));

    JButton back_button = new JButton();
    back_button.setText("Back to Menu");

    back_button.addActionListener(event -> backToMenu(event));
    add(back_button, "North");

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
    // g.fillRect(PADDING, PADDING, getWidth() - 2 * PADDING, getHeight() - 2 * PADDING);


    g.setColor(Color.BLACK);

    g.drawString("Warrior", 85, 175);
    g.drawString("Thief", 190, 175);
    g.drawString("Mage", 290, 175);


    for (Drawable drawable : drawables) {
      drawable.draw(g);
    }
  }


  private void backToMenu(ActionEvent event) {
    manager.switchView(Views.main_menu);
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
