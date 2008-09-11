/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.behavior;

import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class SpiralBehavior extends Behavior {
  private static final int MAX_WALK_MILLIS = 10000;
  private static final int MILLIS_PER_STEP = 50;
  private static final double[] pathX;
  private static final double[] pathY;
  private static final int STEPS = 52;

  static {
    pathX = new double[STEPS];
    pathY = new double[STEPS];
    for (int i = 0; i < STEPS; i++) {
      double degrees = 2 * Math.PI * i / STEPS;
      pathX[i] = Math.sin(degrees);
      pathY[i] = Math.cos(degrees);
    }
  }

  private double sizeFactor;
  private int walkMillis;

  public SpiralBehavior(Sprite sprite) {
    super(sprite);
  }

  @Override
  public void doFirstFrame() {
    clientResized();
    walkMillis = 0;
    setX(getXMax() / 2);
    setY(-getSprite().getFrameInfo().frameHeight);

    setSpeed(-0.05, 0.05);
    super.doFirstFrame();
    sizeFactor = Math.min(getXMax(), getYMax()) / 4;
  }

  @Override
  public boolean doFrame(double millis) {
    boolean again = true;

    walkMillis += millis;
    int step = walkMillis / MILLIS_PER_STEP % STEPS;
    setX(getXMax() / 2 + pathX[step] * sizeFactor);
    setY(getYMax() / 2 + pathY[step] * sizeFactor);

    if (walkMillis >= MAX_WALK_MILLIS) {
      again = false;
    }

    return again && super.doFrame(millis);
  }
}
