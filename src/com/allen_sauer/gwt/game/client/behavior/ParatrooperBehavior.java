package com.allen_sauer.gwt.game.client.behavior;

import com.google.gwt.user.client.Random;

import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class ParatrooperBehavior implements Behavior {
  private static final int MAX_WALK_FRAMES = 30;
  private final Sprite sprite;
  private int walkFrames;
  private int xMax;
  private int xSpeed;
  private int yMax;
  private int ySpeed;

  public ParatrooperBehavior(Sprite sprite) {
    this.sprite = sprite;
  }

  public void doFirstFrame() {
    xMax = Engine.getClientWidth() - sprite.getFrameWidth();
    yMax = Engine.getClientHeight() - sprite.getFrameHeight();
    walkFrames = 0;
    sprite.setXY(Random.nextInt(xMax), -sprite.getFrameHeight());

    xSpeed = Random.nextInt(6) - 3;
    ySpeed = Random.nextInt(5) + 3;
  }

  public void doFrame() {
    int x = sprite.getX() + xSpeed;
    int y = sprite.getY() + ySpeed;

    if (x < 0) {
      x = 0;
      xSpeed = Random.nextInt(5) + 4;
    } else if (x > xMax) {
      x = xMax;
      xSpeed = -Random.nextInt(5) - 4;
    }

    if (y < 0) {
      // y = 0;
      // ySpeed = Random.nextInt(3) + 2;
    } else if (y > yMax) {
      y = yMax;
      ySpeed = 0;
    } else if (y == yMax) {
      if (++walkFrames == MAX_WALK_FRAMES) {
        sprite.removeSelf();
      }
    }

    sprite.setXY(x, y);
  }

  public void doLastFrame() {
  }
}
