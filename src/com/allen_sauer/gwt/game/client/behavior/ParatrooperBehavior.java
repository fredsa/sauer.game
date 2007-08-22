package com.allen_sauer.gwt.game.client.behavior;

import com.google.gwt.user.client.Random;

import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class ParatrooperBehavior extends Behavior {
  private static final int MAX_WALK_FRAMES = 50;
  private int walkFrames;

  public ParatrooperBehavior(Sprite sprite) {
    super(sprite);
  }

  public void doFirstFrame() {
    setXMax(Engine.getClientWidth() - getSprite().getFrameInfo().frameWidth);
    setYMax(Engine.getClientHeight() - getSprite().getFrameInfo().frameHeight);
    walkFrames = 0;
    setX(Random.nextInt(getXMax()));
    setY(-getSprite().getFrameInfo().frameHeight);

    setSpeed(Random.nextInt(6) - 3, Random.nextInt(5) + 3);
    super.doFirstFrame();
  }

  public boolean doFrame() {
    boolean again = true;

    if (getX() == getXMin()) {
      setXSpeed(Random.nextInt(5) + 4);
    } else if (getX() == getXMax()) {
      setXSpeed(-Random.nextInt(5) - 4);
    }

    if (getY() == getYMax()) {
      //      setYSpeed(0);
      if (++walkFrames == MAX_WALK_FRAMES) {
        again = false;
      }
    }

    return again && super.doFrame();
  }
}
