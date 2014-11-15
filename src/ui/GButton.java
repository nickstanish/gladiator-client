package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

public class GButton implements Drawable {

  private double x, y, height, width;
  private String text;
  public int fontSize = 24;
  public Font font = new Font(Font.SERIF, Font.BOLD, fontSize);
  private boolean hovered = false;
  Color baseColor = Color.white;
  Color hoverColor = Color.lightGray;
  private double xpadding = 16;

  public GButton(double x, double y, String text) {
    this.x = x;
    this.y = y;
    this.text = text;
  }

  @Override
  public void draw(Graphics2D g) {
    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
        RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT);
    g.setFont(font);

    g.setColor(Color.green);
    FontMetrics fm = g.getFontMetrics();
    Rectangle2D rect = fm.getStringBounds(text, g);
    double height = fm.getAscent() + fm.getDescent();
    Rectangle2D paddedArea =
        new Rectangle2D.Double(x - xpadding / 2, (y + height) + fm.getDescent(), rect.getWidth()
            + xpadding, height + fm.getDescent());
    Rectangle2D area = new Rectangle2D.Double(x, (y + height), rect.getWidth(), rect.getHeight());
    if (hovered) {
      g.setColor(hoverColor);

    }
    g.fill(paddedArea);
    g.setColor(baseColor);
    g.drawString(text, (int) x, (int) (y + 2 * height));

  }

  @Override
  public boolean contains(Point point) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void onHover() {
    // TODO Auto-generated method stub

  }

  @Override
  public void onUnHover() {
    // TODO Auto-generated method stub

  }

  @Override
  public void onClick() {
    // TODO Auto-generated method stub

  }

}
