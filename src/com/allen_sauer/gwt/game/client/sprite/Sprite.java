/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.sprite;

import com.allen_sauer.gwt.game.client.FrameListener;
import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.Behavior;
import com.allen_sauer.gwt.game.client.sprite.frame.FrameInfo;
import com.allen_sauer.gwt.game.client.ui.util.DOMUtil;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;

public class Sprite extends Composite implements FrameListener {
  public final BoundingBoxInfo boundingBoxInfo;
  private Behavior behavior;
  private FrameInfo frameInfo;
  private final Game game;
  private Image image;
  private boolean markedForRemoval = false;
  private final AbsolutePanel panel = new AbsolutePanel();
  private int poolIndex;
  private SpritePool spritePool;
  private double x;
  private double y;

  public Sprite(Game game, BoundingBoxInfo boundingBoxInfo) {
    this.game = game;
    this.boundingBoxInfo = boundingBoxInfo;

    initWidget(panel);

    if (Game.DEBUG) {
      SimplePanel boundingBoxPanel = new SimplePanel();
      boundingBoxPanel.addStyleName("bounding-box");
      boundingBoxPanel.setPixelSize(boundingBoxInfo.width - 2, boundingBoxInfo.height - 2);
      panel.add(boundingBoxPanel, boundingBoxInfo.offsetLeft, boundingBoxInfo.offsetTop);
      DOM.setStyleAttribute(boundingBoxPanel.getElement(), "border", "1px solid green");
    }

    panel.addStyleName("sprite");
    game.getPlayfield().add(this, -500, -500);
  }

  public void doFirstFrame() {
    frameInfo.doFirstFrame();
    behavior.doFirstFrame();
  }

  public boolean doFrame(double millis) {
    if (markedForRemoval) {
      markedForRemoval = false;
      return false;
    }
    DOMUtil.fastSetElementPosition(getElement(), x, y);
    boolean again = frameInfo.doFrame(millis);
    again &= behavior.doFrame(millis);
    return again;
  }

  public void doLastFrame() {
    DOMUtil.fastSetElementPosition(getElement(), -500, -500);
    spritePool.destroy(this);
    frameInfo.doLastFrame();
    behavior.doLastFrame();
  }

  public FrameInfo getFrameInfo() {
    return frameInfo;
  }

  public Game getGame() {
    return game;
  }

  public int getPoolIndex() {
    return poolIndex;
  }

  public SpritePool getSpritePool() {
    return spritePool;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public void markForRemoval() {
    markedForRemoval = true;
  }

  public void setBehavior(Behavior behavior) {
    this.behavior = behavior;
  }

  public void setDirection(int direction) {
  }

  public void setFrameInfo(FrameInfo frameInfo) {
    this.frameInfo = frameInfo;
    image = new Image(frameInfo.spriteUrl);
    panel.add(image, -500, -500);

    image.setPixelSize(frameInfo.frameWidth * frameInfo.horizontalFrames, frameInfo.frameHeight
        * frameInfo.verticalFrames);
    setPixelSize(frameInfo.frameWidth, frameInfo.frameHeight);
    setPixelSize(frameInfo.frameWidth, frameInfo.frameHeight);
  }

  public void setHidden(boolean hidden) {
    DOM.setStyleAttribute(getElement(), "visibility", hidden ? "hidden" : "");
  }

  public void setImagePosition(int x, int y) {
    DOMUtil.fastSetElementPosition(image.getElement(), x, y);
  }

  public void setPoolIndex(int poolIndex) {
    this.poolIndex = poolIndex;
  }

  public final void setPosition(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public void setSpritePool(SpritePool spritePool) {
    this.spritePool = spritePool;
  }
}
