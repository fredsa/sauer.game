package com.allen_sauer.gwt.game.client;

import com.google.gwt.user.client.Random;

public class IntervalGenerator implements Generator {
  private double probability;
  private final SpritePool spritePool;

  public IntervalGenerator(SpritePool spritePool, double probability) {
    this.spritePool = spritePool;
    this.probability = probability;
  }

  public void doFrame() {
    if (spritePool.size() < spritePool.getMaxSize() && Random.nextDouble() < probability) {
      spritePool.create();
    }
  }
}
