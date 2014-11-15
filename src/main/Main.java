package main;

import utils.ViewManager;

import com.alee.laf.WebLookAndFeel;


public class Main {

  public static void main(String[] args) {

    WebLookAndFeel.install();

    ViewManager window = new ViewManager();
    window.switchView(Views.login);
    window.pack();
    window.setVisible(true);
  }

}
