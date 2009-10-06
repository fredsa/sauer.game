/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.behavior;

import com.google.gwt.user.client.Random;

import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class FallBehavior extends Behavior {
  private static final int MAX_WALK_MILLIS = 1000;
  private int walkMillis;

  public FallBehavior(Sprite sprite) {
    super(sprite);
    setX(sprite.getX());
    setY(sprite.getY());
    setSpeed(0, .1);
  }

  // TODO: FIX THIS METHOD IS NOT CALLED
  @Override
  public void doFirstFrame() {
    clientResized();

    super.doFirstFrame();
  }

  @Override
  public boolean doFrame(double millis) {
    boolean again = true;

    if (getX() == getXMin()) {
      setXSpeed(Random.nextDouble() / 4 + 0.2);
    } else if (getX() == getXMax()) {
      setXSpeed(-Random.nextDouble() / 4 - 0.2);
    }

    return again && super.doFrame(millis);
  }
}
