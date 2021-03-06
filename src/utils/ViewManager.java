package utils;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;

import main.CharacterClass;
import main.Views;
import views.Client_Battle_UI;
import views.Client_Battle_Waiting_UI;
import views.Client_Character_Select_UI;
import views.Client_Customize_UI;
import views.Client_Leaderboards_UI;
import views.Client_Login_UI;
import views.Client_MainMenu_UI;

public class ViewManager extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  public Container contentPane;
  private static final String LOGIN_SCREEN = "Login";
  private static final String MAINMENU_SCREEN = "MainMenu";
  private static final String BATTLE_WAIT_SCREEN = "BattleWaiting";
  private static final String CUSTOMIZE_SCREEN = "Customize";
  private static final String LEADERBOARDS_SCREEN = "Leaderboards";
  private static final String BATTLE_SCREEN = "Battle";
  private static final String CHARACTER_SELECT_SCREEN = "SelectCharacter";
  public CharacterClass charType;
  public Socket socket;
  public BufferedReader in;
  public PrintWriter out;

  public ViewManager() {
    contentPane = getContentPane();
    contentPane.setLayout(new CardLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(400, 100));
    contentPane.add(new Client_Login_UI(this), MAINMENU_SCREEN);
  }

  public void switchView(Views view) {
    CardLayout cl = (CardLayout) (contentPane.getLayout());
    switch (view) {
      case login:
        cl.show(contentPane, LOGIN_SCREEN);
        setTitle("Login/Create");
        break;
      case main_menu:
        contentPane.add(new Client_MainMenu_UI(this), MAINMENU_SCREEN);
        cl.show(contentPane, MAINMENU_SCREEN);
        setSize(400, 300);
        setTitle("Gladiator");
        break;
      case battle_wait_screen:
        contentPane.add(new Client_Battle_Waiting_UI(this), BATTLE_WAIT_SCREEN);
        cl.show(contentPane, BATTLE_WAIT_SCREEN);
        setTitle("Searching for Battle...");
        break;
      case customize:
        contentPane.add(new Client_Customize_UI(this), CUSTOMIZE_SCREEN);
        cl.show(contentPane, CUSTOMIZE_SCREEN);
        setSize(400, 300);
        setTitle("Gladiator Customization");
        break;
      case leaderboards:
        contentPane.add(new Client_Leaderboards_UI(this), LEADERBOARDS_SCREEN);
        cl.show(contentPane, LEADERBOARDS_SCREEN);
        setSize(400, 700);
        setTitle("Leaderboards");
        break;
      case battle:
        contentPane.add(new Client_Battle_UI(this), BATTLE_SCREEN);
        cl.show(contentPane, BATTLE_SCREEN);
        setSize(800, 450);
        setTitle("Fight to the Death!");
        break;
      case characterselect:
        contentPane.add(new Client_Character_Select_UI(this), CHARACTER_SELECT_SCREEN);
        cl.show(contentPane, CHARACTER_SELECT_SCREEN);
        setSize(400, 300);
        setTitle("Choose your Gladiator!");
        break;
    }
  }
}
