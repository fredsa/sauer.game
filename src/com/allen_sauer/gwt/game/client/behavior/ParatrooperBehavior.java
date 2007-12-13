/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.behavior;

import com.google.gwt.user.client.Random;

import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class ParatrooperBehavior extends Behavior {
  private static final int MAX_WALK_FRAMES = 50;
  private int walkFrames;

  public ParatrooperBehavior(Sprite sprite) {
    super(sprite);
  }

  @Override
  public void doFirstFrame() {
    clientResized();
    walkFrames = 0;
    setX(Random.nextInt(getXMax()));
    setY(-getSprite().getFrameInfo().frameHeight);

    setSpeed((Random.nextInt(2) + 1) * randomSign(), Random.nextInt(2) + 1);
    super.doFirstFrame();
  }

  @Override
  public boolean doFrame() {
    boolean again = true;

    if (getX() == getXMin()) {
      setXSpeed(Random.nextInt(5) + 4);
    } else if (getX() == getXMax()) {
      setXSpeed(-Random.nextInt(5) - 4);
    }

    if (getY() == getYMax()) {
      // setYSpeed(0);
      if (++walkFrames == MAX_WALK_FRAMES) {
        again = false;
      }
    }

    return again && super.doFrame();
  }

  private int randomSign() {
    return Random.nextInt(1) == 0 ? -1 : 1;
  }
}
