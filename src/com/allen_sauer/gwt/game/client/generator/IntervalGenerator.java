package com.allen_sauer.gwt.game.client.generator;

import com.google.gwt.user.client.Random;

import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;

public class IntervalGenerator implements Generator {
  private double probability;
  private final SpritePool spritePool;

  public IntervalGenerator(SpritePool spritePool, double probability) {
    this.spritePool = spritePool;
    this.probability = probability;
    Engine.addFrameListener(this);
  }

  public void doFirstFrame() {
  }

  public void doFrame() {
    if (!spritePool.exhausted() && Random.nextDouble() < probability) {
      spritePool.create();
    }
  }

  public void doLastFrame() {
  }
}
