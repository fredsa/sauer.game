/*
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;

public class Sprite extends Composite {
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
    setPosition();
  }

  public void doMove() {
    if (++frame >= frames * 5) {
      frame = 0;
    }
    setFrame(frame / 5);

    x += xSpeed;
    int xMax = game.getPlayfieldWidth() - frameWidth;
    if (x < 0) {
      x = 0;
      xSpeed = Random.nextInt(5) + 4;
    } else if (x > xMax) {
      x = xMax;
      xSpeed = -Random.nextInt(5) - 4;
    }

    y += ySpeed;
    int yMax = game.getPlayfieldHeight() - frameHeight;
    if (y < 0) {
      y = 0;
      ySpeed = Random.nextInt(5) + 4;
    } else if (y > yMax) {
      y = yMax;
      ySpeed = -Random.nextInt(5) - 4;
    }

    setPosition();
  }

  public void setFrame(int frame) {
    DOM.setStyleAttribute(image.getElement(), "left", -frame * frameWidth + "px");
  }

  private void setPosition() {
    DOM.setStyleAttribute(getElement(), "left", x + "px");
    DOM.setStyleAttribute(getElement(), "top", y + "px");
  }
}
