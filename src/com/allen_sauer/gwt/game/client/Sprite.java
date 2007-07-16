package com.allen_sauer.gwt.game.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class Sprite extends Composite {
  private Behavior behavior;
  private int frame;
  private int frameHeight;
  private int frames;
  private int frameWidth;
  private Game game;
  private Image image;
  private AbsolutePanel panel = new AbsolutePanel();
  private SpritePool spritePool;
  private int x;
  private int y;

  public Sprite(Game game, String url, int frames, int frameWidth, int frameHeight) {
    this.game = game;
    this.frames = frames;
    this.frameWidth = frameWidth;
    this.frameHeight = frameHeight;
    initWidget(panel);
    image = new Image(url);
    panel.add(image, 0, 0);

    image.setPixelSize(frameWidth * frames, frameHeight);
    panel.setPixelSize(frameWidth, frameHeight);

    DOM.setStyleAttribute(getElement(), "position", "absolute");
  }

  public void deinit() {
    RootPanel.get().remove(this);
  }

  public void doFrame() {
    if (++frame >= frames * 5) {
      frame = 0;
    }
    setFrame(frame / 5);

    behavior.doFrame();
  }

  public int getFrameHeight() {
    return frameHeight;
  }

  public int getFrameWidth() {
    return frameWidth;
  }

  public Game getGame() {
    return game;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void init() {
    RootPanel.get().add(this);
    behavior.init();
  }

  public void removeSelf() {
    spritePool.destroy(this);
  }

  public void setBehavior(Behavior behavior) {
    this.behavior = behavior;
  }

  public void setFrame(int frame) {
    DOM.setStyleAttribute(image.getElement(), "left", -frame * frameWidth + "px");
  }

  public void setSpritePool(SpritePool spritePool) {
    this.spritePool = spritePool;
  }

  public final void setX(int x) {
    this.x = x;
    DOM.setStyleAttribute(getElement(), "left", x + "px");
  }

  public void setY(int y) {
    DOM.setStyleAttribute(getElement(), "top", y + "px");
    this.y = y;
  }
}
