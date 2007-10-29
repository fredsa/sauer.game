package com.allen_sauer.gwt.game.client.behavior;

import com.allen_sauer.gwt.game.client.engine.FrameListener;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.ui.util.Direction;
import com.allen_sauer.gwt.game.client.ui.util.MathUtil;

public abstract class Behavior implements FrameListener {
  private int direction;
  private final Sprite sprite;
  private int x;
  private int xMax;
  private int xMin = 0;
  private int xSpeed;
  private int y;
  private int yMax;
  private int yMin = 0;
  private int ySpeed;

  public Behavior(Sprite sprite) {
    this.sprite = sprite;
    xMax = sprite.getGame().playfield.getOffsetWidth();
    yMax = sprite.getGame().playfield.getOffsetHeight();
  }

  public void doFirstFrame() {
    sprite.setPosition(x, y);
    updateDirection();
  }

  public boolean doFrame() {
    x = Math.max(Math.min(x + xSpeed, xMax), xMin);
    y = Math.max(Math.min(y + ySpeed, yMax), yMin);

    sprite.setPosition(x, y);
    updateDirection();
    return true;
  }

  public void doLastFrame() {
  }

  public Sprite getSprite() {
    return sprite;
  }

  public int getX() {
    return x;
  }

  public int getXMax() {
    return xMax;
  }

  public int getXMin() {
    return xMin;
  }

  public int getXSpeed() {
    return xSpeed;
  }

  public int getY() {
    return y;
  }

  public int getYMax() {
    return yMax;
  }

  public int getYMin() {
    return yMin;
  }

  public int getYSpeed() {
    return ySpeed;
  }

  public void setSpeed(int xSpeed, int ySpeed) {
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
    updateDirection();
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setXMax(int max) {
    xMax = max;
  }

  public void setXMin(int min) {
    xMin = min;
  }

  public void setXSpeed(int xSpeed) {
    this.xSpeed = xSpeed;
    updateDirection();
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setYMax(int max) {
    yMax = max;
  }

  public void setYMin(int min) {
    yMin = min;
  }

  public void setYSpeed(int ySpeed) {
    this.ySpeed = ySpeed;
    updateDirection();
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
