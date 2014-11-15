package ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import javax.imageio.ImageIO;


public class IconButton implements Drawable {

  private double x, y, height, width;
  private File file;
  private String text;
  public int fontSize = 24;
  public Font font = new Font(Font.SERIF, Font.BOLD, fontSize);
  private boolean hovered = false;
  Color baseColor = Color.white;
  Color hoverColor = Color.lightGray;
  private double xpadding = 16;
  private BufferedImage image;
  private Consumer<Void> callback;

  public IconButton(double x, double y, File file, Consumer<Void> callback) {
    this.x = x;
    this.y = y;
    this.file = file;
    this.callback = callback;
    try {
      this.image = resize(ImageIO.read(file), 64, 64);
      this.height = image.getHeight();
      this.width = image.getWidth();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  @Override
  public void draw(Graphics2D g) {
    if (image == null)
      return;
    Composite comp = g.getComposite();
    if (hovered) {
      g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
    }

    g.drawImage(image, null, (int) x, (int) y);

    g.setComposite(comp);

  }

  @Override
  public boolean contains(Point point) {
    return point.x > x && point.x < x + width && point.y > y && point.y < y + height;
  }

  @Override
  public void onHover() {
    hovered = true;


  }

  @Override
  public void onUnHover() {
    hovered = false;
  }

  @Override
  public void onClick() {
    if (callback != null) {
      callback.accept(null);
    }

  }

  public void setOnClickAction(Consumer<Void> callback) {
    this.callback = callback;
  }

  public static BufferedImage resize(BufferedImage img, int newW, int newH) {
    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();

    return dimg;
  }

}
