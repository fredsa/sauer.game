/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.behavior;

import com.allen_sauer.gwt.game.client.FrameListener;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.ui.util.Direction;
import com.allen_sauer.gwt.game.client.ui.util.MathUtil;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;

public abstract class Behavior implements FrameListener {
  private double direction;
  private final Sprite sprite;
  private double x;
  private double xMax;
  private double xMin = 0;
  private double xSpeed;
  private double y;
  private double yMax;
  private double yMin = 0;
  private double ySpeed;

  public Behavior(Sprite sprite) {
    this.sprite = sprite;
    clientResized();
    Window.addResizeHandler(new ResizeHandler() {
      public void onResize(ResizeEvent event) {
        clientResized();
      }
    });
  }

  public void doFirstFrame() {
    sprite.setPosition(x, y);
    updateDirection();
  }

  public boolean doFrame(double millis) {
    x = Math.max(Math.min(x + millis * xSpeed, xMax), xMin);
    y = Math.max(Math.min(y + millis * ySpeed, yMax), yMin);

    sprite.setPosition(x, y);
    updateDirection();
    return true;
  }

  public void doLastFrame() {
  }

  public Sprite getSprite() {
    return sprite;
  }

  public double getX() {
    return x;
  }

  public double getXMax() {
    return xMax;
  }

  public double getXMin() {
    return xMin;
  }

  public double getXSpeed() {
    return xSpeed;
  }

  public double getY() {
    return y;
  }

  public double getYMax() {
    return yMax;
  }

  public double getYMin() {
    return yMin;
  }

  public double getYSpeed() {
    return ySpeed;
  }

  public void setSpeed(double xSpeed, double ySpeed) {
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
    updateDirection();
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setXMax(double max) {
    xMax = max;
  }

  public void setXMin(double min) {
    xMin = min;
  }

  public void setXSpeed(double xSpeed) {
    this.xSpeed = xSpeed;
    updateDirection();
  }

  public void setY(double y) {
    this.y = y;
  }

  public void setYMax(double max) {
    yMax = max;
  }

  public void setYMin(double min) {
    yMin = min;
  }

  public void setYSpeed(double ySpeed) {
    this.ySpeed = ySpeed;
    updateDirection();
  }

  protected void clientResized() {
    setXMax(getSprite().getGame().getPlayfieldWidth() - getSprite().getFrameInfo().frameWidth);
    setYMax(getSprite().getGame().getPlayfield().getOffsetHeight()
        - getSprite().getFrameInfo().frameHeight);
  }

  private void updateDirection() {
    int newDirection = (MathUtil.sign(xSpeed) == -1 ? Direction.WEST : Direction.EAST)
        | (MathUtil.sign(ySpeed) == -1 ? Direction.NORTH : Direction.SOUTH);
    if (newDirection != direction) {
      direction = newDirection;
      sprite.setDirection(newDirection);
    }
  }
}
