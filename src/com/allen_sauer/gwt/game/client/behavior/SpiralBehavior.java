/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.behavior;

import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.log.client.Log;

public class SpiralBehavior extends Behavior {
  private static final int INITIAL_STEPS = 20;
  private static final int MAX_WALK_MILLIS = 12000;
  private static final int MILLIS_PER_STEP = 50;
  private static final double[] pathX;
  private static final double[] pathY;
  private static final int REPEAT_STEPS = 52;
  private static final double SPIRAL_SIZE_FACTOR = 0.25;

  static {
    pathX = new double[INITIAL_STEPS + REPEAT_STEPS];
    pathY = new double[INITIAL_STEPS + REPEAT_STEPS];
    try {
      pathX[0] = 0.1;
      pathY[0] = 0.1;
      for (int i = 0; i < REPEAT_STEPS; i++) {
        double degrees = 2 * Math.PI * i / REPEAT_STEPS;
        pathX[INITIAL_STEPS + i] = Math.sin(degrees) * SPIRAL_SIZE_FACTOR + 0.5;
        pathY[INITIAL_STEPS + i] = Math.cos(degrees) * SPIRAL_SIZE_FACTOR + 0.3;
      }
      for (int i = 1; i < INITIAL_STEPS; i++) {
        pathX[i] = pathX[0] + (pathX[INITIAL_STEPS] - pathX[0]) * i / INITIAL_STEPS;
        pathY[i] = pathY[0] + (pathY[INITIAL_STEPS] - pathY[0]) * i / INITIAL_STEPS;
      }
    } catch (Exception e) {
      Log.fatal("oh no", e);
    }
  }

  private int walkMillis;

  public SpiralBehavior(Sprite sprite) {
    super(sprite);
  }

  @Override
  public void doFirstFrame() {
    clientResized();
    walkMillis = 0;
    setX(pathX[0] * getXMax());
    setY(pathY[0] * getYMax());

    //    setSpeed(-0.05, 0.05);
    super.doFirstFrame();
  }

  @Override
  public boolean doFrame(double millis) {
    boolean again = true;

    walkMillis += millis;
    int step = walkMillis / MILLIS_PER_STEP % REPEAT_STEPS + INITIAL_STEPS;
    setX(pathX[step] * getXMax());
    setY(pathY[step] * getYMax());

    if (walkMillis >= MAX_WALK_MILLIS) {
      again = false;
    }

    return again && super.doFrame(millis);
  }
}
