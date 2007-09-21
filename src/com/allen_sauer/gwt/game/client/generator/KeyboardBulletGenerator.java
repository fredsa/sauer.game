package com.allen_sauer.gwt.game.client.generator;

import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.client.ui.util.Page;

public class KeyboardBulletGenerator implements Generator {
  private static final int FRAMES_BETWEEN_BULLETS = 7;
  private int frameCounter;
  private boolean markedForRemoval = false;
  private final SpritePool spritePool;

  public KeyboardBulletGenerator(SpritePool spritePool) {
    this.spritePool = spritePool;
    spritePool.getFrameListenerCollection().addFrameListener(this);
  }

  public void doFirstFrame() {
    frameCounter = 0;
  }

  public boolean doFrame() {
    if (++frameCounter > FRAMES_BETWEEN_BULLETS && !spritePool.exhausted()
        && Page.isKeyDown(' ')) {
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
