package ui;

import java.awt.Graphics2D;
import java.awt.Point;

public interface Drawable {
  public void draw(Graphics2D g);

  public boolean contains(Point point);

  public void onHover();

  public void onUnHover();

  public void onClick();

}
