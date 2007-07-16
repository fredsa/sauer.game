/*
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client;

import com.google.gwt.user.client.Random;

public class ParatrooperBehavior implements Behavior {
  private final Sprite sprite;

  public ParatrooperBehavior(Sprite sprite) {
    this.sprite = sprite;
  }

  public void doFrame() {
    Game game = sprite.getGame();

    int x = sprite.getX();
    int xSpeed = sprite.getXSpeed();

    x += xSpeed;
    int xMax = game.getPlayfieldWidth() - sprite.getFrameWidth();
    if (x < 0) {
      x = 0;
      xSpeed = Random.nextInt(5) + 4;
    } else if (x > xMax) {
      x = xMax;
      xSpeed = -Random.nextInt(5) - 4;
    }
    sprite.setX(x);
    sprite.setXSpeed(xSpeed);

    int y = sprite.getY();
    int ySpeed = sprite.getYSpeed();

    y += ySpeed;
    int yMax = game.getPlayfieldHeight() - sprite.getFrameHeight();
    if (y < 0) {
      y = 0;
      ySpeed = Random.nextInt(3) + 2;
    } else if (y > yMax) {
      y = yMax;
      ySpeed = 0;
    }
    sprite.setY(y);
    sprite.setYSpeed(ySpeed);
  }
}
