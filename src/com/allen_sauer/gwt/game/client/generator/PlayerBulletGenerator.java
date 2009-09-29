/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.generator;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;

public class PlayerBulletGenerator implements Generator {
  private static final double MILLIS_BETWEEN_BULLETS = 150;
  private final Game game;
  private boolean markedForRemoval = false;
  private int milliCounter;
  private final SpritePool spritePool;

  public PlayerBulletGenerator(Game game, SpritePool spritePool) {
    this.game = game;
    this.spritePool = spritePool;
    spritePool.getFrameListenerCollection().addFrameListener(this);
  }

  public void doFirstFrame() {
    milliCounter = 0;
  }

  public boolean doFrame(double millis) {
    milliCounter += millis;
    if (milliCounter > MILLIS_BETWEEN_BULLETS && !spritePool.exhausted()
        && (game.input.isKeyDown(' ') || game.input.getClick())) {
      spritePool.create();
      milliCounter = 0;
    }
    if (markedForRemoval) {
      markedForRemoval = false;
      return false;
    }
    return true;
  }

  public void doLastFrame() {
  }

  public void markForRemoval() {
    markedForRemoval = true;
  }
}
