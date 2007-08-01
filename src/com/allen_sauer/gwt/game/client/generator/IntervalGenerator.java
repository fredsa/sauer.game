package com.allen_sauer.gwt.game.client.generator;

import com.google.gwt.user.client.Random;

import com.allen_sauer.gwt.game.client.sprite.SpritePool;

public class IntervalGenerator implements Generator {
  private double probability;
  private final SpritePool spritePool;

  public IntervalGenerator(SpritePool spritePool) {
    this(spritePool, 1);
  }

  public IntervalGenerator(SpritePool spritePool, double probability) {
    this.spritePool = spritePool;
    this.probability = probability;
    spritePool.getFrameListenerCollection().addFrameListener(this);
  }

  public void doFirstFrame() {
  }

  public FrameListenerRetention doFrame() {
    if (!spritePool.exhausted() && Random.nextDouble() < probability) {
      spritePool.create();
    }
    return LISTENER_CONTINUE;
  }

  public void doLastFrame() {
  }
}
