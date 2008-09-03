/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.generator;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;

public class PlayerBulletGenerator implements Generator {
  private static final int FRAMES_BETWEEN_BULLETS = 7;
  private int frameCounter;
  private final Game game;
  private boolean markedForRemoval = false;
  private final SpritePool spritePool;

  public PlayerBulletGenerator(Game game, SpritePool spritePool) {
    this.game = game;
    this.spritePool = spritePool;
    spritePool.getFrameListenerCollection().addFrameListener(this);
  }

  public void doFirstFrame() {
    frameCounter = 0;
  }

  public boolean doFrame(double millis) {
    double factor = millis / 20;

    if (++frameCounter > factor * FRAMES_BETWEEN_BULLETS && !spritePool.exhausted()
        && (game.input.isKeyDown(' ') || game.input.getClick())) {
      spritePool.create();
      frameCounter = 0;
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
