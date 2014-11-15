package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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

import main.CharacterClass;
import main.CharacterInfo;
import main.Views;
import net.miginfocom.swing.MigLayout;
import request.BattleStatusRequest;
import request.StartBattleRequest;
import responses.BattleStatusResponse;
import ui.Drawable;
import ui.IconButton;
import utils.JsonUtils;
import utils.ViewManager;


public class Client_Character_Select_UI extends JPanel implements MouseListener,
    MouseMotionListener {

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


  public Client_Character_Select_UI(ViewManager manager) {
    this.manager = manager;
    initCustomize();
    drawables = new ArrayList<Drawable>();
    drawables
        .add(new IconButton(75, 50, new File("media/icons/spartan.png"), a -> warriorPressed()));
    drawables.add(new IconButton(175, 50, new File("media/icons/hood.png"), a -> theifPressed()));
    drawables.add(new IconButton(275, 50, new File("media/icons/pointy-hat.png"),
        a -> magePressed()));

    /*
     * drawables.add(new IconButton(5, 150, new File(""), a -> nothing())); drawables.add(new
     * IconButton(150, 150, new File(""), a -> nothing())); drawables.add(new IconButton(250, 150,
     * new File(""), a -> nothing())); drawables.add(new IconButton(350, 150, new File(""), a ->
     * nothing()));
     */
    addMouseListener(this);
    addMouseMotionListener(this);
    paintTimer.start();

  }

  private Object nothing() {
    // TODO Auto-generated method stub
    return null;
  }


  private void magePressed() {
    BattleStatusResponse battle_r =
        JsonUtils.makeRequest(manager.out, new StartBattleRequest(CharacterInfo.makeMage()),
            manager.in, BattleStatusResponse.class);
    manager.charType = CharacterClass.mage;
    manager.switchView(Views.battle);
  }

  private void theifPressed() {

    BattleStatusResponse battle_r =
        JsonUtils.makeRequest(manager.out, new StartBattleRequest(CharacterInfo.makeThief()),
            manager.in, BattleStatusResponse.class);

    manager.charType = CharacterClass.theif;
    manager.switchView(Views.battle);
  }


  private void warriorPressed() {

    BattleStatusResponse battle_r =
        JsonUtils.makeRequest(manager.out, new StartBattleRequest(CharacterInfo.makeWarrior()),
            manager.in, BattleStatusResponse.class);
    manager.charType = CharacterClass.warrior;
    manager.switchView(Views.battle);
  }

  public void initCustomize() {

    setLayout(new MigLayout("Fill"));

    JButton back_button = new JButton();
    back_button.setText("Back to Menu");

    back_button.addActionListener(event -> backToMenu(event));
    // add(back_button, "North");

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
    g.drawString("Warrior", 85, 125);
    g.drawString("Thief", 190, 125);
    g.drawString("Mage", 290, 125);


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
        if (drawable.contains(new Point(70, 50))) {



        }
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
