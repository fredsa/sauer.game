/*
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;

public class Sprite extends Composite {
  private Behavior behavior;
  private int frame;
  private int frameHeight;
  private int frames;
  private int frameWidth;
  private Game game;
  private Image image;
  private AbsolutePanel panel = new AbsolutePanel();
  private int x;
  private int xSpeed = 4;
  private int y;
  private int ySpeed = 2;

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
    setDomPosition();
  }

  public void doFrame() {
    if (++frame >= frames * 5) {
      frame = 0;
    }
    setFrame(frame / 5);

    behavior.doFrame();
    setDomPosition();
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

  public int getXSpeed() {
    return xSpeed;
  }

  public int getY() {
    return y;
  }

  public int getYSpeed() {
    return ySpeed;
  }

  public void setBehavior(Behavior behavior) {
    this.behavior = behavior;
  }

  public void setFrame(int frame) {
    DOM.setStyleAttribute(image.getElement(), "left", -frame * frameWidth + "px");
  }

  public final void setX(int x) {
    this.x = x;
  }

  public void setXSpeed(int speed) {
    xSpeed = speed;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setYSpeed(int speed) {
    ySpeed = speed;
  }

  private void setDomPosition() {
    DOM.setStyleAttribute(getElement(), "left", x + "px");
    DOM.setStyleAttribute(getElement(), "top", y + "px");
  }
}
